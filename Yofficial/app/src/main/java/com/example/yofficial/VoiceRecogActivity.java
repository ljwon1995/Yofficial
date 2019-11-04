package com.example.yofficial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import java.util.ArrayList;
import android.app.Dialog;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;




public class VoiceRecogActivity extends AppCompatActivity implements SensorEventListener {
    private static final int REQUEST_CODE = 1234;
    private SensorManager sensorManager;

    Button Start;
    Button sttBtn;
    TextView Speech;
    TextView textView;
    TextView tvProximity;
    Dialog match_text_dialog;
    ListView textlist;
    ArrayList<String> matches_text;
    Intent intent;

    final int PERMISSION = 1;

    SpeechRecognizer mRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice_recog_activity);

        tvProximity = (TextView) findViewById(R.id.tvProximity);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if (proximitySensor == null) {
            Toast.makeText(this, "센서를 찾을 수 없음", Toast.LENGTH_SHORT).show();
        }

        if (Build.VERSION.SDK_INT >= 23) {
            // 퍼미션 체크
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET, Manifest.permission.RECORD_AUDIO}, PERMISSION);
        }

        Start = (Button) findViewById(R.id.start_reg);
        textView = (TextView) findViewById(R.id.speech);

        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        sttBtn = (Button) findViewById(R.id.start_reg);

        sttBtn.setOnClickListener(v -> {
            mRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
            mRecognizer.setRecognitionListener(listener);
            mRecognizer.startListening(intent);
        });

    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] == 0) {
                tvProximity.setText("Near : " + String.valueOf(event.values[0]));

                // 센서 가까워지면 음성인식 작동
                intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                mRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
                mRecognizer.setRecognitionListener(listener);
                mRecognizer.startListening(intent);
            } else {
                //tvProximity.setText(" Far :" + String.valueOf(event.values[0]));
                tvProximity.setText("거리가 가까워지면 음성인식 시작!");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        switch (accuracy) {
            case SensorManager.SENSOR_STATUS_UNRELIABLE:
                Toast.makeText(this, "UNRELIABLE", Toast.LENGTH_SHORT).show();
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_LOW:
                Toast.makeText(this, "LOW", Toast.LENGTH_SHORT).show();
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM:
                Toast.makeText(this, "MEDIUM", Toast.LENGTH_SHORT).show();
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
                Toast.makeText(this, "HIGH", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private RecognitionListener listener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle params) {
            Toast.makeText(getApplicationContext(), "음성인식을 시작합니다.", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onBeginningOfSpeech() {
        }

        @Override
        public void onRmsChanged(float rmsdB) {
        }

        @Override
        public void onBufferReceived(byte[] buffer) {
        }

        @Override
        public void onEndOfSpeech() {
        }

        @Override
        public void onError(int error) {
            String message;

            switch (error) {
                case SpeechRecognizer.ERROR_AUDIO:
                    message = "오디오 에러";
                    break;
                case SpeechRecognizer.ERROR_CLIENT:
                    message = "클라이언트 에러";
                    break;
                case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                    message = "퍼미션 없음";
                    break;
                case SpeechRecognizer.ERROR_NETWORK:
                    message = "네트워크 에러";
                    break;
                case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                    message = "네트웍 타임아웃";
                    break;
                case SpeechRecognizer.ERROR_NO_MATCH:
                    message = "찾을 수 없음";
                    break;
                case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                    message = "RECOGNIZER가 바쁨";
                    break;
                case SpeechRecognizer.ERROR_SERVER:
                    message = "서버가 이상함";
                    break;
                case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                    message = "말하는 시간초과";
                    break;
                default:
                    message = "알 수 없는 오류임";
                    break;
            }

            Toast.makeText(getApplicationContext(), "에러가 발생하였습니다. : " + message, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResults(Bundle results) {
            // 말을 하면 ArrayList에 단어를 넣고 textView에 단어를 이어줍니다.
            ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

            for (int i = 0; i < matches.size(); i++) {
                textView.setText(matches.get(i));
            }
        }

        @Override
        public void onPartialResults(Bundle partialResults) {
        }

        @Override
        public void onEvent(int eventType, Bundle params) {
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            match_text_dialog = new Dialog(VoiceRecogActivity.this);

            textlist = (ListView) match_text_dialog.findViewById(R.id.list);

            matches_text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            textView.setText("You have said " + matches_text.get(0));
            textView.append("\nYou have said " + matches_text.get(1));
            textView.append("\nYou have said " + matches_text.get(2));

            match_text_dialog.show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    void prev() {

    }

    void restart() {

    }

    void stop() {

    }

    void play() {

    }

    void next() {

    }
}


