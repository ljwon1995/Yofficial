package com.example.yofficial;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentActivity extends AppCompatActivity {
    List<CommentItem> list;
    ListView listview;
    CommentAdapter adapter;
    MenuItem mSearch;
    Context c = this;

    String text;
    private EditText edit_comment;
    private final static String TAG = "VideoActivity!";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_list);

        edit_comment = (EditText)findViewById(R.id.comment_edit);


        listview = (ListView) findViewById(R.id.listview1);
        list = new ArrayList<CommentItem>();



        list.add(new CommentItem("1", "1", "박현우", "2019/11/19", "다들 ㅇㅈ이지?"));
        list.add(new CommentItem("1", "2", "이재원", "2019/11/19", "ㄴㄴ 국밥충 극혐"));
        list.add(new CommentItem("1", "3", "민준홍", "2019/11/19", "ㄹㅇ ㅋㅋ"));

        adapter = new CommentAdapter(this, list);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // 리스트 아이템 버튼 작동
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CommentActivity.this ,list.get(position). getUser_id(),Toast.LENGTH_LONG).show();

            }
        });

        // input창에 검색어를 입력시 "addTextChangedListener" 이벤트 리스너를 정의한다.
        edit_comment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                text =  edit_comment.getText().toString();
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // 리스트 아이템 버튼 작동
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(list.get(position).user_id == "박현우"){
                    list.remove(position);
                }
                else{
                    Toast.makeText(CommentActivity.this ,"본인 댓글만 삭제 가능합니다!",Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
    public void insert_comment(View v){
        Toast.makeText(CommentActivity.this ,"삽입",Toast.LENGTH_LONG).show();

        SimpleDateFormat dateFormat = new  SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault());
        Date date = new Date();
        String strDate = dateFormat.format(date);


        list.add(new CommentItem("1", "4", "민준홍", strDate, text));
        adapter.notifyDataSetChanged();
        edit_comment.setText("");
    }


}