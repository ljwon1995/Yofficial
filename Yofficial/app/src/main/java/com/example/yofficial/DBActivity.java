package com.example.yofficial;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class DBActivity extends Activity {


    private static final String TAG = "DBActivity!";


    EditText txtView;
    UserInfo usr;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        txtView = findViewById(R.id.UpText);


        usr = new UserInfo();
        usr.setId("ljwon1995");
        usr.setPw("asdf");
        usr.setStatus("Chef");
        usr.setLevel(400);
        usr.setExp(30);











    }


    public void onBackButtonClicked(View v){

    }

}
