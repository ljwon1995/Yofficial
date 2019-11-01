package com.example.yofficial;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;


public class CreateRecipeActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 0;
    private ImageView getImage;
    private int timeIdCount = 0;

    //재료 테이블 받아오기
    TableRow tr[] = new TableRow[100];
    EditText ingName[] = new EditText[100];
    EditText ingFigures[] = new EditText[100];




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        final TableLayout ingTable = findViewById(R.id.ingTable);
        TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight = 1f;

        for (int i = 0; i < ingName.length; i++) {
            ingName[i] = new EditText(this);
            ingName[i].setLayoutParams(params);
            ingName[i].setBackground(null);
            ingName[i].setHint("예시) 돼지고기");
            ingName[i].setHintTextColor(Color.parseColor("#4075757E"));
            ingName[i].setTextSize(15);
        }

        for (int i = 0; i < ingFigures.length; i++) {
            ingFigures[i] = new EditText(this);
            ingFigures[i].setLayoutParams(params);
            ingFigures[i].setBackground(null);
            ingFigures[i].setHint("예시) 200g");
            ingFigures[i].setHintTextColor(Color.parseColor("#4075757E"));
            ingFigures[i].setTextSize(15);
        }


        for (int i = 0; i < tr.length; i++) {
            tr[i] = new TableRow(this);
        }

        //재료 테이블 추가 삭제
        Button insertIngBtn = findViewById(R.id.insertIng);
        insertIngBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tr[timeIdCount].addView(ingName[timeIdCount]);
                tr[timeIdCount].addView(ingFigures[timeIdCount]);
                ingTable.addView(tr[timeIdCount]);
                timeIdCount++;
            }
        });
        Button removeIngBtn = findViewById(R.id.removeIng);
        removeIngBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timeIdCount > 0) {
                    ingTable.removeView(tr[timeIdCount - 1]);
                    tr[timeIdCount - 1].removeView(ingName[timeIdCount - 1]);
                    tr[timeIdCount - 1].removeView(ingFigures[timeIdCount - 1]);
                    timeIdCount--;
                }
            }
        });


        //사진 받아오기
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


        // 카테고리 스피너 선언부
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


        //시간 태깅 스피너 선언부
        Spinner  s_hour1= (Spinner)findViewById(R.id.s_hour1);
        ArrayAdapter s_hour1Adapter = ArrayAdapter.createFromResource(this, R.array.data_hour, android.R.layout.simple_spinner_item);
        s_hour1.setAdapter(s_hour1Adapter);
        Spinner  s_minute1= (Spinner)findViewById(R.id.s_minute1);
        ArrayAdapter s_minute1Adapter = ArrayAdapter.createFromResource(this, R.array.data_minute, android.R.layout.simple_spinner_item);
        s_minute1.setAdapter(s_minute1Adapter);
        Spinner  s_second1= (Spinner)findViewById(R.id.s_second1);
        ArrayAdapter s_second1Adapter = ArrayAdapter.createFromResource(this, R.array.data_second, android.R.layout.simple_spinner_item);
        s_second1.setAdapter(s_second1Adapter);

        // 인분, 난이도, 소요시간 다중 선택 부
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

        //시간 태깅 입력 추가
        TableLayout timeTable = (TableLayout)findViewById(R.id.timeTable);
        timeTable.setStretchAllColumns(true);
        TableRow tr1 = new TableRow(this);
        tr1.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));



        Button insert = (Button)findViewById(R.id.insertTimeButton);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    //사진 받아오기 관련
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


