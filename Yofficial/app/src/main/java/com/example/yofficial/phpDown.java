package com.example.yofficial;

import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


class phpDown extends AsyncTask<String, Integer,String> {
    private static final String TAG = "phpDown!";

    @Override
    protected String doInBackground(String... urls) {
        StringBuilder jsonHtml = new StringBuilder();
        try{
            // 연결 url 설정
            URL url = new URL(urls[0]);
            // 커넥션 객체 생성
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            // 연결되었으면.
            Log.d(TAG, "conn = " + conn);
            if(conn != null){
                conn.setConnectTimeout(10000);
                conn.setUseCaches(false);

                int response;

                Log.d(TAG, "HTTP_OK = "+HttpURLConnection.HTTP_OK);
                response = conn.getResponseCode();
                Log.d(TAG, "response = " + response);

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
            Log.d(TAG, "@" + ex.getMessage());

        }
        return jsonHtml.toString();

    }


    protected void onPostExecute(String str){

    }
}