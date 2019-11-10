package com.example.yofficial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;


public class Refreg_PopupActivity extends Activity {

    TextView txtText;
    List<VideoItem> list;
    ListView listview;
    VideoAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_refreg_popup);

        //UI 객체생성
        txtText = (TextView)findViewById(R.id.pop_input_text);

        //데이터 가져오기
        Intent intent = getIntent();
        ArrayList<String> ReceiveArr = intent.getStringArrayListExtra("ArrayList"); //어레이 리스트 받아옴 (선택된 재료들)

        txtText.setText("선택된 재료\n" );
        for(int i=0;i<ReceiveArr.size();i++){ // 어레이리스트 출력
            txtText.append("[" + ReceiveArr.get(i) + "] ");
        }


        // 팝업내 리스트뷰
        listview = (ListView) findViewById(R.id.pop_listView);
        list = new ArrayList<VideoItem>();

        /*
        list.add(new VideoItem(ContextCompat.getDrawable(this, R.drawable.ab), "3분만에 만드는 맛있는 수제햄버거", "\n맥도날드", "\n24212 views"));
        list.add(new VideoItem(ContextCompat.getDrawable(this, R.drawable.aa), "delicious gyudon", "\n홍길동", "\n84213 views"));
        list.add(new VideoItem(ContextCompat.getDrawable(this, R.drawable.citrus_image), "Cuisse de grenouille", "\n이재원", "\n11views"));
*/

        adapter = new VideoAdapter(this, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // 리스트 아이템 버튼 작동
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if("delicious gyudon" == list.get(position). getV_title()){
                    Intent intent = new Intent(
                            getApplicationContext(), // 현재 화면의 제어권자
                            HyunWooActivity.class); // 다음 넘어갈 클래스 지정
                    //intent.putExtra();
                    startActivity(intent); // 다음 화면으로 넘어간다
                }
                if("Cuisse de grenouille" == list.get(position). getV_title()){
                    Intent intent = new Intent(
                            getApplicationContext(), // 현재 화면의 제어권자
                            CitrusActivity.class); // 다음 넘어갈 클래스 지정
                    //intent.putExtra();
                    startActivity(intent); // 다음 화면으로 넘어간다
                }
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
