package com.example.cab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread(){

            @Override
            public void run() {

                try {

                    sleep(5000);  // display screen for 5 seconeds
                }
                catch (Exception e){

                    e.printStackTrace();
                }
                finally {

                    sendUserToWelcomeActivity();
                }
            }
        };
        thread.start();
    }


    private void sendUserToWelcomeActivity() {

        Intent welcomeIntent= new Intent(SplashActivity.this,WelcomeActivity.class);
        startActivity(welcomeIntent);
    }

    @Override
    protected void onPause() {

        super.onPause();
    }
}
