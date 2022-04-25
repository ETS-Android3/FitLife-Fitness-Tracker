package com.example.fitlife;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

//This shows all the feedback for our users
public class FeedBackActivity extends AppCompatActivity {

    RecyclerView rvList;

    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);


        rvList=findViewById(R.id.recyclerView);

        rvList.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        rvList.setLayoutManager(linearLayoutManager);


        fab = findViewById(R.id.addFeedBack);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddFeedbackActivity.class));
                finish();
            }
        });

        Query query = FirebaseFirestore.getInstance()
                .collection("Feedback");

        re(query);



    }


    public void re(Query query){
        FirestoreRecyclerOptions<FeedBack> options = new FirestoreRecyclerOptions.Builder<FeedBack>()
                .setQuery(query, FeedBack.class)
                .build();


        FirestoreRecyclerAdapter adapter = new FirestoreRecyclerAdapter<FeedBack, Viewholder>(options) {
            @Override
            public void onBindViewHolder(Viewholder holder, int position, FeedBack job) {


                holder.name.setText(job.getName());
                holder.email.setText(job.getEmail());

                holder.message.setText(job.getMessage());







            }

            @Override
            public Viewholder onCreateViewHolder(ViewGroup group, int i) {
                // Using a custom layout called R.layout.message for each item, we create a new instance of the viewholder
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.feedback_row, group, false);

                return new Viewholder(view);
            }
        };
        adapter.startListening();

        rvList.setAdapter(adapter);

    }


    static class Viewholder extends RecyclerView.ViewHolder {

        private TextView name,email,message;



        public Viewholder(@NonNull View itemView)
        {
            super(itemView);


            name=itemView.findViewById(R.id.name);
            email=itemView.findViewById(R.id.email);
            message=itemView.findViewById(R.id.message);



        }
    }

}