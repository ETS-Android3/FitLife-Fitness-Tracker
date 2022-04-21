package com.example.fitlife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddFeedbackActivity extends AppCompatActivity {


    EditText name,message;
    Button applyBtn;
    FirebaseFirestore firebaseFirestore;

    FirebaseAuth auth;
    String nameStr,messageStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feedback);

        name = findViewById(R.id.name);
        message = findViewById(R.id.message);
        applyBtn = findViewById(R.id.applyBtn);

        auth = FirebaseAuth.getInstance();

        firebaseFirestore = FirebaseFirestore.getInstance();
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!name.getText().toString().isEmpty() && !message.getText().toString().isEmpty()){


                    nameStr = name.getText().toString();
                    messageStr = message.getText().toString();

                    if(auth.getCurrentUser()!=null){
                        inputApplicationData(nameStr,messageStr);

                    }
                }



            }
        });

    }


    private void inputApplicationData(String n,String m) {

            DocumentReference documentReference =
                    firebaseFirestore.collection("Feedback").document();
            Map<String, Object> user = new HashMap<>();
            user.put("email", auth.getCurrentUser().getEmail());
            user.put("name", n);
            user.put("message",m );//the application will be pending status by default


            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(AddFeedbackActivity.this, "Thank you for your feedback", Toast.LENGTH_SHORT).show();
                }
            });

            Intent intent = new Intent(AddFeedbackActivity.this, FeedBackActivity.class);
            startActivity(intent);



    }

}