package com.example.fitlife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

//This class allows the user to view other profiles of other users. User is able to send friend request, cancel friend request.
//The reciever of the friend request can then use them to either deny the request or accept it. When accepted both sender and reciever are allowed to unfriend each other
public class PersonProfileActivity extends AppCompatActivity {

    TextView first, last, email, user;
    CircleImageView profileImage;
    Button sendFriendRequest, declineFriendRequest, group;
    FirebaseAuth fAuth;
    DatabaseReference reference, userRef, friendsRef;
    FirebaseDatabase fData;
    StorageReference storageReference;
    String sendUserId, recieverUserId, currentState, saveCurrentDate, challenge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_profile);


        fAuth = FirebaseAuth.getInstance();
        sendUserId =fAuth.getCurrentUser().getUid();
        recieverUserId = getIntent().getExtras().get("user_id").toString();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        IntializeFields();

        reference = FirebaseDatabase.getInstance().getReference().child("FriendRequests");
        friendsRef = FirebaseDatabase.getInstance().getReference().child("Friends");

        storageReference = FirebaseStorage.getInstance().getReference().child("users/"+recieverUserId+"/profile.jpg");

        //Retrieving the Clicked person Information From Firebase
        userRef.child(recieverUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user.setText(snapshot.child("Username").getValue(String.class));
                first.setText(snapshot.child("FirstName").getValue(String.class));
                last.setText(snapshot.child("LastName").getValue(String.class));
                email.setText(snapshot.child("Email").getValue(String.class));

                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profileImage);
                    }
                });

                MaintenanceOfButtons();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        declineFriendRequest.setVisibility(View.INVISIBLE);
        declineFriendRequest.setEnabled(false);

        group.setVisibility(View.INVISIBLE);
        group.setEnabled(false);

        if(!sendUserId.equals(recieverUserId)){
            sendFriendRequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sendFriendRequest.setEnabled(false);

                    if(currentState.equals("not_friends")){
                        SendFriendRequestToPerson();
                    }

                    if(currentState.equals("request_sent")){
                        CancelFriendRequest();
                    }
                    if(currentState.equals("request_received")){
                        AcceptFriendRequest();
                    }
                    if(currentState.equals("friends")){
                        UnFriendAnExistingFriend();
                    }
                }
            });
        }
        else{
            declineFriendRequest.setVisibility(View.INVISIBLE);
            sendFriendRequest.setVisibility(View.INVISIBLE);
            group.setVisibility(View.INVISIBLE);
        }

    }

    //When friends are made both the user and user can unfriend each other. This updates the database to make the friendship data deleted.
    private void UnFriendAnExistingFriend() {
        friendsRef.child(sendUserId).child(recieverUserId).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    friendsRef.child(recieverUserId).child(sendUserId).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                sendFriendRequest.setEnabled(true);
                                currentState = "not_friends";
                                sendFriendRequest.setText("Send Friend Request");

                                declineFriendRequest.setVisibility(View.INVISIBLE);
                                declineFriendRequest.setEnabled(false);

                                group.setVisibility(View.INVISIBLE);
                                group.setEnabled(false);
                            }
                        }
                    });
                }
            }
        });
    }

    //User can Accept the Friend Request
    private void AcceptFriendRequest() {
        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());
        HashMap newInput = new HashMap();
        newInput.put("date", saveCurrentDate);
        newInput.put("array", "Null");
        newInput.put("counter", 0);

        //deletes the friend request node within the database and also creates a new node of friends and updates the users to that.
        friendsRef.child(sendUserId).child(recieverUserId).setValue(newInput).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    friendsRef.child(recieverUserId).child(sendUserId).setValue(newInput).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                reference.child(sendUserId).child(recieverUserId).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            reference.child(recieverUserId).child(sendUserId).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){
                                                        sendFriendRequest.setEnabled(true);
                                                        currentState = "friends";
                                                        sendFriendRequest.setText("Unfriend");

                                                        declineFriendRequest.setVisibility(View.INVISIBLE);
                                                        declineFriendRequest.setEnabled(false);

                                                        group.setVisibility(View.VISIBLE);
                                                        group.setEnabled(true);
                                                    }
                                                }
                                            });
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
    }

    //Used so users can cancel a friend request that they have sent out
    private void CancelFriendRequest() {
        reference.child(sendUserId).child(recieverUserId).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    reference.child(recieverUserId).child(sendUserId).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                sendFriendRequest.setEnabled(true);
                                currentState = "not_friends";
                                sendFriendRequest.setText("Send Friend Request");

                                declineFriendRequest.setVisibility(View.INVISIBLE);
                                declineFriendRequest.setEnabled(false);

                                group.setVisibility(View.INVISIBLE);
                                group.setEnabled(false);
                            }
                        }
                    });
                }
            }
        });
    }

    //This Function ensures that when a user has already sent a friend request and opens up the profile
    //of another user again then they will see the cancel friend request button and not send friend request button
    //Ensures button maintained
    private void MaintenanceOfButtons() {
        reference.child(sendUserId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(recieverUserId)){
                    String request_type = snapshot.child(recieverUserId).child("request_type").getValue().toString();

                    if(request_type.equals("sent")){
                        currentState = "request_sent";
                        sendFriendRequest.setText("Cancel Friend Request");

                        declineFriendRequest.setVisibility(View.INVISIBLE);
                        declineFriendRequest.setEnabled(false);

                        group.setVisibility(View.INVISIBLE);
                        group.setEnabled(false);
                    }
                    else if(request_type.equals("received")){
                        currentState = "request_received";
                        sendFriendRequest.setText("Accept Friend Request");

                        declineFriendRequest.setVisibility(View.VISIBLE);
                        declineFriendRequest.setEnabled(true);

                        declineFriendRequest.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                CancelFriendRequest();
                            }
                        });

                        group.setVisibility(View.INVISIBLE);
                        group.setEnabled(false);
                    }

                }
                else{
                    friendsRef.child(sendUserId).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(recieverUserId)){
                                currentState = "friends";
                                sendFriendRequest.setText("Unfriend");

                                declineFriendRequest.setVisibility(View.INVISIBLE);
                                declineFriendRequest.setEnabled(false);

                                group.setVisibility(View.VISIBLE);
                                group.setEnabled(true);

                                group.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        GroupChall();
                                    }
                                });

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void GroupChall() {
        Intent intent = new Intent(getApplicationContext(), GroupChallenges.class);
        String friend_id = recieverUserId;
        intent.putExtra("friend_id", friend_id);
        startActivity(intent);
    }

    //This Function creates a node within the database to show that a user has sent a friend request or not.
    private void SendFriendRequestToPerson() {
        reference.child(sendUserId).child(recieverUserId).child("request_type").setValue("sent").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    reference.child(recieverUserId).child(sendUserId).child("request_type").setValue("received").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                sendFriendRequest.setEnabled(true);
                                currentState = "request_sent";
                                sendFriendRequest.setText("Cancel Friend Request");

                                declineFriendRequest.setVisibility(View.INVISIBLE);
                                declineFriendRequest.setEnabled(false);

                                group.setVisibility(View.INVISIBLE);
                                group.setEnabled(false);
                            }
                        }
                    });
                }
            }
        });
    }


    //Initializes the different buttons and textview parts of the application
    private void IntializeFields() {
        first= findViewById(R.id.friendFirst);
        last = findViewById(R.id.friendLast);
        email = findViewById(R.id.friendEmail);
        user = findViewById(R.id.friendUser);
        profileImage = findViewById(R.id.friendImage);

        sendFriendRequest = findViewById(R.id.person_send_friend_request);
        declineFriendRequest = findViewById(R.id.person_decline_friend_request);
        group = findViewById(R.id.groupChallenge);

        currentState = "not_friends";
        challenge = "not_sent";
    }
}