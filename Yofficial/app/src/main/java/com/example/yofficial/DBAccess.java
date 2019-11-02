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
                        Toast.makeText(ac.getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                });


    }

    void deleteUser(UserInfo usr){
        myRef.child("users").child(usr.getId()).removeValue().addOnSuccessListener(ac, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ac.getApplicationContext(), "Succeeded", Toast.LENGTH_SHORT).show();
                ac.finish();
            }
        }).addOnFailureListener(ac, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ac.getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void deleteUser(String userId){
        myRef.child("users").child(userId).removeValue().addOnSuccessListener(ac, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ac.getApplicationContext(), "Succeeded", Toast.LENGTH_SHORT).show();
                ac.finish();
            }
        }).addOnFailureListener(ac, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ac.getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    void addRecipe(RecipeInfo recipe){
        myRef.child("recipes").push().setValue(recipe).addOnSuccessListener(ac, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ac.getApplicationContext(), "Succeeded", Toast.LENGTH_SHORT).show();
                ac.finish();
            }
        }).addOnFailureListener(ac, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ac.getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }









}