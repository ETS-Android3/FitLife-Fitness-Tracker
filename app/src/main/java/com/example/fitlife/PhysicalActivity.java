package com.example.fitlife;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PhysicalActivity extends AppCompatActivity {

    private int id;
    private String ex_name;
    private String ex_details, type;

    private int seconds = 0;

    // Is the stopwatch running?
    private boolean running;

    TextView exDetails, exName, date, timer;
    Button cancelBtn, startStopBtn;
    private boolean wasRunning;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    
    public class PhysicalActivity implements AppCompatActivity {

	@Override
	public void notifyUser()
	{
		System.out.println("Java Factory method design pattern");
	}
}

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical);

        exDetails = findViewById(R.id.ex_details);
        exName = findViewById(R.id.ex_name);
        date = findViewById(R.id.date);
        cancelBtn = findViewById(R.id.cancel_btn);
        startStopBtn = findViewById(R.id.start_stop_btn);
        timer = findViewById(R.id.timer);
//        int userid = new SessionManager(CardioTrainDetailsActivity.this).getUserId();
//
//        Toast.makeText(this, new Repo(this).getAllCardioModel(userid).size() + "", Toast.LENGTH_SHORT)
//             .show();

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
