package com.example.fitlife;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;


public class sigginActivity extends AppCompatActivity {
    private TextView login;
    private Button button;
    private FirebaseAuth mAuth;
    private EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siggin);

        editTextFirstName = findViewById(R.id.etFirstName);
        editTextLastName = findViewById(R.id.etLastName);
        editTextEmail = findViewById(R.id.etEmail);
        editTextPassword = findViewById(R.id.etPassword);
        button = findViewById(R.id.btnNext);
        login = findViewById(R.id.btnLogin);

        mAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        if(mAuth.getCurrentUser() != null){
            openQuestionaire();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
                mAuth.createUserWithEmailAndPassword(editTextEmail.toString(), editTextPassword.toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            openQuestionaire();
                        }
                        else{
                        }
                    }
                });
            }
        });


        button = (Button) findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin();
            }
        });


   }

    public void openQuestionaire(){
        Intent intent = new Intent(this, qeustionaireActivity.class);
        startActivity(intent);
    }

    public  void openLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }




    //This Function to Ensure that the user does submit all text submission for the Registration
    public void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();

        if(firstName.isEmpty()){
            editTextFirstName.setError("First Name Required!");
            editTextFirstName.requestFocus();
            return;
        }
        if(lastName.isEmpty()){
            editTextLastName.setError("Last Name Required!");
            editTextLastName.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editTextEmail.setError("Email Required!");
            editTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("Password Required!");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            editTextPassword.setError("Password Length Must Be Greater than 6");
            editTextPassword.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches());
        {
            editTextEmail.setError("Please Provide valid Email!");
            editTextEmail.requestFocus();
            return;
        }
    }

}