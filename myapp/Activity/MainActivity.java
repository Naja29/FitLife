package com.project.myapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.project.myapp.R;

public class MainActivity extends AppCompatActivity {

    // Duration of the splash screen in milliseconds
    public static int SPLASH_TIMER = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Using a Handler to delay the start of the next activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create an intent to navigate to the ProfileActivity
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIMER);
    }
}
