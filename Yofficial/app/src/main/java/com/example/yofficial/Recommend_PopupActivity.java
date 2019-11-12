package com.example.yofficial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Recommend_PopupActivity extends Activity {
    TextView txtText;
    List<String> list;
    ListView listview;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_recommend_pupup);

        //UI 객체생성
        txtText = (TextView)findViewById(R.id.select1);

        //데이터 가져오기
        Intent intent = getIntent();
        ArrayList<String> ReceiveArr = intent.getStringArrayListExtra("ArrayList");
        txtText.setText(ReceiveArr.get(0));
    }


    public void onSelect1(View view){
        Intent intent = new Intent(
                getApplicationContext(), // 현재 화면의 제어권자
                VideoListActivity.class); // 다음 넘어갈 클래스 지정
        //intent.putExtra();
        startActivity(intent); // 다음 화면으로 넘어간다

    }

    //확인 버튼 클릭
    public void mOnClose(View v){
        /*
        //데이터 전달하기
        Intent intent = new Intent();
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);

         */
        Intent intent = new Intent(
                getApplicationContext(), // 현재 화면의 제어권자
                VideoListActivity.class); // 다음 넘어갈 클래스 지정
        //intent.putExtra();
        startActivity(intent); // 다음 화면으로 넘어간다

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


    /*
    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
     */
}
