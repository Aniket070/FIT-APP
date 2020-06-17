package com.example.fitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ScrollView;
import android.widget.TextView;

public class Workout extends AppCompatActivity {
    private Chronometer chronometer;
    private boolean running;
    private long pauseOffset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        chronometer = findViewById(R.id.Chronometer);
        chronometer.setFormat("Time: %s");

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {

            }
        });


    }
    public void startChronometer(View v){
        if(!running){
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }
    public void pauseChronometer(View v){
            if(running){
                pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
                chronometer.stop();
                running = false;
            }
    }
    public void resetChronometer(View v){
            chronometer.setBase(SystemClock.elapsedRealtime());
            pauseOffset=0;

    }

    }

