package com.example.myapplication5;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener {
    MediaPlayer mediaPlayer;
    Button startButton, pauseButton, stopButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer=MediaPlayer.create(this, R.raw.music);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay();
            }
        });
        startButton = (Button) findViewById(R.id.buttonStart);
        pauseButton = (Button) findViewById(R.id.buttonPause);
        stopButton = (Button) findViewById(R.id.buttonStop);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
    }
    private void stopPlay(){
        mediaPlayer.stop();
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
        try {
            mediaPlayer.prepare();
            mediaPlayer.seekTo(0);
            startButton.setEnabled(true);
        }
        catch (Throwable t) {
            Toast.makeText(this, t.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void onStartClick(View view)
    {
        mediaPlayer.start();
        startButton.setEnabled(false);
        pauseButton.setEnabled(true);
        stopButton.setEnabled(true);
    }
    public void onPauseClick(View view)
    {

        mediaPlayer.pause();
        startButton.setEnabled(true);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(true);
    }

    public void onStopClick(View view)
    {
        stopPlay();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer.isPlaying()) {
            stopPlay();
        }
    }
    @Override
    public void onPrepared(MediaPlayer mp) {
    }
}