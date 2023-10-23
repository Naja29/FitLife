package com.project.myapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.project.myapp.Database.DatabaseHelper;
import com.project.myapp.R;

public class SignupActivity extends AppCompatActivity {

    EditText username, email, password, confirmPassword;
    Button button;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        button = findViewById(R.id.regbtn);
        db = new DatabaseHelper(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userValue = username.getText().toString();
                String emailValue = email.getText().toString();
                String passwordValue = password.getText().toString();
                String confirmPasswordValue = confirmPassword.getText().toString();

                if (userValue.isEmpty()) {
                    username.setError("Username is required");
                    username.requestFocus();
                    return;
                }

                if (emailValue.isEmpty()) {
                    email.setError("Email is required");
                    email.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()) {
                    email.setError("Please enter a valid email");
                    email.requestFocus();
                    return;
                }

                if (passwordValue.isEmpty()) {
                    password.setError("Password is required");
                    password.requestFocus();
                    return;
                }

                if (passwordValue.length() < 5) {
                    password.setError("Minimum length of password should be 5");
                    password.requestFocus();
                    return;
                }

                if (!confirmPasswordValue.equals(passwordValue)) {
                    confirmPassword.setError("Password does not match");
                    confirmPassword.requestFocus();
                    return;
                }

                // Check if user already exists
                if (db.checkUser(emailValue)) {
                    Toast.makeText(SignupActivity.this, "Email already exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if all fields are filled
                if (userValue.equals("") || emailValue.equals("") || passwordValue.equals("") || confirmPasswordValue.equals("")) {
                    Toast.makeText(SignupActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkUser = db.checkUsername(userValue);
                    if (checkUser == false) {
                        Boolean insert = db.insertData(userValue, emailValue, passwordValue, confirmPasswordValue);
                        if (insert == true) {
                            Toast.makeText(SignupActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignupActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignupActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}