package com.example.yofficial;

import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

public class CommunityActivity extends AppCompatActivity {
    List<BoardItem> list;
    ListView listview;
    BoardAdapter adapter;
    MenuItem mSearch;
    Context c = this;

    private ArrayList<BoardItem> arraylist;
    private EditText board_search;
    private final static String TAG = "VideoActivity!";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_list);

        board_search = (EditText)findViewById(R.id.board_search);


        listview = (ListView) findViewById(R.id.listview1);
        list = new ArrayList<BoardItem>();

        arraylist = new ArrayList<BoardItem>();


        list.add(new BoardItem("1", "\n탕수육을 왜먹냐? 그 돈으로 국밥 3그릇을 먹지", "박현우", "2018/01/01", "아무튼 국밥임"));
        list.add(new BoardItem("2", "\n국밥을 왜먹냐? 그 돈으로 123을 가겠다", "이재원", "2018/01/01","123이 최고임"));
        arraylist.addAll(list);

        adapter = new BoardAdapter(this, list);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // 리스트 버튼 작동
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), BoardActivity.class);
                String board_id= list.get(position).getBoard_id();
                intent.putExtra("id", board_id);
                startActivity(intent);
            }
        });

        // input창에 검색어를 입력시 "addTextChangedListener" 이벤트 리스너를 정의한다.
        board_search.addTextChangedListener(new TextWatcher() {
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
                String text;
                text =  board_search.getText().toString();
                search(text);
            }
        });

    }

    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        list.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            list.addAll(arraylist);
        }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < arraylist.size(); i++)
            {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (arraylist.get(i).board_title.toLowerCase().contains(charText))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    list.add(arraylist.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }

    public void make_board(View v){
        Intent intent = new Intent(getApplicationContext(), BoardMakeActivity.class);
        startActivity(intent);
    }

}
