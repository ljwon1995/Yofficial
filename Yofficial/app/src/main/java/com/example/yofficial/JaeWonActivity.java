package com.example.yofficial;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
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

public class JaeWonActivity extends YouTubeBaseActivity {

    private static final String TAG = "JaeWonActivity";


    YouTubePlayerView mYouTubePlayerView;
    Button btnPlay;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;


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
    }



    public void onBackButtonClicked(View v){
        finish();
    }

    public void onVideoBtnClicked(View v)  {
        Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();

    }







}
