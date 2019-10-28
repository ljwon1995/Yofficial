package com.example.viewpaper;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.InputStream;


public class CreateRecipeActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 0;
    private ImageView getImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        getImage = (ImageView)findViewById(R.id.user_image);

        getImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });



        Spinner mainIngSpinner = (Spinner)findViewById(R.id.main_ing);
        ArrayAdapter mainIngAdapter = ArrayAdapter.createFromResource(this, R.array.data_mainIng, android.R.layout.simple_spinner_item);
        mainIngAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mainIngSpinner.setAdapter(mainIngAdapter);

        Spinner typeSpinner = (Spinner)findViewById(R.id.type);
        ArrayAdapter typeAdapter = ArrayAdapter.createFromResource(this, R.array.data_type, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);

        Spinner featureSpinner = (Spinner)findViewById(R.id.feature);
        ArrayAdapter featureAdapter = ArrayAdapter.createFromResource(this, R.array.data_feature, android.R.layout.simple_spinner_item);
        featureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        featureSpinner.setAdapter(featureAdapter);

        final TextView servingSelB = (TextView) findViewById(R.id.servingSel);
        final TextView diffiSelB = (TextView) findViewById(R.id.diffiSel);
        final TextView duraSelB = (TextView) findViewById(R.id.duraSel);

        servingSelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] serNum = new String[]{"1인분","2인분","3인분","4인분","5인분","5인분이상"};
                final int[] selectedIndex = {0};

                AlertDialog.Builder dialog = new AlertDialog.Builder(CreateRecipeActivity.this);
                dialog.setTitle("몇 인분인지 선택해주세요.");
                dialog.setSingleChoiceItems(serNum, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedIndex[0] = which;
                    }
                }).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        servingSelB.setText(serNum[selectedIndex[0]]);
                    }
                }).create().show();
            }
        });

        diffiSelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] level = new String[]{"브론즈","실버","골드","다이아","마스터"};
                final int[] selectedIndex = {0};

                AlertDialog.Builder dialog = new AlertDialog.Builder(CreateRecipeActivity.this);
                dialog.setTitle("난이도를 선택해주세요.");
                dialog.setSingleChoiceItems(level, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedIndex[0] = which;
                    }
                }).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        diffiSelB.setText(level[selectedIndex[0]]);
                    }
                }).create().show();
            }
        });

        duraSelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] duraTime = new String[]{"5분","10분","15분","30분","1시간","2시간 이상"};
                final int[] selectedIndex = {0};

                AlertDialog.Builder dialog = new AlertDialog.Builder(CreateRecipeActivity.this);
                dialog.setTitle("요리에 걸리는 시간을 선택해주세요.");
                dialog.setSingleChoiceItems(duraTime, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedIndex[0] = which;
                    }
                }).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        duraSelB.setText(duraTime[selectedIndex[0]]);
                    }
                }).create().show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE)
        {
            if(resultCode == RESULT_OK)
            {
                try{
                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();

                    getImage.setImageBitmap(img);
                }catch(Exception e)
                {

                }
            }
            else if(resultCode == RESULT_CANCELED)
            {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }
}


