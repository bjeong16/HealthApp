package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login_Activity_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__2);
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
        EditText height = findViewById(R.id.editTextTextHeight);
        EditText weight = findViewById(R.id.editTextTextWeight);
        EditText gender = findViewById(R.id.editTextTextGender);

        TextView ask_height = findViewById(R.id.textView2);
        TextView ask_weight = findViewById(R.id.textView3);
        TextView ask_gender = findViewById(R.id.textView4);

        ask_height.setText("Height (cm)");
        ask_weight.setText("Weight (lbs");
        ask_gender.setText("Gender (F/M");
        Button submit = findViewById(R.id.button2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String height_string = height.getText().toString();
                String weight_string = weight.getText().toString();
                String gender_string = gender.getText().toString();
                Intent result = new Intent();
                String other_info = getIntent().getStringExtra("ExtraInfo");
                String[] other_info_arr = other_info.split(" ");
                result.putExtra("QuestionResult", other_info + " " + height_string + " " + weight_string + " " + gender_string);
                setResult(Activity.RESULT_OK, result);
                finish();
            }
        });
    }

}
