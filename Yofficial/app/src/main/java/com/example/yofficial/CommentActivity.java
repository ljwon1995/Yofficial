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
    BoardItem board;
    ArrayList<CommentItem> list;
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

        board = new BoardItem("1", "\n탕수육을 왜먹냐? 그 돈으로 국밥 3그릇을 먹지", "박현우", "2018/01/01", "아무튼 국밥임");

        edit_comment = (EditText)findViewById(R.id.comment_edit);

        listview = (ListView) findViewById(R.id.listview1);
        board.c_list = new ArrayList<CommentItem>();
        board.c_list.add(new CommentItem("박현우", "2018/01/01", "\n다들 인정하지?"));
        board.c_list.add(new CommentItem("이재원", "2018/01/02", "\nㄴㄴ 국밥충 극혐임"));
        board.c_list.add(new CommentItem("민준홍", "2018/01/02", "\nㄹㅇ ㅋㅋ"));

        list =  board.c_list;

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


        list.add(new CommentItem("민준홍", strDate, text));
        adapter.notifyDataSetChanged();
        edit_comment.setText("");
    }


}