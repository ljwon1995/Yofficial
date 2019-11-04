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
    String[] dbIng = {"소고기/200g", "양파/반개", "마늘/5개", "청양고추/1개", "피망/1개"};
    String[] ingredients;
    String[] dbSsn = {"올리고당/스푼", "간장/2스푼", "고춧가루/3스푼", "물/2-3스푼"};
    String[] seasonings;
    String[] dbStartTime = {"2/30/20","0/22/34","0/20/01"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hyunwoo);

        refo.setRecipeTitle("asdf");
        refo.setRecipeSubTitle("덮밥은 진리다");
        refo.setIntroRecipe("누구나 쉽게! 맛있는 차슈덮밥");
        refo.setServings("6인분 이상");
        refo.setDifficulty("다이아몬드");
        refo.setDuraTime("2시간 이상");


        /*
        //db에서 쏴 준 정보 임시 문자열 테이블에 저장, 나중에 db연동 시 수정
        ingredients = new String[dbIng.length];
        for (int i = 0; i < ingredients.length; i++) {
            ingredients[i] = dbIng[i];
        }
        refo.setIngredient(ingredients);

        seasonings = new String[dbSsn.length];
        for (int i = 0; i < seasonings.length; i++) {
            seasonings[i] = dbSsn[i];
        }
        refo.setSeasoning(seasonings);

         */


        //db정보가 있는 RecipeInfo클래스에서 정보 받아오기
        TextView recipeTitle = (TextView)findViewById(R.id.title);
        recipeTitle.setText(refo.getRecipeTitle());
        TextView recipeSubTitle = (TextView)findViewById(R.id.sub_title);
        recipeSubTitle.setText(refo.getRecipeSubTitle());
        TextView introEdit = (TextView) findViewById(R.id.introRecipe);
        introEdit.setText(refo.getIntroRecipe());
        TextView serveNum = (TextView)findViewById(R.id.serveNum);
        serveNum.setText(refo.getServings());
        TextView difficulty = (TextView)findViewById(R.id.difficulty);
        difficulty.setText(refo.getDifficulty());
        TextView duraTime = (TextView)findViewById(R.id.duraTime);
        duraTime.setText(refo.getDuraTime());
        TextView ingredientList = (TextView)findViewById(R.id.ingredientList);
        //ingredientList.setText(refo.getIngredient());
        TextView seasoningList = (TextView)findViewById(R.id.seasoningList);
        //seasoningList.setText(refo.getSeasoning());

        //뷰, 버튼들의 선언부
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


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreateRecipeActivity.class);
                startActivity(intent);
            }
        });


    }
}
