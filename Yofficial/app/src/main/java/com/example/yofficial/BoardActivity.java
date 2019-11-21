package com.example.yofficial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
        this.finish();
    }
}
