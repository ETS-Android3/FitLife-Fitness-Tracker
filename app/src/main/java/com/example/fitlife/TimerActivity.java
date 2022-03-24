package com.example.fitlife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.SetOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TimerActivity extends AppCompatActivity {

    private int id;
    private String ex_name;
    private String ex_details, type;

    private int seconds = 0;

    // Is the stopwatch running?
    private boolean running;

    TextView exDetails, exName, date, timer;
    Button cancelBtn, startStopBtn,save_btn;
    private boolean wasRunning;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    String name;
    FirebaseFirestore db;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);


        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        name =getIntent().getStringExtra("name");
        exDetails = findViewById(R.id.ex_details);
        exName = findViewById(R.id.ex_name);
        date = findViewById(R.id.date);
        cancelBtn = findViewById(R.id.cancel_btn);
        startStopBtn = findViewById(R.id.start_stop_btn);
        timer = findViewById(R.id.timer);
        save_btn = findViewById(R.id.save_btn);


        date.setText(simpleDateFormat.format(Calendar.getInstance(Locale.ENGLISH)
                .getTime()));
        runTimer();

        cancelBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

        startStopBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                running = !running;

                if (running)
                {
                    startStopBtn.setText("Stop");
                    cancelBtn.setVisibility(View.VISIBLE);

                }
                else
                {


                    startStopBtn.setText("Start");
                }

            }
        });


        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                running = !running;

                if (running)
                {
                    startStopBtn.setText("Stop");
                    cancelBtn.setVisibility(View.VISIBLE);

                }
                else
                {


                    startStopBtn.setText("Start");
                }
                Date c = Calendar.getInstance().getTime();

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                String formattedDate = df.format(c);

                Date currentTime = Calendar.getInstance().getTime();

                //Date currentTime = Calendar.getInstance().getTime();

                Map<String, Object> data = new HashMap<>();
                data.put("name", name);
                data.put("timer", timer.getText().toString());
                data.put("userid", auth.getCurrentUser().getUid());
                data.put("timestamp", FieldValue.serverTimestamp());

                data.put("date", formattedDate);
                data.put("timestamp_milli", System.currentTimeMillis());




                //data.put("time", currentTime);

                db.collection("activities").document().set(data, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();

                        Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });


            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();




    }

    private void runTimer()
    {


        // Creates a new Handler
        final Handler handler = new Handler();

        // Call the post() method,
        // passing in a new Runnable.
        // The post() method processes
        // code without a delay,
        // so the code in the Runnable
        // will run almost immediately.
        handler.post(new Runnable()
        {
            @Override

            public void run()
            {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                // Format the seconds into hours, minutes,
                // and seconds.
                String time
                        = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);

                // Set the text view text.
                timer.setText(time);

                // If running is true, increment the
                // seconds variable.
                if (running)
                {
                    seconds++;
                }

                // Post the code again
                // with a delay of 1 second.
                handler.postDelayed(this, 1000);
            }
        });
    }
}