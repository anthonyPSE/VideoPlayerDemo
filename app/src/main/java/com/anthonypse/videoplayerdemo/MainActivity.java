package com.anthonypse.videoplayerdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.anthonypse.jinglz_player.JinglzPlayerActivity;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    Button mStartPlayerActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Log.d(TAG, "bindViews");
        mStartPlayerActivity = findViewById(R.id.buttonStart);
    }

    public void onStartButtonPressed( View v ){
        Log.d(TAG, "onStartButtonPressed");
        Intent intent = new Intent(getApplicationContext(), JinglzPlayerActivity.class);
        startActivity(intent);
    }

}
