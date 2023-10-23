package com.project.myapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.project.myapp.R;

public class Video1Activity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video1);

        // Initialize the VideoView
        VideoView videoView = findViewById(R.id.videoView);

        // Set the video path using the resource identifier
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video1);

        // Start playing the video
        videoView.start();

        // Create a media controller for video playback control
        MediaController mediaController = new MediaController(this);

        // Set the anchor view for the media controller
        mediaController.setAnchorView(videoView);

        // Set the media controller for the VideoView
        videoView.setMediaController(mediaController);
    }
}