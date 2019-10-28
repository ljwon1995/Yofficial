package com.example.yofficial;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class VideoList extends AppCompatActivity {
    List<VideoItem> list;
    ListView listview;
    VideoAdapter adapter;
    EditText editsearch;
    MenuItem mSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v_list);

        //editsearch = (EditText)findViewById(R.id.editSearch);

        listview = (ListView) findViewById(R.id.listview1);
        list = new ArrayList<VideoItem>();


        list.add(new VideoItem(ContextCompat.getDrawable(this, R.drawable.ab), "3분만에 만드는 맛있는 수제햄버거", "\n맥도날드", "\n24212 views"));
        list.add(new VideoItem(ContextCompat.getDrawable(this, R.drawable.aa), "delicious gyudon", "\n홍길동", "\n84213 views"));

        adapter = new VideoAdapter(this, list);
        listview.setAdapter(adapter);

        /* editsearch 사용창
        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                String text = editsearch.getText().toString()
                        .toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                VideoItem item = (VideoItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getV_title() ;
                String descStr = item.getV_uploader() ;

                // TODO : use item data.
            }
        }) ; */

    }


    //메뉴 생성하는 onCreateOptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        //search_menu.xml 등록
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        mSearch=menu.findItem(R.id.search);

        //메뉴 아이콘 클릭했을 시 확장, 취소했을 시 축소
        mSearch.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;
            }
        });


        //menuItem을 이용해서 SearchView 변수 생성
        SearchView sv=(SearchView)mSearch.getActionView();
        //확인버튼 활성화
        sv.setSubmitButtonEnabled(true);

        //SearchView의 검색 이벤트
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            //검색버튼을 눌렀을 경우
            @Override
            public boolean onQueryTextSubmit(String query) {
                //   TextView text = (TextView)findViewById(R.id.txtresult);
                //   text.setText(query + "를 검색합니다.");
                adapter.filter(query);
                return true;
            }

            //텍스트가 바뀔때마다 호출
            @Override
            public boolean onQueryTextChange(String newText) {
                //TextView text = (TextView)findViewById(R.id.txtsearch);
                //text.setText("검색식 : "+newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int curId = item.getItemId();
        switch(curId) {
            case R.id.menu_refresh:
                Toast.makeText(this, "새로고침 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_search:
                Toast.makeText(this, "검색 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_settings:
                Toast.makeText(this, "설정 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
