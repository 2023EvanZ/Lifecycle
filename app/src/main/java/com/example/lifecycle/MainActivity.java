package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Button tLeft, tRight, bLeft, bRight;

    int countonCreate = 0;
    int countonStart = 0;
    int countonResume = 0;
    int countonPause = 0;
    int countonStop = 0;
    int countonRestart = 0;
    int countonDestroy = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("Values", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        tLeft = findViewById(R.id.top_left_button);
        tRight = findViewById(R.id.top_right_button);
        bLeft = findViewById(R.id.bottom_left_button);
        bRight = findViewById(R.id.bottom_right_button);
        tLeft.setOnClickListener(this);
        bLeft.setOnClickListener(this);
        tRight.setOnClickListener(this);
        bRight.setOnClickListener(this);
        setInitialValues();
        countonCreate++;
        storeValues();
    }

    @Override
    public void onClick(View view) {
        Button x = (Button) view;
        x.setText("" + (Integer.parseInt(x.getText().toString()) + 1));
        storeValues();
    }
    private void setInitialValues() {
        tLeft.setText(""+sharedPreferences.getInt("topleft", 0));
        tRight.setText(""+sharedPreferences.getInt("topright", 0));
        bLeft.setText(""+sharedPreferences.getInt("bottomleft", 0));
        bRight.setText(""+sharedPreferences.getInt("bottomright", 0));
        countonCreate=sharedPreferences.getInt("onCreate",0);
        countonStart=sharedPreferences.getInt("onStart",0);
        countonResume=sharedPreferences.getInt("onResume",0);
        countonPause=sharedPreferences.getInt("onPause",0);
        countonStop=sharedPreferences.getInt("onStop",0);
        countonRestart=sharedPreferences.getInt("onRestart",0);
        countonDestroy=sharedPreferences.getInt("onDestroy",0);



    }
    private void storeValues() {
        editor.putInt("topleft",Integer.parseInt(tLeft.getText().toString()));
        editor.putInt("topright",Integer.parseInt(tRight.getText().toString()));
        editor.putInt("bottomleft",Integer.parseInt(bLeft.getText().toString()));
        editor.putInt("bottomright",Integer.parseInt(bRight.getText().toString()));
        editor.putInt("onCreate",  countonCreate);
        editor.putInt("onStart",  countonStart);
        editor.putInt("onResume",  countonResume);
        editor.putInt("onPause",  countonPause);
        editor.putInt("onStop",  countonStop);
        editor.putInt("onRestart",  countonRestart);
        editor.putInt("onDestroy",  countonDestroy);
        editor.apply();
    }
    private void erase() {
        editor.remove("Values").commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        countonPause++;
        storeValues();
    }

    @Override
    protected void onStart() {
        super.onStart();
        countonStart++;
        storeValues();
    }

    @Override
    protected void onResume() {
        super.onResume();
        countonResume++;
        storeValues();
    }

    @Override
    protected void onStop() {
        super.onStop();
        countonStop++;
        storeValues();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        countonRestart++;
        storeValues();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countonDestroy++;
        storeValues();
    }
}