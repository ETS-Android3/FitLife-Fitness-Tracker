package com.example.fitlife;

import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

public class TimelyActActivity extends AppCompatActivity {



    FirebaseFirestore db;
    RecyclerView recyclerView;

    long start,end;
    FirestoreRecyclerAdapter adapter;
    String top;
    TextView tvtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timely_act);

        db = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.recyclerView);
        tvtop = findViewById(R.id.top);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        start = getIntent().getLongExtra("start",0);
        end = getIntent().getLongExtra("end",0);
        top = getIntent().getStringExtra("top");
        tvtop.setText(top);

        Query query = db.collection("activities").whereGreaterThanOrEqualTo("timestamp_milli",start).whereLessThanOrEqualTo("timestamp_milli",end);

        FirestoreRecyclerOptions<Activities> options = new FirestoreRecyclerOptions.Builder<Activities>()
                .setQuery(query, Activities.class)
                .build();




        adapter = new FirestoreRecyclerAdapter<Activities, ViewHolder>(options) {
            @Override
            public void onBindViewHolder(ViewHolder holder, int position, Activities model) {

                Log.e("jiji", "onBindViewHolder: "+model.getName() );

                holder.name.setText(model.getName());
                holder.time.setText(model.getTimer());


            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup group, int i) {
                // Using a custom layout called R.layout.message for each item, we create a new instance of the viewholder
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.row, group, false);

                return new ViewHolder(view);
            }
            @Override
            public void onError(FirebaseFirestoreException e) {

                Log.e("lora", "onError: "+e.getMessage() );
                // Called when there is an error getting a query snapshot. You may want to update
                // your UI to display an error message to the user.
                // ...
            }
        };

        adapter.startListening();

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        View mView;
        TextView time,name;

        public ViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

            name = mView.findViewById(R.id.name);
            time = mView.findViewById(R.id.time);

        }

    }
}