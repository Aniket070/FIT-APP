package com.example.fitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
public class Home extends AppCompatActivity {


    private TextView Steps;
    private  TextView Distance;
    private TextView Calories;
    private double MagnitudePrevious = 0;
    private Integer stepCount = 0;
    private ProgressBar progress;
    private Integer DistanceTraveled=1;
    private Integer CaloriesBurnt = 1;
    private Integer x=0;
    private Button btnHome;
    private Button btnWorkout;
    private Button btnProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //btn for test
        btnHome = findViewById(R.id.btnHome);
        btnProfile = findViewById(R.id.btnProfile);
        btnWorkout = findViewById(R.id.btnWorkout);

        Steps = findViewById(R.id.textView);
        Distance = findViewById(R.id.DistanceH);
        Calories = findViewById(R.id.CaloriesH);
        progress = findViewById(R.id.progressBar);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        final Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        btnWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Workout = new Intent(Home.this, Workout.class);
                Home.this.startActivity(Workout);
            }
        });
        SensorEventListener stepDetector = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent != null){
                    float x_accleration = sensorEvent.values[0];
                    float y_accleration = sensorEvent.values[1];
                    float z_accleration = sensorEvent.values[2];

                    double Magnitude = Math.sqrt(x_accleration*x_accleration + y_accleration*y_accleration + z_accleration*z_accleration);

                    double MagnitudeDelta = Magnitude - MagnitudePrevious;
                    MagnitudePrevious = Magnitude;

                    if(MagnitudeDelta > 6 ){
                        stepCount++;
                    }
                    Steps.setText(stepCount.toString());
                    progress.setProgress(stepCount);
                    DistanceTraveled = (76*stepCount)/100;
                    Distance.setText(DistanceTraveled.toString());
                    x=stepCount;
                    CaloriesBurnt= (35*stepCount)/100;
                    Calories.setText(CaloriesBurnt.toString());
                }


            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sensorManager.registerListener(stepDetector,sensor,SensorManager.SENSOR_DELAY_NORMAL);

    }

    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt("stepCount",stepCount);
        editor.apply();

    }
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt("stepCount",stepCount);
        editor.apply();

    }
    protected void onResume(){
        super.onResume();
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        stepCount = sharedPreferences.getInt("stepCount",0);
    }


}
