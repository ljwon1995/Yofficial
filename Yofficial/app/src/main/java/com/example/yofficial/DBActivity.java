package com.example.yofficial;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;


public class DBActivity extends Activity {


    private static final String TAG = "DBActivity!";


    EditText txtView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        txtView = findViewById(R.id.UpText);

    }


    public void onBackButtonClicked(View v){
        finish();
    }

}
