package com.example.haidar_1202164150_si4006_pab_modul2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Splash_screen extends AppCompatActivity {
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_splash_screen);

        actionBar = getSupportActionBar();
        actionBar.hide();
        Log.d("MainActivity/"+this.getClass().getSimpleName(), "ini onCreate");
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 5000L); //5000 L = 5 detik
    }
    //    Start Log Activity LifeCycle
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity/" + this.getClass().getSimpleName(), "ini onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity/" + this.getClass().getSimpleName(), "ini onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity/" + this.getClass().getSimpleName(), "ini onPause");
    }
//    End Log Activity LifeCycle
}
