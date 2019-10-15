package com.example.yofficial;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;


public class DBActivity extends Activity {


    private static final String TAG = "DBActivity!";
    private static final String INSERT = "1";
    private static final String SELECT = "2";
    TextView txtView;
    String result;
    SendDataToPHP sender;
    String IPAdress = "http:/172.30.1.42:8080/GetValue.php";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        txtView = (TextView)findViewById(R.id.textView);


        sender = new SendDataToPHP(){
            @Override
            protected void onPostExecute(String str) {
                super.onPostExecute(str);
                Log.d(TAG, "Result = " + str);
                result = str;
                txtView.setText(result);
            }
        };
        sender.makeQuery(SELECT, "select * from person");
        sender.execute(IPAdress);

        sender = new SendDataToPHP(){
            @Override
            protected void onPostExecute(String str) {
                super.onPostExecute(str);
                Log.d(TAG, "Result = " + str);
                result = str;
                txtView.setText(result);
            }
        };


        sender.makeQuery(INSERT, "insert into person values('jaewon')");
        sender.execute(IPAdress);

        sender = new SendDataToPHP(){
            @Override
            protected void onPostExecute(String str) {
                super.onPostExecute(str);
                Log.d(TAG, "Result = " + str);
                result = str;
                txtView.setText(result);
            }
        };

        sender.makeQuery(SELECT, "select * from person");
        sender.execute(IPAdress);


        sender = new SendDataToPHP(){
            @Override
            protected void onPostExecute(String str) {
                super.onPostExecute(str);
                Log.d(TAG, "Result = " + str);
                result = str;
                txtView.setText(result);
            }
        };


        sender.makeQuery(INSERT, "insert into person values('yoom')");
        sender.execute(IPAdress);

        sender = new SendDataToPHP(){
            @Override
            protected void onPostExecute(String str) {
                super.onPostExecute(str);
                Log.d(TAG, "Result = " + str);
                result = str;
                txtView.setText(result);
            }
        };

        sender.makeQuery(SELECT, "select * from person");
        sender.execute(IPAdress);




        sender = new SendDataToPHP(){
            @Override
            protected void onPostExecute(String str) {
                super.onPostExecute(str);
                Log.d(TAG, "Result = " + str);
                result = str;
                txtView.setText(result);
            }
        };


        sender.makeQuery(INSERT, "insert into person values('hyunwoo')");
        sender.execute(IPAdress);

        sender = new SendDataToPHP(){
            @Override
            protected void onPostExecute(String str) {
                super.onPostExecute(str);
                Log.d(TAG, "Result = " + str);
                result = str;
                txtView.setText(result);
            }
        };

        sender.makeQuery(SELECT, "select * from person");
        sender.execute(IPAdress);


    }


    public void onBackButtonClicked(View v){
        finish();
    }

}
