package com.example.fitlife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

//This class takes Creates a recyclerview where users can see all the users within the application. From there the user can click on the item list and then be taken to the Person
//profile activity
public class tableList extends AppCompatActivity {
    ValueEventListener listener;
    DatabaseReference reference;
    List<item2> mUploads;
    RecyclerView recyclerView;
    leaderboardAdapter myAdapter;
    Button button;
    leaderboardAdapter.RecyclerViewClickListener listen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        button = findViewById(R.id.homeBtn10);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

        mUploads = new ArrayList<>();

        setOnClickListener();
        recyclerView = findViewById(R.id.searchResultList2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.smoothScrollToPosition(0);
        myAdapter = new leaderboardAdapter(mUploads, listen);

        recyclerView.setAdapter(myAdapter);


        reference = FirebaseDatabase.getInstance().getReference().child("Users");
        listener = reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mUploads.clear();
                //This Adds the data that will be shown within the recyclerview
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String FirstName = dataSnapshot.child("FirstName").getValue().toString();
                    String LastName = dataSnapshot.child("LastName").getValue().toString();
                    String Username = dataSnapshot.child("Username").getValue().toString();
                    String Key = dataSnapshot.getKey();
                    mUploads.add(new item2(FirstName, LastName, Username, Key));
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    //Onclick method when it is clicked the user will be transported to the PersonProfileActiviy class
    private void setOnClickListener() {
        listen = new leaderboardAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), friendPoints.class);
                String user_id = mUploads.get(position).getKey();
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        };
    }
}