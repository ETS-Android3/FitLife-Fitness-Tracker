package com.example.fitlife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class TimelyReviewActivity extends AppCompatActivity {


    TextView dateRangeText;
    Button calendar;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timely_review);

        db = FirebaseFirestore.getInstance();
        dateRangeText=findViewById(R.id.range);
        calendar = findViewById(R.id.calender);


        MaterialDatePicker materialDatePicker =  MaterialDatePicker.Builder.dateRangePicker()
                .setSelection(Pair.create(MaterialDatePicker.thisMonthInUtcMilliseconds(),MaterialDatePicker.todayInUtcMilliseconds())).build();



        MaterialDatePicker.Builder<Pair<Long, Long>> materialDateBuilder = MaterialDatePicker.Builder.dateRangePicker();
        materialDateBuilder.setTitleText("SELECT A DATE");
        final MaterialDatePicker materialDatePicker2 = materialDateBuilder.build();

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                materialDatePicker2.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");

                materialDatePicker2.addOnPositiveButtonClickListener(
                        new MaterialPickerOnPositiveButtonClickListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onPositiveButtonClick(Object selection) {

                                String str = selection.toString().replace("Pair","").replace("{","").replace("}","");

                                String[] splited = str.split("\\s+");

                                long start = Long.parseLong(splited[0]);
                                long end = Long.parseLong(splited[1]);

                                Intent intent = new Intent(getApplicationContext(),TimelyActActivity.class);
                                intent.putExtra("start",start);
                                intent.putExtra("end",end);
                                intent.putExtra("top",materialDatePicker2.getHeaderText());

                                startActivity(intent);


                                dateRangeText.setText( materialDatePicker2.getHeaderText());






                            }
                        });
            }
        });








    }

    @Override
    protected void onStart() {
        super.onStart();
        // adapter.startListening();


    }

    @Override
    protected void onResume() {
        super.onResume();
        // adapter.startListening();

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