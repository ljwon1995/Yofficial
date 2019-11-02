package com.example.yofficial;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DBAccess {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private Activity ac;

    DBAccess(Activity activity){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        ac = activity;
    }

    void addUser(UserInfo usr){
        myRef.child("users").child(usr.getId()).setValue(usr).addOnSuccessListener(ac, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ac.getApplicationContext(), "Succeeded", Toast.LENGTH_SHORT).show();
                ac.finish();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ac.getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });


    }









}
