package com.example.fitlife;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
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
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(),qeustionaireActivity.class));
            finish();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                //Checking if Data is Valid
                if (TextUtils.isEmpty(email)) {
                    editTextEmail.setError("Email Is Required.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    editTextPassword.setError("Password is Required");
                    return;
                }
                if (password.length() < 6) {
                    editTextPassword.setError("Password Must Have At Least 6 Characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                //Register User To FireBase
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(sigginActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),qeustionaireActivity.class));
                        } else {
                            Toast.makeText(sigginActivity.this, "Error Occured" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
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

        });

    }

        public void openLogin () {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }



}