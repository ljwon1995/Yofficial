package com.example.yofficial;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.io.IOException;

public class YouTubeActivity extends YouTubeBaseActivity {

    private static final String TAG = "YouTubeActivity!";
    int[] start_time = {60, 120, 180, 240, 300};
    int[] end_time = {65, 125, 185, 245, 305};
    int state = 0;


    YouTubePlayerView mYouTubePlayerView;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;
    YouTubePlayer.PlayerStateChangeListener mPlayerStateChangeListener;
    YouTubePlayer.PlaybackEventListener mPlayBackEventListener;
    YouTubePlayer player;

    Button btnPlay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate : Starting.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        btnPlay = (Button)findViewById(R.id.btnPlay);
        mYouTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtubePlay);

        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "onClick : Done initializing");
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
                youTubePlayer.setPlayerStateChangeListener(mPlayerStateChangeListener);
                youTubePlayer.setPlaybackEventListener(mPlayBackEventListener);
                player = youTubePlayer;
                youTubePlayer.loadVideo("wEdoqb2CuYc");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "onClick : Fail to initializing");
            }
        };

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Onclick : Initializing YouTube Player.");
                mYouTubePlayerView.initialize(YouTubeConfig.getApiKey(), mOnInitializedListener);
            }
        });

        mPlayerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onLoaded(String s) {
            }

            @Override
            public void onAdStarted() {

            }

            @Override
            public void onVideoStarted() {
                Log.d(TAG, "Video Started");
                player.seekToMillis(start_time[state] * 1000);
            }
            @Override
            public void onVideoEnded() {
            }
            @Override
            public void onError(YouTubePlayer.ErrorReason errorReason) {

            }
        };

        mPlayBackEventListener = new YouTubePlayer.PlaybackEventListener() {
            @Override
            public void onPlaying() {

            }

            @Override
            public void onPaused() {

            }

            @Override
            public void onStopped() {

            }

            @Override
            public void onBuffering(boolean b) {

            }

            @Override
            public void onSeekTo(int i) {
                Log.d(TAG, "Seek to "+ start_time[state] + "s");
                player.play();

                final Handler handler = new Handler();
                Thread t = new Thread(new Runnable() {
                    Runnable stop = new Runnable(){
                        @Override
                        public void run() {
                            player.pause();
                        }
                    };
                    Runnable finish = new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Finished", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    };

                    Runnable searchNext = new Runnable() {
                        @Override
                        public void run() {
                            player.seekToMillis(start_time[state] * 1000);

                        }
                    };

                    @Override
                    public void run() {
                        try {
                            while(player.getCurrentTimeMillis() < end_time[state] * 1000){
                                //Log.d(TAG, ""+ player.getCurrentTimeMillis());
                                Thread.sleep(100);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.post(stop);
                        Log.d(TAG, "Stopped at time : "+player.getCurrentTimeMillis());
                        state += 1;
                        Log.d(TAG, "State = " + state);
                        if(state == 5){
                            handler.post(finish);
                        }
                        else {
                            handler.post(searchNext);
                        }



                    }
                });

                t.start();
            }
        };

    }



    public void onBackButtonClicked(View v){
        finish();
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}


