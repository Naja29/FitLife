package com.project.myapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.project.myapp.R;

public class ProfileActivity extends AppCompatActivity {

    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize the "Login" button and set its click listener
        button = (Button) findViewById(R.id.loginbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the LoginActivity when the "Login" button is clicked
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // Initialize the "Signup" button and set its click listener
        button = (Button) findViewById(R.id.signupbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the SignupActivity when the "Signup" button is clicked
                Intent intent = new Intent(ProfileActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}