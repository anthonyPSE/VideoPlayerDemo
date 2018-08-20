package com.anthonypse.jinglz_player;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

/**
 * Created by Anthony Osteen on 8/17/2018.
 */
public class JinglzPlayerActivity extends AppCompatActivity{
    private static String TAG = "JinglzPlayerActivity";
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
        setContentView(R.layout.activity_player);

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
    protected void onDestroy() {
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
