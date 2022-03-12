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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


//Function main priority is to allow users to sign up and create an account. It will ask for name, email, password, and username so these can be stored and used later on
public class sigginActivity extends AppCompatActivity {
    private Button login;
    private Button button;
    private FirebaseAuth mAuth;
    private EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword, editTextUser;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siggin);

        editTextFirstName = findViewById(R.id.etFirstName);
        editTextLastName = findViewById(R.id.etLastName);
        editTextEmail = findViewById(R.id.etEmail);
        editTextPassword = findViewById(R.id.etPassword);
        editTextUser = findViewById(R.id.etUser);

        button = findViewById(R.id.btnNext);
        login = findViewById(R.id.btnLogin);

        mAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(),qeustionaireActivity.class));
            finish();
        }

        login = findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString().trim();
                String first = editTextFirstName.getText().toString().trim();
                String last = editTextLastName.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String userName = editTextUser.getText().toString().trim();
                Query userNameQuery = FirebaseDatabase.getInstance().getReference().child("Users").orderByChild("Username").equalTo(userName);
                //Checking if Data is not empty and meets the standards for firebase
                if (TextUtils.isEmpty(email)) {
                    editTextEmail.setError("Email Is Required.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    editTextPassword.setError("Password is Required");
                    return;
                }
                if(TextUtils.isEmpty(first)){
                    editTextFirstName.setError("First Name Required");
                    return;
                }
                if(TextUtils.isEmpty(last)){
                    editTextLastName.setError("Last Name Required");
                    return;
                }
                if (password.length() < 6) {
                    editTextPassword.setError("Password Must Have At Least 6 Characters");
                    return;
                }
                if(TextUtils.isEmpty(userName)){
                    editTextUser.setError("Username is Required");
                    return;
                }
                userNameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    //Function checks first if the username that the person selected is unique.
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.getChildrenCount()>0){
                            Toast.makeText(sigginActivity.this, "Choose A Different Username", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            //If the username is unique the user will be able to create there account.
                            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                //This function adds the user information to the realtime database
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        //Storing Info In DataBase
                                        String user_id = mAuth.getCurrentUser().getUid();
                                        DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

                                        Map newPost = new HashMap();
                                        newPost.put("First Name", first);
                                        newPost.put("Last Name", last);
                                        newPost.put("Username", userName);
                                        newPost.put("Email", email);
                                        newPost.put("Password", password);
                                        current_user_db.setValue(newPost);


                                        Toast.makeText(sigginActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),qeustionaireActivity.class));
                                    } else {
                                        Toast.makeText(sigginActivity.this, "Error Occurred" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                progressBar.setVisibility(View.VISIBLE);


            }

        });

    }

}