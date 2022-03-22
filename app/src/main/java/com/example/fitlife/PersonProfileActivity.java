package com.example.fitlife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonProfileActivity extends AppCompatActivity {

    TextView first, last, email, user;
    CircleImageView profileImage;
    Button sendFriendRequest, declineFriendRequest;
    FirebaseAuth fAuth;
    DatabaseReference reference, userRef;
    FirebaseDatabase fData;
    StorageReference storageReference;
    String sendUserId, recieverUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_profile);


        fAuth = FirebaseAuth.getInstance();
        recieverUserId = getIntent().getExtras().get("user_id").toString();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        IntializeFields();
        storageReference = FirebaseStorage.getInstance().getReference().child("users/"+recieverUserId+"/profile.jpg");

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

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void IntializeFields() {
        first= findViewById(R.id.friendFirst);
        last = findViewById(R.id.friendLast);
        email = findViewById(R.id.friendEmail);
        user = findViewById(R.id.friendUser);
        profileImage = findViewById(R.id.friendImage);
        sendFriendRequest = findViewById(R.id.person_send_friend_request);
        declineFriendRequest = findViewById(R.id.person_decline_friend_request);
    }
}