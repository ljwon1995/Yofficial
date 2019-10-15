package com.example.yofficial;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class DBActivity extends Activity {


    private static final String TAG = "DBActivity!";
    TextView txtView;
    phpDown task;
    String result;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        txtView = (TextView)findViewById(R.id.textView);
        task = new phpDown(){
            @Override
            protected void onPostExecute(String str) {
                super.onPostExecute(str);
                result = str;
                Log.d(TAG, "Result = " + str);
                txtView.setText(str);
            }
        };
        task.execute("http://10.210.61.234:8080/PHP_connection.php");//도메인을 실행하게되면 php가 실행되서 데이터전달이 일어난다.

    }

    public void onBackButtonClicked(View v){
        finish();
    }
}
