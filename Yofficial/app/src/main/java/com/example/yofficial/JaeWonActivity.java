package com.example.yofficial;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.io.IOException;

public class JaeWonActivity extends YouTubeBaseActivity {

    private static final String TAG = "JaeWonActivity";


    YouTubePlayerView mYouTubePlayerView;
    Button btnPlay;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;
    YouTubePlayer.PlayerStateChangeListener mPlayerStateChangelistener;
    YouTubePlayer player;

    int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate : Starting.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jaewon);
        btnPlay = (Button)findViewById(R.id.btnPlay);
        mYouTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtubePlay);

        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "onClick : Done initializing");
                //youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
                youTubePlayer.setPlayerStateChangeListener(mPlayerStateChangelistener);
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

        mPlayerStateChangelistener = new YouTubePlayer.PlayerStateChangeListener() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onLoaded(String s) {
                player.play();
            }

            @Override
            public void onAdStarted() {

            }

            @Override
            public void onVideoStarted() {
                Log.d(TAG, "Video Started");
                showMessage("Video Started");
                player.seekToMillis(100 * 1000);

                final Handler handler = new Handler();

                Thread t = new Thread(new Runnable() {
                   Runnable stop = new Runnable(){
                       @Override
                       public void run() {
                           player.pause();
                       }
                   };


                   @Override
                   public void run() {
                       try {
                           while(player.getCurrentTimeMillis() < 110 * 1000){
                               Log.d(TAG, ""+ player.getCurrentTimeMillis());
                               Thread.sleep(1000);
                           }
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       handler.post(stop);
                       Log.d(TAG, "Stopped at time : "+player.getCurrentTimeMillis());
                   }
               });

                t.start();
            }






            @Override
            public void onVideoEnded() {

            }

            @Override
            public void onError(YouTubePlayer.ErrorReason errorReason) {

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


