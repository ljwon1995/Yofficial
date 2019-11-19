package com.example.yofficial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardMakeActivity extends AppCompatActivity {

    private EditText edit_title;
    private EditText edit_body;

    BoardItem item;
    String b_id = "123";
    String b_uploader ="박현우";

    String title;
    String body;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_board_activity);



        edit_title = (EditText)findViewById(R.id.board_t_input);
        edit_body = (EditText)findViewById(R.id.board_b_input);

        title = edit_title.getText().toString();
        body = edit_body.getText().toString();

    }

    public void upload_board(View v){
        SimpleDateFormat dateFormat = new  SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault());
        Date date = new Date();
        String strDate = dateFormat.format(date);

        item = new BoardItem(b_id,title, b_uploader, strDate, body);
    }
}
