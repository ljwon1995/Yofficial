package com.example.yofficial;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



public class Ingre_PopupActivity extends Activity {

    TextView rateText;
    LinearLayout rate_linear;
    ImageView first;
    ImageView second;
    ImageView third;
    ImageView fourth;
    ImageView fifth;

    int self_score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //배경 투명하게
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        setContentView(R.layout.activity_ingre_popup);

        //UI 객체생성
        rateText = (TextView)findViewById(R.id.rateText);
        first = findViewById(R.id.first_star);
        second = findViewById(R.id.second_star);
        third = findViewById(R.id.third_star);
        fourth = findViewById(R.id.fourth_star);
        fifth = findViewById(R.id.fifth_star);



        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first.setImageResource(R.drawable.filled_star);
                second.setImageResource(R.drawable.empty_star);
                third.setImageResource(R.drawable.empty_star);
                fourth.setImageResource(R.drawable.empty_star);
                fifth.setImageResource(R.drawable.empty_star);
                rateText.setText("연습이 필요");
                self_score = 1;
                System.out.println(rateText.getText());
                System.out.println(self_score);
            }
        });

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first.setImageResource(R.drawable.filled_star);
                second.setImageResource(R.drawable.filled_star);
                third.setImageResource(R.drawable.empty_star);
                fourth.setImageResource(R.drawable.empty_star);
                fifth.setImageResource(R.drawable.empty_star);
                rateText.setText("아직 부족함");
                self_score = 2;
                System.out.println(rateText.getText());
                System.out.println(self_score);
            }
        });

        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first.setImageResource(R.drawable.filled_star);
                second.setImageResource(R.drawable.filled_star);
                third.setImageResource(R.drawable.filled_star);
                fourth.setImageResource(R.drawable.empty_star);
                fifth.setImageResource(R.drawable.empty_star);
                rateText.setText("먹어줄만 함");
                self_score = 3;
                System.out.println(rateText.getText());
                System.out.println(self_score);
            }
        });

        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first.setImageResource(R.drawable.filled_star);
                second.setImageResource(R.drawable.filled_star);
                third.setImageResource(R.drawable.filled_star);
                fourth.setImageResource(R.drawable.filled_star);
                fifth.setImageResource(R.drawable.empty_star);
                rateText.setText("만족스러움");
                self_score = 4;
                System.out.println(rateText.getText());
                System.out.println(self_score);
            }
        });

        fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first.setImageResource(R.drawable.filled_star);
                second.setImageResource(R.drawable.filled_star);
                third.setImageResource(R.drawable.filled_star);
                fourth.setImageResource(R.drawable.filled_star);
                fifth.setImageResource(R.drawable.filled_star);
                rateText.setText("레시피를 마스터함");
                self_score = 5;
                System.out.println(rateText.getText());
                System.out.println(self_score);
            }
        });


    }

    //확인 버튼 클릭
    public void mOnClose(View v){
        //데이터 전달하기
        Intent intent = new Intent();
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}
