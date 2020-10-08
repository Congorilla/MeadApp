package com.example.meadapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorman;
    private TextView dice1_num;
    private TextView dice2_num;
    private TextView dice3_num;
    private String[] dice1_numbers;
    private String[] dice2_numbers;
    private String[] dice3_numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorman = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        dice1_num = (TextView)findViewById(R.id.dice1_number);
        dice2_num = (TextView)findViewById(R.id.dice2_number);
        dice3_num = (TextView)findViewById(R.id.dice3_number);
        dice1_numbers = new String[]{"0","1","2","3","4","5"};
        dice2_numbers = new String[]{"0","0","1","2","3","4"};
        dice3_numbers = new String[]{"10","20","20","30","30","30"};
    }

    public void rollDice(){
        dice1_num.setText(dice1_numbers[(int)(Math.random()*7)]);
        dice2_num.setText(dice2_numbers[(int)(Math.random()*7)]);
        dice3_num.setText(dice3_numbers[(int)(Math.random()*7)]);
    }

    @Override public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    @Override public void onSensorChanged(SensorEvent event){
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        if(Math.abs(x)>15||Math.abs(y)>15||Math.abs(z)>15)
            rollDice();
    }

    @Override protected void onResume(){
        super.onResume();
        sensorman.registerListener(this, sensorman.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                                    SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override protected void onPause(){
        super.onPause();
        sensorman.unregisterListener(this);
    }
}