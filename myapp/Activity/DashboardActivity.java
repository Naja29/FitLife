package com.project.myapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.project.myapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class DashboardActivity extends AppCompatActivity {

    public ImageView imageview;
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize logout button
        button = (Button) findViewById(R.id.logoutbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start ProfileActivity when logout button is clicked
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        // Initialize circle image view for PlanActivity
        CircleImageView planBtn = (CircleImageView) findViewById(R.id.btnPlan);
        planBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start PlanActivity when Plan button is clicked
                Intent intent = new Intent(DashboardActivity.this, PlanActivity.class);
                startActivity(intent);
            }
        });

        // Initialize circle image view for MediaActivity
        CircleImageView mediaBtn = (CircleImageView) findViewById(R.id.btnMedia);
        mediaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start MediaActivity when Media button is clicked
                Intent intent = new Intent(DashboardActivity.this, MediaActivity.class);
                startActivity(intent);
            }
        });

        // Initialize circle image view for MapsActivity
        CircleImageView locationBtn = (CircleImageView) findViewById(R.id.btnLocation);
        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start MapsActivity when Location button is clicked
                Intent intent = new Intent(DashboardActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        // Initialize circle image view for StepCountActivity
        CircleImageView sensorBtn = (CircleImageView) findViewById(R.id.btnSensor);
        sensorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start StepCountActivity when Sensor button is clicked
                Intent intent = new Intent(DashboardActivity.this, StepCountActivity.class);
                startActivity(intent);
            }
        });
    }
}


