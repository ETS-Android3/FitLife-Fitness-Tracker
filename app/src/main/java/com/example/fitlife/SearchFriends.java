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

public class SearchFriends extends AppCompatActivity {
    ValueEventListener listener;
    DatabaseReference reference;
    List<Item> mUploads;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friends);

        button = findViewById(R.id.homeBtn3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
        
        reference = FirebaseDatabase.getInstance().getReference().child("Users");
        mUploads = new ArrayList<>();

        recyclerView = findViewById(R.id.searchResultList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.smoothScrollToPosition(0);
        myAdapter = new MyAdapter(this, mUploads);

        recyclerView.setAdapter(myAdapter);

        listener = reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mUploads.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String FirstName = dataSnapshot.child("FirstName").getValue().toString();
                    String LastName = dataSnapshot.child("LastName").getValue().toString();
                    String Username = dataSnapshot.child("Username").getValue().toString();
                    mUploads.add(new Item(FirstName, LastName, Username));
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}