package com.anthonypse.videoplayerdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.anthonypse.jinglz_player.JinglzPlayerActivity;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    Button mStartPlayerActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        bindViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }
    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    private void bindViews(){
        mStartPlayerActivity = findViewById(R.id.buttonStart);
    }

    public void onStartButtonPressed( View v ){
        Intent intent = new Intent(this, JinglzPlayerActivity.class);
        startActivity(intent);
    }

}
