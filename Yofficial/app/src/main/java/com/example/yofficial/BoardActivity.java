package com.example.yofficial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BoardActivity extends AppCompatActivity {

    String id;
    BoardItem item;
    TextView title;
    TextView info;
    TextView body;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private BoardActivity ba = this;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_activity);


        Intent intent = getIntent();
        id =intent.getExtras().getString("id");



        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        myRef.child("boards").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                item = dataSnapshot.getValue(BoardItem.class);
                title = (TextView)findViewById(R.id.board_title);
                info = (TextView)findViewById(R.id.board_info);
                body = (TextView)findViewById(R.id.board_data);
                title.setText(item.board_title);
                info.setText(item.board_uploader + " | " + item.board_date);
                body.setText(item.board_data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    public void see_comment(View v){
        Intent intent = new Intent(getApplicationContext(), CommentActivity.class);
        intent.putExtra("id", item.board_id);
        startActivity(intent);
    }

    public void delete_board(View v){
        mAuth = FirebaseAuth.getInstance();
        String userID = mAuth.getCurrentUser().getEmail().split("@")[0];

        if(item.board_uploader.compareTo(userID) == 0){

            myRef.child("boards").child(item.board_id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    myRef.child("comments").child(item.board_id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            ba.finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(BoardActivity.this ,"다시 시도해주세요!",Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(BoardActivity.this ,"다시 시도해주세요!",Toast.LENGTH_SHORT).show();
                }
            });


        }
        else{
            Toast.makeText(BoardActivity.this ,"본인 게시글만 삭제 가능합니다!",Toast.LENGTH_SHORT).show();
        }

    }
}
