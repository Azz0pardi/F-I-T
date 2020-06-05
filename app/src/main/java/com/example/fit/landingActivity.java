package com.example.fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;



import java.util.Timer;
import java.util.TimerTask;
                                                            // This Activity is a simple activity used as the landing page
                                                            // This is displayed every time the user opens the app

public class landingActivity extends AppCompatActivity {
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        timer = new Timer();                                        // A timer is used in order to change the activity after a preset amount of time goes by
        timer.schedule(new TimerTask() {
            @Override
            public void run() {                                       // When the time is over the user is then taken to the login page activity by making use of intents and startsActivity()
                Intent intent = new Intent(landingActivity.this, UserLogin.class);
                startActivity(intent);
                finish();
            }
        },4000);


    }
}
