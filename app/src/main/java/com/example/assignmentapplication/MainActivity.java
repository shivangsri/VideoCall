package com.example.assignmentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText emailBox, passwordBox;
    Button loginBtn, signupBtn;

    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        emailBox = findViewById(R.id.emailBox);
        passwordBox = findViewById(R.id.passwordBox);

        signupBtn=findViewById(R.id.createBtn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUp.class));

            }
        });
        loginBtn=findViewById(R.id.loginbtn);

        //Authentication check
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, pass, name;
                email = emailBox.getText().toString().trim();
                pass = passwordBox.getText().toString().trim();

                if(pass.length()<6){
                    passwordBox.setError("Password must be greater than 6 characters");
                    return;
                }
                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {


                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Logged In",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,CallActivity.class));
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();

                        }

                    }
                });


            }
        });
    }
}