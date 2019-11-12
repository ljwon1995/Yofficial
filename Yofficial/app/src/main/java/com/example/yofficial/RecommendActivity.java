package com.example.yofficial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


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

        double a, b;
        ArrayList<String> result_title = new ArrayList<>();
        test = (TextView)findViewById(R.id.textView5);
        a =  list.get(0).cos_similarity(user_savory, user_sweet, user_sour, user_spicy, user_salty);
        b =  list.get(1).cos_similarity(user_savory, user_sweet, user_sour, user_spicy, user_salty);
        test.setText(a + "\n" + b);
        result_title.add(list.get(0).getFood_name());
        Intent intent = new Intent(this, Recommend_PopupActivity.class);
        intent.putStringArrayListExtra("ArrayList", result_title);
        startActivityForResult(intent, 1);

    }
}


