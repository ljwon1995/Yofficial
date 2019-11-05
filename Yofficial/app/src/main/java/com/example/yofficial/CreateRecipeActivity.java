package com.example.yofficial;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
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
import androidx.core.content.ContextCompat;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class CreateRecipeActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 0;
    private ImageView getImage;
    private int timeIdCount = 0; // 재료 추가 테이블 count
    private int ssnIdCount = 0;  // 양념 추가 테이블 count
    private int stageIdCount = 0; // 태깅 단계 추가 테이블 count

    //재료 테이블 받아오기
    TableRow tr[] = new TableRow[100];
    EditText ingName[] = new EditText[100];
    EditText ingFigures[] = new EditText[100];

    //양념 테이블 받아오기
    TableRow tr1[] = new TableRow[100];
    EditText ssnName[] = new EditText[100];
    EditText ssnFigures[] = new EditText[100];

    //시간 태깅 단계 받아오기
    TableRow tr2[][] = new TableRow[100][2];
    EditText stageDescr[] = new EditText[100];
    TextView startTime[] = new TextView[100];
    TextView endTime[] = new TextView[100];
    EditText stageEdit[][] = new EditText[100][6];

    //재료 테이블 입력 ArrayList 이름과 양
    ArrayList<String> ingredientName = new ArrayList<String>();
    ArrayList<String> ingredientAmount = new ArrayList<String>();

    //양념 테이블 입력 ArrayList 이름과 양
    ArrayList<String> seasoningName = new ArrayList<String>();
    ArrayList<String> seasoningAmount = new ArrayList<String>();

    // 영상 url 및 시간 태깅 받는 arrayList
    ArrayList<String> stepDescrib = new ArrayList<String>();
    ArrayList<String> startTimeList = new ArrayList<String>();
    ArrayList<String> endTimeList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        final EditText titleEdit = findViewById(R.id.titleEdit);
        final EditText subTitleEdit = findViewById(R.id.subTitleEdit);
        final EditText introEdit = findViewById(R.id.titleEdit);
        final EditText ing1 = findViewById(R.id.ing1);
        final EditText ing2 = findViewById(R.id.ing2);
        final EditText ing3 = findViewById(R.id.ing3);
        final EditText volume1 = findViewById(R.id.volume1);
        final EditText volume2 = findViewById(R.id.volume2);
        final EditText volume3 = findViewById(R.id.volume3);
        final EditText ssn1 = findViewById(R.id.ssn1);
        final EditText ssn2 = findViewById(R.id.ssn2);
        final EditText ssn3 = findViewById(R.id.ssn3);
        final EditText vol1 = findViewById(R.id.vol1);
        final EditText vol2 = findViewById(R.id.vol2);
        final EditText vol3 = findViewById(R.id.vol3);
        final EditText youtubeUrl = findViewById(R.id.yUrl);
        final EditText stepDescrib1 = findViewById(R.id.stepDescrib1);
        final EditText s_hour1 = findViewById(R.id.s_hour1);
        final EditText s_minute1 = findViewById(R.id.s_minute1);
        final EditText s_second1 = findViewById(R.id.s_second1);
        final EditText e_hour1 = findViewById(R.id.e_hour1);
        final EditText e_minute1 = findViewById(R.id.e_minute1);
        final EditText e_second1 = findViewById(R.id.e_second1);



        final TableLayout ingTable = findViewById(R.id.ingTable);
        final TableLayout ssnTable = findViewById(R.id.ssnTable);
        final TableLayout stageTable = findViewById(R.id.stageTable);
        TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams stage_params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight = 1f;
        stage_params.weight= 1f;


        //재료 테이블 배열 초기화
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

        //양념 테이블 배열 초기화
        for (int i = 0; i < ssnName.length; i++) {
            ssnName[i] = new EditText(this);
            ssnName[i].setLayoutParams(params);
            ssnName[i].setBackground(null);
            ssnName[i].setHint("예시) 간장");
            ssnName[i].setHintTextColor(Color.parseColor("#4075757E"));
            ssnName[i].setTextSize(15);
        }

        for (int i = 0; i < ssnFigures.length; i++) {
            ssnFigures[i] = new EditText(this);
            ssnFigures[i].setLayoutParams(params);
            ssnFigures[i].setBackground(null);
            ssnFigures[i].setHint("예시) 2T");
            ssnFigures[i].setHintTextColor(Color.parseColor("#4075757E"));
            ssnFigures[i].setTextSize(15);
        }


        for (int i = 0; i < tr1.length; i++) {
            tr1[i] = new TableRow(this);
        }

        // 시간 태깅 단계 설명 배열 초기화
        for (int i = 0; i < stageDescr.length; i++) {
            stageDescr[i] = new EditText(this);
            stageDescr[i].setLayoutParams(params);
            stageDescr[i].setPadding(0,50,0,0);
            stageDescr[i].setBackground(null);
            stageDescr[i].setHintTextColor(Color.parseColor("#4075757E"));
            stageDescr[i].setTextSize(15);

            int int_temp = i + 2;
            String string_temp = Integer.toString(int_temp);
            string_temp += "단계 영상설명 ex) 물을 끊입니다.";
            stageDescr[i].setHint(string_temp);
        }


        for (int i = 0; i < startTime.length; i++) {
            startTime[i] = new TextView(this);
            startTime[i].setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            startTime[i].setText("시작 시간");
            startTime[i].setTextColor(Color.parseColor("#000000"));
        }

        for (int i = 0; i < endTime.length; i++) {
            endTime[i] = new TextView(this);
            endTime[i].setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            endTime[i].setText("종료 시간");
            endTime[i].setTextColor(Color.parseColor("#000000"));
        }

        for (int i = 0; i < stageEdit.length; i++) {
            for (int j = 0; j < stageEdit[i].length; j++) {
                stageEdit[i][j] = new EditText(this);
                stageEdit[i][j].setLayoutParams(stage_params);
                stageEdit[i][j].setBackgroundResource(R.drawable.edittext_teduri);
                stageEdit[i][j].setTextSize(15);

                InputFilter[] FilterArray = new InputFilter[1];
                FilterArray[0] = new InputFilter.LengthFilter(2);
                stageEdit[i][j].setFilters(FilterArray);
                stageEdit[i][j].setInputType(InputType.TYPE_CLASS_NUMBER);
            }
        }

        for (int i = 0; i < tr2.length; i++) {
            for (int j = 0; j < tr2[i].length; j++) {
                tr2[i][j] = new TableRow(this);
            }
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

        //양념 테이블 추가 삭제
        Button insertSsnBtn = findViewById(R.id.insertSsn);
        insertSsnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tr1[ssnIdCount].addView(ssnName[ssnIdCount]);
                tr1[ssnIdCount].addView(ssnFigures[ssnIdCount]);
                ssnTable.addView(tr1[ssnIdCount]);
                ssnIdCount++;
            }
        });
        Button removeSsnBtn = findViewById(R.id.removeSsn);
        removeSsnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ssnIdCount > 0) {
                    ssnTable.removeView(tr1[ssnIdCount - 1]);
                    tr1[ssnIdCount - 1].removeView(ssnName[ssnIdCount - 1]);
                    tr1[ssnIdCount - 1].removeView(ssnFigures[ssnIdCount - 1]);
                    ssnIdCount--;
                }
            }
        });

        //시간 태깅 입력 추가

        Button insertStage = (Button)findViewById(R.id.insertStage);
        insertStage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stageTable.addView(stageDescr[stageIdCount]);
                tr2[stageIdCount][0].addView(startTime[stageIdCount]);
                tr2[stageIdCount][0].addView(stageEdit[stageIdCount][0]);
                tr2[stageIdCount][0].addView(stageEdit[stageIdCount][1]);
                tr2[stageIdCount][0].addView(stageEdit[stageIdCount][2]);
                tr2[stageIdCount][1].addView(endTime[stageIdCount]);
                tr2[stageIdCount][1].addView(stageEdit[stageIdCount][3]);
                tr2[stageIdCount][1].addView(stageEdit[stageIdCount][4]);
                tr2[stageIdCount][1].addView(stageEdit[stageIdCount][5]);
                stageTable.addView(tr2[stageIdCount][0]);
                stageTable.addView(tr2[stageIdCount][1]);
                stageIdCount++;
            }
        });

        Button removeStage = (Button)findViewById(R.id.removeStage);
        removeStage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stageIdCount > 0) {
                    stageTable.removeView(tr2[stageIdCount - 1][1]);
                    stageTable.removeView(tr2[stageIdCount - 1][0]);
                    tr2[stageIdCount - 1][1].removeView(stageEdit[stageIdCount - 1][5]);
                    tr2[stageIdCount - 1][1].removeView(stageEdit[stageIdCount - 1][4]);
                    tr2[stageIdCount - 1][1].removeView(stageEdit[stageIdCount - 1][3]);
                    tr2[stageIdCount - 1][1].removeView(endTime[stageIdCount - 1]);
                    tr2[stageIdCount - 1][0].removeView(stageEdit[stageIdCount - 1][2]);
                    tr2[stageIdCount - 1][0].removeView(stageEdit[stageIdCount - 1][1]);
                    tr2[stageIdCount - 1][0].removeView(stageEdit[stageIdCount - 1][0]);
                    tr2[stageIdCount - 1][0].removeView(startTime[stageIdCount - 1]);
                    stageTable.removeView(stageDescr[stageIdCount - 1]);
                    stageIdCount--;
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
        final Spinner mainIngSpinner = (Spinner)findViewById(R.id.main_ing);
        ArrayAdapter mainIngAdapter = ArrayAdapter.createFromResource(this, R.array.data_mainIng, android.R.layout.simple_spinner_item);
        mainIngAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mainIngSpinner.setAdapter(mainIngAdapter);

        final Spinner typeSpinner = (Spinner)findViewById(R.id.type);
        ArrayAdapter typeAdapter = ArrayAdapter.createFromResource(this, R.array.data_type, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);

        final Spinner featureSpinner = (Spinner)findViewById(R.id.feature);
        ArrayAdapter featureAdapter = ArrayAdapter.createFromResource(this, R.array.data_feature, android.R.layout.simple_spinner_item);
        featureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        featureSpinner.setAdapter(featureAdapter);



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

        final RecipeInfo recipeInfo = new RecipeInfo();


        Button createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() { // 레시피 정보 액티비티에 입력받은 값 전달
            @Override
            public void onClick(View v) {
                recipeInfo.setRecipeTitle(titleEdit.getText().toString()); // 제목 전달
                recipeInfo.setRecipeSubTitle(subTitleEdit.getText().toString()); // 부제목 전달
                recipeInfo.setIntroRecipe(introEdit.getText().toString()); //요리 설명 전달
                recipeInfo.setMainIngredient(mainIngSpinner.getSelectedItem().toString()); // 카테고리 중 주재료 부분 전달
                recipeInfo.setType(typeSpinner.getSelectedItem().toString()); // 카테고리 중 타입 부분 전달
                recipeInfo.setFeature(featureSpinner.getSelectedItem().toString()); // 카테고리 중 특징 부분 전달
                recipeInfo.setServings(servingSelB.getText().toString()); // 인분 수 전달
                recipeInfo.setDifficulty(diffiSelB.getText().toString()); // 난이도 전달
                recipeInfo.setDuraTime(duraSelB.getText().toString()); // 소요시간 전달

                // 재료 입력 전달 부분
                ingredientName.add(ing1.getText().toString());  // xml의 view를 통해 입력 받은 값 arraylist에 입력, 재료 이름 부분
                ingredientName.add(ing2.getText().toString());
                ingredientName.add(ing3.getText().toString());
                ingredientAmount.add(volume1.getText().toString()); // xml의 view를 통해 입력 받은 값 arraylist에 입력, 재료의 양 부분
                ingredientAmount.add(volume2.getText().toString());
                ingredientAmount.add(volume3.getText().toString());

                // 추가 버튼으로 만들어진 동적 테이블 행에 들어간 값 arraylist에 입력
                for (int i = 0; i < timeIdCount; i++) {
                    EditText tempName = (EditText)tr[i].getChildAt(0);
                    ingredientName.add(tempName.getText().toString());

                    EditText tempAmount = (EditText)tr[i].getChildAt(1);
                    ingredientAmount.add(tempAmount.getText().toString());
                }

                recipeInfo.setIngredientName(ingredientName); // 들어가는 재료 이름 전달
                recipeInfo.setIngredientAmount(ingredientAmount); // 들어가는 재료의 양 전달

                // 양념 입력 전달 부분
                seasoningName.add(ssn1.getText().toString());  // xml의 view를 통해 입력 받은 값 arraylist에 입력, 양념 이름 부분
                seasoningName.add(ssn2.getText().toString());
                seasoningName.add(ssn3.getText().toString());
                seasoningAmount.add(vol1.getText().toString()); // xml의 view를 통해 입력 받은 값 arraylist에 입력, 양념의 양 부분
                seasoningAmount.add(vol2.getText().toString());
                seasoningAmount.add(vol3.getText().toString());

                // 추가 버튼으로 만들어진 동적 테이블 행에 들어간 값 arraylist에 입력
                for (int i = 0; i < ssnIdCount; i++) {
                    EditText tempName = (EditText)tr1[i].getChildAt(0);
                    seasoningName.add(tempName.getText().toString());

                    EditText tempAmount = (EditText)tr1[i].getChildAt(1);
                    seasoningAmount.add(tempAmount.getText().toString());
                }


                recipeInfo.setIngredientName(seasoningName); // 들어가는 양념 이름 전달
                recipeInfo.setIngredientAmount(seasoningAmount); // 들어가는 양념의 양 전달

                recipeInfo.setYoutubeUrl(youtubeUrl.getText().toString()); // 실행할 영상 url 전달

                // 단계별 설명 및 시간 태깅 전달 부분
                stepDescrib.add(stepDescrib1.getText().toString());
                startTimeList.add(s_hour1.getText().toString() + ":" + s_minute1.getText().toString() + ":" + s_second1.getText().toString());
                endTimeList.add(e_hour1.getText().toString() + ":" + e_minute1.getText().toString() + ":" + e_second1.getText().toString());

                for (int i = 0; i < stageIdCount; i++) {
                    EditText tempStepDescrib = (EditText) stageTable.getChildAt(3 * (i + 1));
                    stepDescrib.add(tempStepDescrib.getText().toString());

                    EditText tempStartHour = (EditText) tr2[i][0].getChildAt(1);
                    EditText tempStartMinute = (EditText) tr2[i][0].getChildAt(2);
                    EditText tempStartSecond = (EditText) tr2[i][0].getChildAt(3);
                    startTimeList.add(tempStartHour.getText().toString() + ":" + tempStartMinute.getText().toString() + ":" + tempStartSecond.getText().toString());

                    EditText tempEndHour = (EditText) tr2[i][1].getChildAt(1);
                    EditText tempEndMinute = (EditText) tr2[i][1].getChildAt(2);
                    EditText tempEndSecond = (EditText) tr2[i][1].getChildAt(3);
                    endTimeList.add(tempEndHour.getText().toString() + ":" + tempEndMinute.getText().toString() + ":" + tempEndSecond.getText().toString());

                }
                for (int i = 0; i < stepDescrib.size(); i++) {
                    System.out.println(stepDescrib.get(i));
                    System.out.println(startTimeList.get(i));
                    System.out.println(endTimeList.get(i));
                }

                recipeInfo.setStepDescrib(stepDescrib); //단계별 설명 전달
                recipeInfo.setStartTime(startTimeList); // 단계별 시작 시간 전달
                recipeInfo.setEndTime(endTimeList); // 단계별 죵료 시간 전달



            }
        });

        TextView testSearchButton = findViewById(R.id.testSearchButton);
        testSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), IngreSearchActivity.class);
                startActivity(intent);
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


