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
    DatabaseReference myRef = database.getReference();
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


        myRef.child("users").child(usr.getId()).setValue(usr);








        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "Changed");
                UserInfo value = dataSnapshot.getValue(UserInfo.class);
                Log.d(TAG, value.printUser());
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
