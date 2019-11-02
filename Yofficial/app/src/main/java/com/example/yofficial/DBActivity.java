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
    DBAccess da = new DBAccess(this);

    UserInfo usr;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);


        usr = new UserInfo();
        usr.setId("3457may1");
        usr.setPw("asdf");
        usr.setStatus("Cook");
        usr.setLevel(30);
        usr.setExp(30);

        da.addUser(usr);
        da.deleteUser("ljwon19951");


    }


    public void onBackButtonClicked(View v){

    }

}
