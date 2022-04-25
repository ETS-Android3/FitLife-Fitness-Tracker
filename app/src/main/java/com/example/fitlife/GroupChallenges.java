package com.example.fitlife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class GroupChallenges extends AppCompatActivity {

    TextView friendName, showChallenge;
    Button complete, notYet;
    String friendId, myId, currentState, fName, lName, x, place;
    FirebaseAuth firebaseAuth;
    int counter, points, newPoints;
    DatabaseReference setArray, placeHolder, status;
    String challenges[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_challenges);

        firebaseAuth = FirebaseAuth.getInstance();
        myId = firebaseAuth.getCurrentUser().getUid();
        friendId = getIntent().getExtras().get("friend_id").toString();

        setArray = FirebaseDatabase.getInstance().getReference().child("Friends");
        setArray.child(myId).child(friendId).child("array").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String check = snapshot.getValue().toString();
                if(check.equals("Null")){
                    setGroupChallenge();
                }
                else{
                    showChallenge.setText(check);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        //Placeholder database reference to put the name of the friend that they are doing a group challenge with
        placeHolder = FirebaseDatabase.getInstance().getReference().child("Users");
        initialFields();

        notYet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SearchFriends.class));
                finish();
            }
        });


        status = FirebaseDatabase.getInstance().getReference().child("Status");

        //Initialize the Text View with the respective friends Name
        placeHolder.child(friendId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fName = snapshot.child("FirstName").getValue().toString();
                lName = snapshot.child("LastName").getValue().toString();
                friendName.setText(fName + " " + lName);

                maintainButtons();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                complete.setEnabled(false);
                if(currentState.equals("not_started")){
                    complete.setText("Start Group Challenge");
                    StartChallenge();
                }
                if(currentState.equals("not_complete")){
                    CompletedTask();
                }
                if(currentState.equals("Waiting")){
                    waitForFriend();
                }
                if(currentState.equals("CatchUp")){
                    CatchUp();
                }
            }
        });

    }

    //This function is supposed to show a new text value for when the points get added.
    private void setGroupChallenge() {

        x = getChallenge();

        //Setting the Text Value for the challenge
        setArray.child(myId).child(friendId).child("array").setValue(x).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    setArray.child(friendId).child(myId).child("array").setValue(x);
                }
            }
        });

        setArray.child(friendId).child(myId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                place = snapshot.child("array").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //Stores to the the database that they have created a group challenge. This will be the start that uses
    private void StartChallenge() {
        status.child(myId).child(friendId).child("MyStatus").setValue("Started").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    status.child(friendId).child(myId).child("MyStatus").setValue("Started").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                complete.setEnabled(true);
                                complete.setText("Completed Group Challenge");
                                currentState = "not_complete";

                                notYet.setEnabled(true);
                                notYet.setVisibility(View.VISIBLE);

                            }
                        }
                    });

                }
            }
        });
    }

    //This is the functionality for the second user who has not completed the challenge. When they click the button they will be given to this function where they would be able to
    //Change there group status.
    private void CatchUp() {
        status.child(myId).child(friendId).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    status.child(friendId).child(myId).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                complete.setEnabled(true);
                                complete.setText("Start Group Challenge");
                                currentState = "not_started";
                                showChallenge.setText("Group Challenge: ");

                                String check = "Null";
                                setArray.child(myId).child(friendId).child("array").setValue(check).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()) {
                                            setArray.child(friendId).child(myId).child("array").setValue(check).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(GroupChallenges.this, "Array Has Updated", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                        }
                                    }
                                });

                                placeHolder.child(myId).child("Points").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        String current = snapshot.getValue().toString();
                                        points = Integer.parseInt(current);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                                placeHolder.child(myId).child("Points").setValue(points + 5).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(GroupChallenges.this, "Your Points Have Been Updated", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                notYet.setEnabled(true);
                                notYet.setVisibility(View.VISIBLE);
                                notYet.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivity(new Intent(getApplicationContext(), SearchFriends.class));
                                        finish();
                                    }
                                });

                            }
                        }
                    });
                }
            }
        });
    }

    //Array for the Users that will show the different group challenges challenges
    private String getChallenge() {
        String dailyChal;
        challenges = new String[]{"Do 10 push-ups today", "Do 10 sit-ups today", "Run for 2 miles today",
                "Do 10 squats today", "Take a 20 minute walk today", "Do a 1 minute plank today",
                "Do 10 burpees today", "Take a 5 minute jog today", "Do 5 minutes of HIIT today",
                "Walk an extra 1000 steps today", "Drink more water today", "Do 10 minutes of yoga today",
                "Do 10 leg raises today", "Do high knees for 2 minutes today", "Do 20 lunges today"};

        //counter used to store array value.
        setArray.child(myId).child(friendId).child("counter").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String count = snapshot.getValue().toString();
                counter = Integer.parseInt(count);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        setArray.child(myId).child(friendId).child("counter").setValue(counter+1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                        setArray.child(friendId).child(myId).child("counter").setValue(counter+1).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(GroupChallenges.this, "Counter Updated", Toast.LENGTH_SHORT).show();
                            }
                        });
                }
            }
        });
        dailyChal = challenges[counter];
        return dailyChal;
    }

    //Method used to start main activity, making sure user can go back to home page.
    private void waitForFriend() {
        startActivity(new Intent(getApplicationContext(), SearchFriends.class));
        finish();
    }

    //This function adds the user points and updates that value into the database. Changes the status for the two friends within database to know which function must be executed.
    private void CompletedTask() {
        status.child(myId).child(friendId).child("MyStatus").setValue("Complete").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    status.child(friendId).child(myId).child("MyStatus").setValue("NotYet").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                complete.setEnabled(true);
                                currentState = "Waiting";
                                complete.setText("Waiting For Friend");

                                placeHolder.child(myId).child("Points").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        String current = snapshot.getValue().toString();
                                        points = Integer.parseInt(current);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                                newPoints = points + 5;
                                placeHolder.child(myId).child("Points").setValue(newPoints).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(GroupChallenges.this, "Your Points Have Been Updated", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                notYet.setEnabled(false);
                                notYet.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                }
            }
        });
    }

    //This function makes sure that when a user leaves the activity that they are still able to see the correct text views. Make sure the buttons are the same
    private void maintainButtons() {
        status.child(myId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(friendId)){
                    String request_type = snapshot.child(friendId).child("MyStatus").getValue().toString();

                    //Chooses what buttons will be shown to the user
                    if(request_type.equals("Complete")){
                        currentState = "Waiting";
                        complete.setText("Waiting");

                        notYet.setEnabled(false);
                        notYet.setVisibility(View.INVISIBLE);
                    }
                    else if(request_type.equals("NotYet")){
                        currentState = "CatchUp";
                        complete.setText("Second Complete");

                        notYet.setEnabled(true);
                        notYet.setVisibility(View.VISIBLE);
                        notYet.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(getApplicationContext(), SearchFriends.class));
                            }
                        });
                    }
                    else if(request_type.equals("Started")){
                        currentState = "not_started";
                        complete.setText("Start Group Challenge");

                        notYet.setEnabled(false);
                        notYet.setVisibility(View.INVISIBLE);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //Method that initializes all of our methods, makes our code easier to read
    private void initialFields() {
        showChallenge = findViewById(R.id.challenge);
        friendName = findViewById(R.id.thereName);
        complete = findViewById(R.id.completeTask2);
        notYet = findViewById(R.id.notYet2);

        currentState = "not_started";
    }
}