package com.project.myapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.project.myapp.R;

public class MediaActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    public ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        // Set click listeners for video buttons
        imageButton = (ImageButton) findViewById(R.id.video1);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaActivity.this, Video1Activity.class);
                startActivity(intent);
            }
        });

        imageButton = (ImageButton) findViewById(R.id.video2);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaActivity.this, Video2Activity.class);
                startActivity(intent);
            }
        });
    }

    // Play, pause, and stop functions for the first song
    public void playButton(View view) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.audio1);
            mediaPlayer.start();
        }
    }

    public void pauseButton(View view) {
        mediaPlayer.pause();
    }

    public void stopButton(View view) {
        mediaPlayer.stop();
        mediaPlayer = null;
    }

    // Play, pause, and stop functions for the second song
    public void playButton2(View view) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.audio2);
        }
        mediaPlayer.start();
    }

    public void pauseButton2(View view) {
        mediaPlayer.pause();
    }

    public void stopButton2(View view) {
        mediaPlayer.stop();
        mediaPlayer = null;
    }

    // Play, pause, and stop functions for the third song
    public void playButton3(View view) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.audio3);
        }
        mediaPlayer.start();
    }

    public void pauseButton3(View view) {
        mediaPlayer.pause();
    }

    public void stopButton3(View view) {
        mediaPlayer.stop();
        mediaPlayer = null;
    }
}