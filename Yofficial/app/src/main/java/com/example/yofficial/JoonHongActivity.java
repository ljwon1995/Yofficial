package com.example.yofficial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;


public class JoonHongActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private Activity ac = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joonhong);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.startmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.signoutbtn:
                mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();

                if(mAuth.getCurrentUser() == null){

                    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestIdToken("903484886790-84aqko9kngrqeohq586c1jujt0ide1vb.apps.googleusercontent.com")
                            .requestEmail()
                            .build();
                    mGoogleSignInClient = GoogleSignIn.getClient(ac, gso);
                    mGoogleSignInClient.signOut();
                }

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);


            default:
                return super.onOptionsItemSelected(item);
        }
    }





    // onButton1Clicked에 대한 로직

    public void onButton1Clicked(View view){
        // LENGTH_LONG : 길게 화면에 나타남
        // LENGTH_SHORT : 짧게 화면에 나타남
        Toast.makeText(getApplicationContext(), "버튼이 눌렸습니다!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(
                getApplicationContext(), // 현재 화면의 제어권자
                VideoListActivity.class); // 다음 넘어갈 클래스 지정
        //intent.putExtra();
        startActivity(intent); // 다음 화면으로 넘어간다

    }

    public void onButton2Clicked(View view){
        // LENGTH_LONG : 길게 화면에 나타남
        // LENGTH_SHORT : 짧게 화면에 나타남
        Toast.makeText(getApplicationContext(), "버튼이 눌렸습니다!", Toast.LENGTH_SHORT).show();



    }

    public void onButton3Clicked(View view){
        // LENGTH_LONG : 길게 화면에 나타남
        // LENGTH_SHORT : 짧게 화면에 나타남
        Toast.makeText(getApplicationContext(), "버튼이 눌렸습니다!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(
                getApplicationContext(), // 현재 화면의 제어권자
                RefregActivity.class); // 다음 넘어갈 클래스 지정
        //intent.putExtra();
        startActivity(intent); // 다음 화면으로 넘어간다

    }

    public void onButton4Clicked(View view){
        // LENGTH_LONG : 길게 화면에 나타남
        // LENGTH_SHORT : 짧게 화면에 나타남
        Toast.makeText(getApplicationContext(), "버튼이 눌렸습니다!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(
                getApplicationContext(), // 현재 화면의 제어권자
                RecommendActivity.class); // 다음 넘어갈 클래스 지정
        //intent.putExtra();
        startActivity(intent); // 다음 화면으로 넘어간다
    }
}
