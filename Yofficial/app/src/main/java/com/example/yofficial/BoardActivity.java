package com.example.yofficial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BoardActivity extends AppCompatActivity {

    String id;
    BoardItem item;
    TextView title;
    TextView info;
    TextView body;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_activity);

        item = new BoardItem("1", "\n탕수육을 왜먹냐? 그 돈으로 국밥 3그릇을 먹지", "박현우", "2018/01/01", "아무튼 국밥임");

        Intent intent = getIntent();
        id =intent.getExtras().getString("id");


        if(id.equals(item.board_id)){
            title = (TextView)findViewById(R.id.board_title);
            info = (TextView)findViewById(R.id.board_info);
            body = (TextView)findViewById(R.id.board_data);

            title.setText(item.board_title);
            info.setText(item.board_uploader + " | " + item.board_date);
            body.setText(item.board_data);
        }


    }

    public void see_comment(View v){
        Intent intent = new Intent(getApplicationContext(), CommentActivity.class);
        startActivity(intent);
    }

    public void delete_board(View v){
        this.finish();
    }
}
