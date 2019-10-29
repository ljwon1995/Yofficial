package com.example.yofficial;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class YouTubeActivity extends YouTubeBaseActivity {

    private static final int NOTHING = -1;
    private static final int PLAYING = 0;
    private static final int PAUSED = 1;
    private static final int LOADING = 2;
    private static final int LOADED = 3;
    private static final int ENDED = 4;

    private static final String TAG = "YouTubeActivity!";
    int[] start_time = {4, 25, 39, 57};
    int[] end_time = {20,38, 56, 70};

    String[] desc = {"1. 귤을 까 주세요", "2. 귤을 반으로 쪼개주세요", "3. 귤을 또 반으로 쪼개주세요.", "4. 맛있게 보이게 디피 해주세요."};



    int state = 0;
    String videoId = "B2TeCM7TrXQ";

    int videoState = -1;

    TextView tv;


    YouTubePlayerView mYouTubePlayerView;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;
    YouTubePlayer.PlayerStateChangeListener mPlayerStateChangeListener;
    YouTubePlayer.PlaybackEventListener mPlayBackEventListener;
    YouTubePlayer player = null;

    Button btnPlay;
    Button btnPause;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate : Starting.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        btnPlay = (Button)findViewById(R.id.btnPlay);
        mYouTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtubePlay);
        btnPause = findViewById(R.id.pause);
        tv = findViewById(R.id.description);

        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "onClick : Done initializing");
                player = youTubePlayer;
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
                youTubePlayer.setPlayerStateChangeListener(mPlayerStateChangeListener);
                youTubePlayer.setPlaybackEventListener(mPlayBackEventListener);
                youTubePlayer.loadVideo(videoId);
                videoState = LOADING;
                Log.d(TAG, "VideoStateChanged = " + videoState + " must be " + LOADING);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "onClick : Fail to initializing");
            }
        };

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(videoState == NOTHING){
                    Log.d(TAG, "Onclick : Initializing YouTube Player.");
                    mYouTubePlayerView.initialize(YouTubeConfig.getApiKey(), mOnInitializedListener);
                }

                if(videoState == PAUSED){
                    player.play();
                    videoState = PLAYING;
                    Log.d(TAG, "VideoStateChanged = " + videoState + " must be " + PLAYING);
                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                if(videoState == PLAYING) {
                    player.pause();
                    videoState = PAUSED;
                    Log.d(TAG, "VideoStateChanged = " + videoState + " must be " + PAUSED);
                }
            }
        });

        mPlayerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
            @Override
            public void onLoading() {
                videoState = LOADING;
                Log.d(TAG, "VideoStateChanged = " + videoState + " must be " + LOADING);
            }

            @Override
            public void onLoaded(String s) {
                videoState = LOADED;
                Log.d(TAG, "VideoStateChanged = " + videoState + " must be " + LOADED);
            }

            @Override
            public void onAdStarted() {

            }

            @Override
            public void onVideoStarted() {
                Log.d(TAG, "Video Started");
                videoState = PLAYING;
                Log.d(TAG, "VideoStateChanged = " + videoState+ " must be " + PLAYING);
                player.seekToMillis(start_time[state] * 1000);

            }
            @Override
            public void onVideoEnded() {
                videoState = ENDED;
                Log.d(TAG, "VideoStateChanged = " + videoState + " must be " + ENDED);
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
                //videoState = PAUSED;
                //Log.d(TAG, "VideoStateChanged = " + videoState + " must be " + PAUSED);
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
                videoState = PLAYING;
                Log.d(TAG, "VideoStateChanged = " + videoState + " must be " + PLAYING);
                tv.setText(desc[state]);

                final Handler handler = new Handler();
                Thread t = new Thread(new Runnable() {
                    Runnable stop = new Runnable(){
                        @Override
                        public void run() {
                            player.pause();
                            videoState = PAUSED;
                            Log.d(TAG, "VideoStateChanged = " + videoState + " must be " + PAUSED);
                        }
                    };
                    Runnable finish = new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Finished", Toast.LENGTH_LONG).show();
                            videoState = ENDED;
                            Log.d(TAG, "VideoStateChanged = " + videoState + " must be " + ENDED);
                            //finish();
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
                        if(state == 4){
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
        if(videoState != LOADING)
        finish();
    }


}


