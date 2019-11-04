package com.example.yofficial;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


public class DBActivity extends Activity {


    private static final String TAG = "DBActivity!";
    DBAccess da = new DBAccess(this);

    UserInfo usr;
    RecipeInfo recipe;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);


        recipe = new RecipeInfo();

        recipe.setRecipeTitle("존스페이버릿");
        recipe.setRecipeSubTitle("파파존스존스");
        recipe.setIntroRecipe("어허허허");
        recipe.setServings("5");
        recipe.setDifficulty("Silver");
        recipe.setDuraTime("30");
        recipe.setMainIngredient("소고기");
        recipe.setType("pizza");
        recipe.setFeature("feature");

        HashMap<String, String> ing = new HashMap<>();
        ing.put("소고기", "200g");
        ing.put("빵", "100g");

        //recipe.setIngredient(ing);


        HashMap<String, String> sea = new HashMap<>();
        sea.put("갈릭디핑소", "50g");
        sea.put("토마토소스", "100g");
       // recipe.setSeasoning(sea);
        recipe.setYoutubeUrl("xmlss");

        ArrayList<String> st = new ArrayList<>();
        st.add("100");
        st.add("120");
        recipe.setStartTime(st);
        ArrayList<String> et = new ArrayList<>();
        et.add("110");
        et.add("130");
        recipe.setEndTime(et);
        recipe.setImgsrc("aaaa");

        ArrayList<String> des = new ArrayList<>();
        st.add("1. 귤을 까주세요");
        st.add("2. 귤을 반으로 짤라주세요");
        recipe.setStepDescrib(des);

        da.addRecipe(recipe);




    }


    public void onBackButtonClicked(View v){

    }

}
