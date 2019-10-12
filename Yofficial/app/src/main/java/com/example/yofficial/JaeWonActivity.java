package com.example.yofficial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;


public class JaeWonActivity extends Activity{

    private static final String TAG = "JaeWonActivity!";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate : Starting.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jaewon);

        Button youtubeTestBtn = (Button)findViewById(R.id.youtubeTestBtn);
        Button dbTestBtn = (Button)findViewById(R.id.dbTestBtn);

        youtubeTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), YouTubeActivity.class);
                startActivity(intent);
            }
        });

        dbTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DBActivity.class);
                startActivity(intent);
            }
        });

    }

    public void onBackButtonClicked(View v){
        finish();
    }

}


