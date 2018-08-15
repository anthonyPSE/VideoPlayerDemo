package com.anthonypse.jinglz_player;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by Anthony Osteen on 8/15/2018.
 * Plays a random video.  If you stop and restart then it plays another random video.
 */
public class JinglzPlayer {
    private static String TAG = "JinglzPlayer";
    private SimpleExoPlayerView mPlayerView;
    private Context mContext;

    //private CountdownTimer timerthingy;

    public JinglzPlayer(@NonNull SimpleExoPlayerView v, @NonNull Context context ) {
        mPlayerView = v;

        //Force it to false, you don't want the end-user scrubbing to the end of the video, lol.
        mPlayerView.setUseController(false);
        mContext = context;

        initPlayer();
    }

    //Call this to release resources used by this class.
    public void release(){
        mPlayerView.getPlayer().release();
    }

    private void initPlayer(){
        if(mPlayerView.getPlayer() != null ){
            mPlayerView.getPlayer().release();
        }

        // 1. Create a default TrackSelector
        Handler mainHandler = new Handler();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        DefaultTrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        // 2. Create the player
        mPlayerView.setPlayer( ExoPlayerFactory.newSimpleInstance(mContext, trackSelector));

        //Plays as soon as the media is ready to play back;
        mPlayerView.getPlayer().setPlayWhenReady(true);
        mPlayerView.getPlayer().prepare(prepareMediaSource());
    }

    /**
     * Here we need to determine what we will be showing in the media player.
     * We need to show the media source where the video we want to show is located,
     * We will also tweak whatever buffering options we need tweaked while we're at it
     * because this is probably going to be used on all kinds of networks, fast and slow
     * @return the fully prepared media source.
     */
    private MediaSource prepareMediaSource() {
        // Measures bandwidth during playback. Can be null if not required.
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(mContext,
                Util.getUserAgent(mContext, "BaseAndroidProject"), bandwidthMeter);
        // This is the MediaSource representing the media to be played.
        Uri contentUri = getContentUri();

        return new HlsMediaSource.Factory(dataSourceFactory)
                .createMediaSource(contentUri);
    }

    /**
     * Will provide a URI
     * @return the URI of the content we'll be playing
     */
    private Uri getContentUri(){
        //TODO: eventually we'll need a 'get ad' function or something that will provide for us the appropriate adUrl
        //For now though, we'll hard code it to just this video

        List<String> stupidURIlis = new ArrayList<>();
        stupidURIlis.add("https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8");
        stupidURIlis.add("https://dev.content.jinglz.net/badgers-1518850635939/hls/index.m3u8");
        stupidURIlis.add("http://devimages.apple.com/samplecode/adDemo/ad.m3u8");
        int random = getRandomNumber(0, stupidURIlis.size()-1);
        return Uri.parse(stupidURIlis.get(random));
    }

    /**
     * This function is not meant to be here in the final version of the app.  It's just to
     * facilitate getting dummy data.
     * @param min min acceptable value
     * @param max min acceptable value
     * @return a random number between min and max, inclusive
     */
    @Deprecated
    private int getRandomNumber(int min,int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }

    /**
     * Stops playback and releases allocated resources.
     */
    @Deprecated //Should actually be private
    public void stop(){
        try {
            mPlayerView.getPlayer().stop();
            mPlayerView.getPlayer().release();
            mPlayerView.setPlayer(null);
        } catch( NullPointerException e ){
            Log.d(TAG, "The video player is null.  Probably needs to be re-initialized");
            //We don't need to do anything here.
        } catch (Exception e){
            Log.d(TAG, "Unknown Exception.  Idk what to do about it yet.");
        }
    }

    /**
     * Pauses playback
     */
    @Deprecated //Should actually be private
    public void pause(){
        try {
            mPlayerView.getPlayer().setPlayWhenReady(false);
        } catch( NullPointerException e ){
            Log.d(TAG, "The video player is null.  Probably needs to be re-initialized");
            //We don't need to do anything here.
        } catch (Exception e){
            Log.d(TAG, "Unknown Exception.  Idk what to do about it yet.");
        }
    }

    /**
     * resumes or starts playback
     */
    @Deprecated //Should actually be private
    public void start(){
        try {
            mPlayerView.getPlayer().setPlayWhenReady(true);
        } catch (NullPointerException e){
            initPlayer();
            start();
        } catch (Exception f) {
            //I don't know what else could have happened here.
            Log.d(this.getClass().getName(), "You've met with a terrible fate, haven't you?");
        }
    }

    /**
     * This function should be called any time a good signal is returned from the backend
     * after processing face detection and whatnot.
     */
    private void onGoodSignal(){
        //timerthingy.reset();
        if( mPlayerView.getPlayer().getPlayWhenReady() == false ) {
            mPlayerView.getPlayer().setPlayWhenReady(true);
        }
    }

    /**
     * If it's been a while since you've received a good face result from the server, it's
     * probably time to handle that.  Determine if the connection
     */
    private void onTimeOut(){
        //Check to see if the connection is still good.
        //if it is, then that probably means the user isn't looking at their phone and
    }
}
