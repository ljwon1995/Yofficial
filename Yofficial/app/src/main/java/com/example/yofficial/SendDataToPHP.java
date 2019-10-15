package com.example.yofficial;


import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


class SendDataToPHP extends AsyncTask<String, Integer,String> {
    private static final String TAG = "SendDataToPHP!";
    String postData;


    void makeQuery(String category, String query){
        StringBuilder s = new StringBuilder();

        s.append("category=");
        s.append(category);
        s.append("&query=");
        s.append(query);
        postData = s.toString();
    }


    @Override
    protected String doInBackground(String... urls) {
        StringBuilder jsonHtml = new StringBuilder();


        try{
            // 연결 url 설정
            URL url = new URL(urls[0]);

            // 커넥션 객체 생성
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();


            Log.d(TAG, "conn = " + conn);
            // 연결되었으면.
            if(conn != null){
                conn.setConnectTimeout(10000);
                conn.setUseCaches(false);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Accept-Charset", "UTF-8");
                conn.setRequestProperty("UTF-8", "application/x-www-form-urlencoded");
                Log.d(TAG,"postData = " + postData);
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(postData.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();
                Log.d(TAG,"Data sent");

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                for(;;){
                    // 웹상에 보여지는 텍스트를 라인단위로 읽어 저장.
                    String line = br.readLine();
                    if(line == null) break;
                    // 저장된 텍스트 라인을 jsonHtml에 붙여넣음
                    jsonHtml.append(line + "\n");
                }
                br.close();
                Log.d(TAG, "result = " + jsonHtml);
                conn.disconnect();
            }


        } catch(Exception ex){
            ex.printStackTrace();
            Log.d(TAG, "@" + ex.getMessage());

        }
        return jsonHtml.toString();

    }


    protected void onPostExecute(String str){
        //Log.d(TAG, "result = " + str);
    }
}
