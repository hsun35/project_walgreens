package com.example.project_walgreens.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import static java.lang.Thread.sleep;

/**
 * Created by hefen on 2/22/2018.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Start home activity
        Thread launcher = new Thread(new SplashScreenLauncher());
        launcher.start();

        //startActivity(new Intent(SplashActivity.this, MainActivity.class));

        // close splash activity
        //finish();
    }
    private class SplashScreenLauncher implements Runnable {
        public void run() {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            SplashActivity.this.finish();
        }
    }
}
