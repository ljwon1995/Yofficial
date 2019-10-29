package com.example.yofficial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class JoonHongActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joonhong);
    }

    // onButton1Clicked에 대한 로직

    public void onButton1Clicked(View view){
        // LENGTH_LONG : 길게 화면에 나타남
        // LENGTH_SHORT : 짧게 화면에 나타남
        Toast.makeText(getApplicationContext(), "버튼이 눌렸습니다!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(
                getApplicationContext(), // 현재 화면의 제어권자
                VideoListActivity.class); // 다음 넘어갈 클래스 지정
        //intent.putExtra();
        startActivity(intent); // 다음 화면으로 넘어간다

    }

}
