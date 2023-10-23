package com.project.myapp.Activity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.project.myapp.Activity.DashboardActivity;
import com.project.myapp.Activity.SignupActivity;
import com.project.myapp.Database.DatabaseHelper;
import com.project.myapp.R;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton, signupButton;
    private ImageView googleImage, facebookImage, twitterImage;
    private EditText username, password;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        // Initialize database helper
        db = new DatabaseHelper(this);

        // Initialize login button
        loginButton = findViewById(R.id.loginbtn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get username and password values from EditText fields
                String userValue = username.getText().toString();
                String passwordValue = password.getText().toString();

                if (userValue.isEmpty() || passwordValue.isEmpty()) {
                    // Display a toast message if any of the fields is empty
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Check if the entered username and password match the records in the database
                    Boolean checkUserPassword = db.checkUsernamePassword(userValue, passwordValue);
                    if (checkUserPassword) {
                        // Display a toast message for successful sign-in
                        Toast.makeText(LoginActivity.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                        // Start DashboardActivity when login is successful
                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        startActivity(intent);
                    } else {
                        // Display a toast message for invalid username or password
                        Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Initialize signup button
        signupButton = findViewById(R.id.signupbtn);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start SignupActivity when signup button is clicked
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        // Initialize Google image view
        googleImage = findViewById(R.id.google);
        googleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open the Google website in a browser when Google image is clicked
                goToUrl("http://www.google.com");
            }
        });

        // Initialize Facebook image view
        facebookImage = findViewById(R.id.facebook);
        facebookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open the Facebook website in a browser when Facebook image is clicked
                goToUrl("https://web.facebook.com");
            }
        });

        // Initialize Twitter image view
        twitterImage = findViewById(R.id.twitter);
        twitterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open the Twitter website in a browser when Twitter image is clicked
                goToUrl("https://twitter.com");
            }
        });
    }

    private void goToUrl(String url) {
        // Open a URL in a browser when called
        Uri uri = Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}




