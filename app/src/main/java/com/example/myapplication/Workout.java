package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Workout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        User user = getIntent().getParcelableExtra("UserInfo");
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TextView which_workout = findViewById(R.id.textView7);
        which_workout.setText("오늘의 운동?");
        TextView show_id = findViewById(R.id.textView11);
        show_id.setText(user.getUsername() + "님");

        String recommend = recommended_workout(user.getLast_Workout());

        TextView select_main = findViewById(R.id.textView12);
        select_main.setText("메인 운동을 정해주세요 (추천: " + recommend + ")");
        String[] list_of_workouts = {"가슴", "어깨", "등", "하체"};
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list_of_workouts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        TextView select_sub = findViewById(R.id.textView14);
        select_sub.setText("서브 운동을 정해주세요");
        String[] sub_list_of_workouts = {"복근", "팔", "유산소"};
        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sub_list_of_workouts);
        spinner2.setAdapter(adapter2);
        Button submit = findViewById(R.id.button3);
        submit.setText("확인");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String main = spinner.getSelectedItem().toString(); // main = 주 운동
                String sub = spinner2.getSelectedItem().toString(); // sub = 서브 운동
                Intent show_workout = new Intent();
                show_workout.setClass(Workout.this, showWorkout.class);
                show_workout.putExtra("Workout Type", main + " " + sub);
                startActivity(show_workout); // MAIN + Sub workout given as putExtra to show_workout activity
            }
        });

    }

    public String recommended_workout(String last_workout){
        String recommend = last_workout;
        switch(last_workout){
            case "chest" :
                recommend = "shoulder";
                break;
            case "shoulder" :
                recommend = "back";
                break;
            case "back" :
                recommend = "leg";
                break;
            case "leg" :
                recommend = "chest";
                break;
        }
        return recommend;
    }


}