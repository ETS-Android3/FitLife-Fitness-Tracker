package com.example.fitlife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Item> items;
    RecyclerViewClickListener listener;

     public MyAdapter(List<Item> items, RecyclerViewClickListener listener){
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
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_user_display,parent,false);

         return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
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



        /*/
        holder.first.setText(items.get(position).getFirstName());
        holder.last.setText(items.get(position).getLastName());
        holder.user.setText(items.get(position).getUsername());
        */

}
