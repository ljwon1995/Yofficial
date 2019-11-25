package com.example.yofficial;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class HyunWooActivity extends AppCompatActivity {
    RecipeInfo refo;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private Context c = this;

    private static final String TAG = "HyunWoo!";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        Bundle extras = getIntent().getExtras();
        String id = extras.getString("id");
        Log.d("HyunWoo!", id);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        myRef.child("recipes").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                Log.d(TAG, "enterDataChange");
                refo = dataSnapshot.getValue(RecipeInfo.class);
                Log.d(TAG, refo.getRecipeTitle() + " " + refo.getRecipeId());


                // drawable에 있는 이미지를 지정합니다.
                storage = FirebaseStorage.getInstance();
                storageRef = storage.getReference().child("images/" + refo.getRecipeId() +".jpg");
                storageRef.getBytes(1024 * 1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {

                        Log.d(TAG, "Succeeded");
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        BitmapDrawable img = new BitmapDrawable(getResources(), bitmap);



                        setContentView(R.layout.activity_hyunwoo);

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

                        StringBuilder ingreds = new StringBuilder();
                        for(int i = 0; i < refo.getIngredientName().size(); i++){
                            ingreds.append(refo.getIngredientName().get(i));
                            ingreds.append(refo.getIngredientAmount().get(i));
                            ingreds.append(",");
                            Log.d(TAG, "cur SB = " + ingreds.toString());
                        }

                        ingredientList.setText(ingreds.toString());


                        TextView seasoningList = (TextView)findViewById(R.id.seasoningList);

                        StringBuilder seasons = new StringBuilder();
                        for(int i = 0; i < refo.getSeasoningName().size(); i++){
                            seasons.append(refo.getSeasoningName().get(i));
                            seasons.append(refo.getSeasoningAmount().get(i));
                            seasons.append(",");
                            Log.d(TAG, "cur SB = " + ingreds.toString());
                        }

                        seasoningList.setText(seasons.toString());

                        //뷰, 버튼들의 선언부
                        ImageView imageView = (ImageView)findViewById(R.id.imageview);
                        ImageView yiconView = (ImageView)findViewById(R.id.yicon);
                        ImageView servings = (ImageView)findViewById(R.id.servings);
                        ImageView level = (ImageView)findViewById(R.id.level);
                        ImageView duration = (ImageView)findViewById(R.id.duration);
                        ImageView youtubeUrl = (ImageView) findViewById(R.id.youtubeUrl);



                        yiconView.setImageResource(R.drawable.yicon);
                        servings.setImageResource(R.drawable.servings);
                        level.setImageResource(R.drawable.level);
                        duration.setImageResource(R.drawable.duration);


                        Log.d(TAG, "URL : " + refo.getYoutubeUrl());

                        youtubeUrl.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), YouTubeActivity.class);
                                intent.putExtra("url", refo.getYoutubeUrl());
                                intent.putExtra("startTime", refo.getStartTime());
                                intent.putExtra("endTime", refo.getEndTime());
                                intent.putExtra("stepDesc", refo.getStepDescrib());
                                startActivity(intent);
                            }
                        });

                        imageView.setImageDrawable(img);


                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {



                        setContentView(R.layout.activity_hyunwoo);
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

                        StringBuilder ingreds = new StringBuilder();
                        for(int i = 0; i < refo.getIngredientName().size(); i++){
                            ingreds.append(refo.getIngredientName().get(i));
                            ingreds.append(refo.getIngredientAmount().get(i));
                            ingreds.append(",");
                            Log.d(TAG, "cur SB = " + ingreds.toString());
                        }

                        ingredientList.setText(ingreds.toString());


                        TextView seasoningList = (TextView)findViewById(R.id.seasoningList);

                        StringBuilder seasons = new StringBuilder();
                        for(int i = 0; i < refo.getSeasoningName().size(); i++){
                            seasons.append(refo.getSeasoningName().get(i));
                            seasons.append(refo.getSeasoningAmount().get(i));
                            seasons.append(",");
                            Log.d(TAG, "cur SB = " + ingreds.toString());
                        }

                        seasoningList.setText(seasons.toString());

                        //뷰, 버튼들의 선언부
                        ImageView imageView = (ImageView)findViewById(R.id.imageview);
                        ImageView yiconView = (ImageView)findViewById(R.id.yicon);
                        ImageView servings = (ImageView)findViewById(R.id.servings);
                        ImageView level = (ImageView)findViewById(R.id.level);
                        ImageView duration = (ImageView)findViewById(R.id.duration);
                        ImageView youtubeUrl = (ImageView) findViewById(R.id.youtubeUrl);



                        yiconView.setImageResource(R.drawable.yicon);
                        servings.setImageResource(R.drawable.servings);
                        level.setImageResource(R.drawable.level);
                        duration.setImageResource(R.drawable.duration);


                        youtubeUrl.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), YouTubeActivity.class);

                                intent.putExtra("url", refo.getYoutubeUrl());
                                intent.putExtra("startTime", refo.getStartTime());
                                intent.putExtra("endTime", refo.getEndTime());
                                intent.putExtra("stepDesc", refo.getStepDescrib());
                                startActivity(intent);
                            }
                        });

                        Log.d(TAG, "Failed");
                        Drawable img = ContextCompat.getDrawable(c, R.drawable.fail);
                        imageView.setImageDrawable(img);



                    }
                });



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



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





    }
}
