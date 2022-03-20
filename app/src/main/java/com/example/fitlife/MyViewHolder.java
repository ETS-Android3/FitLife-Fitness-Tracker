package com.example.fitlife;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView first, last, user;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        first = itemView.findViewById(R.id.friendFName);
        last = itemView.findViewById(R.id.friendLName);
        user = itemView.findViewById(R.id.friendUName);
    }
}
