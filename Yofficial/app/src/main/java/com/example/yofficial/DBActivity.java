package com.example.yofficial;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class DBActivity extends Activity {


    private static final String TAG = "DBActivity!";
    TextView txtView;
    phpDown task;
    String result;
    SendDataToPHP sender;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        txtView = (TextView)findViewById(R.id.textView);

        /*
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

         */

        sender = new SendDataToPHP(){
            @Override
            protected void onPostExecute(String str) {
                super.onPostExecute(str);
                Log.d(TAG, "Result = " + str);
                result = str;
                txtView.setText(result);
            }
        };

        sender.execute("http:/172.30.1.42:8080/GetValue.php");


    }


    public void onBackButtonClicked(View v){
        finish();
    }

}
