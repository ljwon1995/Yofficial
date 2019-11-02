package com.example.yofficial;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DBAccess {
    private FirebaseDatabase database;
    private DatabaseReference myRef;


    DBAccess(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
    }

    void addUser(UserInfo usr){
        myRef.child("users").child(usr.getId()).setValue(usr);
    }









}
