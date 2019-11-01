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
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("User");
    UserInfo usr;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        txtView = findViewById(R.id.UpText);

        Log.d(TAG, "IN");
        usr = new UserInfo();
        usr.setId("ljwon1995");
        usr.setPw("asdf");
        usr.setStatus("Chef");
        usr.setLevel(4);
        usr.setExp(30);
        myRef.setValue(usr);
        Log.d(TAG, "Set!");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "Changed");
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






    }


    public void onBackButtonClicked(View v){
        usr.setExp(40);
        myRef.setValue(usr);
    }

}
