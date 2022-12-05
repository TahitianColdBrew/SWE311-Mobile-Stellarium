package org.xmum.stellarium;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private ImageView appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        appName = findViewById(R.id.app_name);

        // set animation for the app name
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.app_name_anim);
        appName.setAnimation(anim);

        // there is an animation for the splash activity, after 5s, it will go to introduction automatically
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(SplashActivity.this, IntroductionActivity.class);
                startActivity(intent);

                // so that when clicking on the "back", the application will quit
                // instead of going back to the splash page
                SplashActivity.this.finish();
            }
        }.start();

    }
}