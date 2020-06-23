package com.example.skhool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {
    EditText editTextUsername, editTextEmail, editTextPassword;
    Button registerBtn;
    TextView goToLogin;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerBtn = findViewById(R.id.registerBtn);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        progressBar = findViewById(R.id.progressBar);

        fAuth = FirebaseAuth.getInstance();
        if (fAuth.getCurrentUser()!=null){
            startActivity(new Intent(this.getApplicationContext(),DashboardActivity.class));
        }
        //When register button is clicked
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim( );
                String password = editTextPassword.getText().toString().trim();

                if (TextUtils.isEmpty(username)){
                    editTextUsername.setError("Username is required.");
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    editTextEmail.setError("Email is required.");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    editTextPassword.setError("Password is required.");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                //Register user in Firebase
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Account Created!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
                        }
                        else {
                             Toast.makeText(RegisterActivity.this,"Error! " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}