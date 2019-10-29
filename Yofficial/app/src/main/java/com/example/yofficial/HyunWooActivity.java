package com.example.yofficial;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HyunWooActivity extends AppCompatActivity {
    RecipeInfo refo = new RecipeInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hyunwoo);

        refo.setIntroRecipe("누구나 쉽게! 맛있는 차슈덮밥");

        TextView introEdit = (TextView) findViewById(R.id.introRecipe);
        introEdit.setText(refo.getIntroRecipe());
        ImageView imageView = (ImageView)findViewById(R.id.imageview);
        ImageView yiconView = (ImageView)findViewById(R.id.yicon);
        ImageView servings = (ImageView)findViewById(R.id.servings);
        ImageView level = (ImageView)findViewById(R.id.level);
        ImageView duration = (ImageView)findViewById(R.id.duration);
        ImageView youtubeUrl = (ImageView) findViewById(R.id.youtubeUrl);
        Button button = (Button) findViewById(R.id.create);


        // drawable에 있는 이미지를 지정합니다.
        imageView.setImageResource(R.drawable.moon);
        yiconView.setImageResource(R.drawable.yicon);
        servings.setImageResource(R.drawable.servings);
        level.setImageResource(R.drawable.level);
        duration.setImageResource(R.drawable.duration);
        youtubeUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), YouTubeActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreateRecipeActivity.class);
                startActivity(intent);
            }
        });


    }
}
