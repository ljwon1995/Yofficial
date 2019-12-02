package com.example.yofficial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;


public class RecommendActivity extends AppCompatActivity {

    ArrayList<RecommendData> list = new ArrayList<>();
    int user_savory;
    int user_sweet;
    int user_sour;
    int user_spicy;
    int user_salty;
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        getSupportActionBar().setElevation(0);

        TextView savory_value1 = (TextView)findViewById(R.id.savory_value);
        TextView sweet_value1 = (TextView)findViewById(R.id.sweet_value);
        TextView sour_value1 = (TextView)findViewById(R.id.sour_value);
        TextView spicy_value1 = (TextView)findViewById(R.id.spicy_value);
        TextView salty_value1 = (TextView)findViewById(R.id.salty_value);

        list.add(new RecommendData("비빔밥", 53, 68, 59, 78, 42, 43));
        list.add(new RecommendData("불고기", 85, 74, 30, 48, 37, 45));
        list.add(new RecommendData("안동찜닭", 75, 74, 30, 48, 37, 45));
        list.add(new RecommendData("돼지갈비", 75, 74, 30, 44, 37, 45));

        SeekBar sb_savory  = (SeekBar) findViewById(R.id.seekBar_savory);
        sb_savory.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar seekBar) { }

            public void onStartTrackingTouch(SeekBar seekBar) { }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                savory_value1.setText(String.valueOf(progress));
                user_savory = progress;
            }
        });

        SeekBar sb_sweet  = (SeekBar) findViewById(R.id.seekBar_sweet);
        sb_sweet.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) { }

            public void onStartTrackingTouch(SeekBar seekBar) { }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sweet_value1.setText(String.valueOf(progress));
                user_sweet = progress;
            }
        });

        SeekBar sb_sour  = (SeekBar) findViewById(R.id.seekBar_sour );
        sb_sour.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) { }

            public void onStartTrackingTouch(SeekBar seekBar) { }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sour_value1.setText(String.valueOf(progress));
                user_sour = progress;
            }
        });

        SeekBar sb_spicy  = (SeekBar) findViewById(R.id.seekBar_spicy );
        sb_spicy.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) { }

            public void onStartTrackingTouch(SeekBar seekBar) { }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                spicy_value1.setText(String.valueOf(progress));
                user_spicy = progress;
            }
        });

        SeekBar sb_salty = (SeekBar) findViewById(R.id.seekBar_salty );
        sb_salty.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) { }

            public void onStartTrackingTouch(SeekBar seekBar) { }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                salty_value1.setText(String.valueOf(progress));
                user_salty = progress;
            }
        });

    }

    public void onButtonClicked(View view){

        ArrayList<Double> arr = new ArrayList<>();
        ArrayList<Integer> arr_n = new ArrayList<>();
        int first = 0, twice = 0, third = 0;
        ArrayList<String> result_title = new ArrayList<>();
       // test = (TextView)findViewById(R.id.textView5);

        for(int i = 0; i < list.size(); i++){
             arr.add(list.get(i).cos_similarity(user_savory, user_sweet, user_spicy, user_sour, user_salty));
             arr_n.add(i);
        }

        for(int i = 0 ; i < arr.size() ; i ++) {
            for(int j = 0 ; j < arr.size() -i -1 ; j ++) {
                if(arr.get(j)<arr.get(j+1)) {
                    double b;

                    b = arr.get(j);
                    arr.set(j, arr.get(j+1));
                    arr.set(j+1, b);

                    int idx = arr_n.get(j);
                    arr_n.set(j, arr_n.get(j+1));
                    arr_n.set(j+1, idx);
                }
            }
        }

        Iterator iterator = arr.iterator();
        while (iterator.hasNext()) {
            double element = (double) iterator.next();
            System.out.println(element);
        }

        Iterator iterator1 = arr_n.iterator();
        while (iterator1.hasNext()) {
            Integer element = (Integer) iterator1.next();
            System.out.println(element);
        }
        result_title.add(list.get(arr_n.get(0)).food_name);
        result_title.add(list.get(arr_n.get(1)).food_name);
        result_title.add(list.get(arr_n.get(2)).food_name);
        Intent intent = new Intent(this, Recommend_PopupActivity.class);
        intent.putStringArrayListExtra("ArrayList", result_title);
        startActivityForResult(intent, 1);

    }
}


