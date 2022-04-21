package com.example.fitlife;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

//Functions necessary for implementing a recyclerview
public class leaderboardAdapter extends RecyclerView.Adapter<leaderboardAdapter.MyViewHolder> {
    List<item2> items;
    RecyclerViewClickListener listener;

    public leaderboardAdapter(List<item2> items, RecyclerViewClickListener listener){
        this.items = items;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView first, last, user;

        public MyViewHolder(final View itemView) {
            super(itemView);
            first = itemView.findViewById(R.id.friendFName);
            last = itemView.findViewById(R.id.friendLName);
            user = itemView.findViewById(R.id.friendUName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public leaderboardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_user_display,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull leaderboardAdapter.MyViewHolder holder, int position) {
        String Fname = items.get(position).getFirstName();
        String LName = items.get(position).getLastName();
        String Uname = items.get(position).getUsername();
        holder.first.setText(Fname);
        holder.last.setText(LName);
        holder.user.setText(Uname);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }


}
