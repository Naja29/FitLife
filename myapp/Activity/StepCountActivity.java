package com.project.myapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.project.myapp.R;

public class StepCountActivity extends AppCompatActivity implements SensorEventListener {

    private TextView stepCountTextView;
    private Button restartButton;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private int stepCount = 0;
    private boolean isStepCounting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_count);

        // Get reference to the sensor manager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        stepCountTextView = findViewById(R.id.stepCountTextView);
        restartButton = findViewById(R.id.restartButton);

        // Button to restart the step count
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stepCount = 0;
                stepCountTextView.setText(String.valueOf(stepCount));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register the sensor listener when the activity resumes
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the sensor listener when the activity is paused
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Get the accelerometer values
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        // Calculate the acceleration excluding gravity
        float acceleration = (float) Math.sqrt(x * x + y * y + z * z) - SensorManager.GRAVITY_EARTH;

        if (acceleration > 5 && !isStepCounting) {
            // Start counting steps if acceleration exceeds a threshold and step counting is not in progress
            isStepCounting = true;
            stepCount++;
        } else if (acceleration < 0 && isStepCounting) {
            // Stop counting steps if acceleration drops below a threshold and step counting is in progress
            isStepCounting = false;
        }

        // Update the step count display
        stepCountTextView.setText(String.valueOf(stepCount));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do nothing
    }
}