package com.example.yofficial;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DBActivity extends Activity {

    TextView txtView;
    phpDown task;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        txtView = (TextView)findViewById(R.id.textView);
        task = new phpDown();
        task.execute("http://10.210.131.89:8080/PHP_connection.php");//도메인을 실행하게되면 php가 실행되서 데이터전달이 일어난다.
    }


    private class phpDown extends AsyncTask<String, Integer,String> {

        @Override
        protected String doInBackground(String... urls) {
            StringBuilder jsonHtml = new StringBuilder();
            try{
                // 연결 url 설정
                URL url = new URL(urls[0]);
                // 커넥션 객체 생성
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                // 연결되었으면.
                if(conn != null){
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);
                    // 연결되었음 코드가 리턴되면.
                    if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                        for(;;){
                            // 웹상에 보여지는 텍스트를 라인단위로 읽어 저장.
                            String line = br.readLine();
                            if(line == null) break;
                            // 저장된 텍스트 라인을 jsonHtml에 붙여넣음
                            jsonHtml.append(line + "\n");
                        }
                        br.close();
                    }
                    conn.disconnect();
                }
            } catch(Exception ex){
                ex.printStackTrace();
            }
            return jsonHtml.toString();

        }

        /*
        protected void onPostExecute(String str){
            txtView.setText(str);
        }
        */
        /*
        protected void onPostExecute(String str){
            String id;

            try{
                JSONObject root = new JSONObject(str);
                JSONArray ja = root.getJSONArray("results"); //get the JSONArray which I made in the php file. the name of JSONArray is "results"

                for(int i=0;i<ja.length();i++){
                    JSONObject jo = ja.getJSONObject(i);
                    id = jo.getString("id");
                    listitem.add(new ListItem(id));
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            txtView.setText("id : "+listitem.get(0).getData(0));
        }
        */


    }



    public void onBackButtonClicked(View v){
        finish();
    }
}
