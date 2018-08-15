package com.anthonypse.videoplayerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.anthonypse.jinglz_player.JinglzPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    Button mStartButton;
    Button mPauseButton;
    Button mStopButton;

    ///The view that the user sees and (possibly) interacts with
    //We want to remove exoplayer dependencies from the main project to the library.  Needs some
    //investigation
    SimpleExoPlayerView mPlayerView;

    JinglzPlayer mPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mStartButton.setVisibility(View.INVISIBLE);
        mPlayer = new JinglzPlayer(mPlayerView, getApplicationContext());
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
        mPlayer.release();
        super.onDestroy();
    }

    private void bindViews(){
        mStartButton = findViewById(R.id.buttonStart);
        mPauseButton = findViewById(R.id.buttonPause);
        mStopButton = findViewById(R.id.buttonStop);

        mPlayerView = findViewById(R.id.videoView);
    }

    public void onStartButtonPressed( View v ){
        mStartButton.setVisibility(View.INVISIBLE);
        mPlayer.start();
    }

    public void onPauseButtonPressed(View v) {
        mStartButton.setVisibility(View.VISIBLE);
        mStartButton.setText("Resume");
        mPlayer.pause();
    }

    public void onStopButtonPressed(View v){
        mStartButton.setVisibility(View.VISIBLE);
        mStartButton.setText("Play Random");
        mPlayer.stop();
    }
}
