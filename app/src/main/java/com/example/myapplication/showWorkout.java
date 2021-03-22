package com.example.myapplication;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeMap;

public class showWorkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        String workout_type = getIntent().getStringExtra("Workout Type");
        String[] workout_type_arr = workout_type.split(" ");
        TypesOfWorkouts workout = new TypesOfWorkouts();
        TreeMap<String, Integer> getWorkout = new TreeMap<>();
        TreeMap<String, Integer> getSubWorkout = new TreeMap<>();
        switch (workout_type_arr[0]) {
            case "가슴":
                getWorkout = workout.Chest();
                break;
            case "어깨":
                getWorkout = workout.Shoulder();
                break;
            case "등":
                getWorkout = workout.Back();
                break;
            case "하체":
                getWorkout = workout.Leg();
                break;                              // getWorkout contains Treemap of <WorkoutInfo , Sets)
        }

        switch (workout_type_arr[1]) {
            case "복근":
                getSubWorkout = workout.Abs();
                break;
            case "팔":
                getSubWorkout = workout.Arm();
                break;
            case "유산소":
                getSubWorkout = workout.Running();
                break;
        }

        ArrayList<String> keys = new ArrayList<>(getWorkout.keySet());
        ArrayList<String> keys2 = new ArrayList<>(getSubWorkout.keySet());
        String[] arr_keys = new String[keys.size() + keys2.size()];
        for(int i = 0; i < keys.size(); i++){
            arr_keys[i] = keys.get(i);
        }
        int j = 0;
        for(int i = keys.size(); i < keys.size() + keys2.size(); i++){
            arr_keys[i] = keys2.get(j);
            j++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr_keys);
        ListView listView1 = findViewById(R.id.listView);
        listView1.setAdapter(adapter);

        TextView description = findViewById(R.id.textView9);
        description.setText("오늘의 운동 계획입니다");
        Button start = findViewById(R.id.button4);
        start.setText("시작");
    }
}