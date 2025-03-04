package com.example.tp1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView logo;
    private TextView appName;
    private static final int SPLASH_DURATION = 5000; // 5 secondes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logo = findViewById(R.id.logo);
        appName = findViewById(R.id.appName);

        // Animation combinée pour le logo
        AnimationSet animationSet = new AnimationSet(true);

        // Rotation
        RotateAnimation rotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(SPLASH_DURATION);
        rotate.setInterpolator(new AccelerateDecelerateInterpolator());

        // Pulsation (scale)
        ScaleAnimation pulse = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        pulse.setDuration(SPLASH_DURATION / 2);
        pulse.setRepeatCount(1);
        pulse.setRepeatMode(Animation.REVERSE);

        // Fondu
        AlphaAnimation fadeIn = new AlphaAnimation(0.3f, 1.0f);
        fadeIn.setDuration(SPLASH_DURATION / 2);

        // Ajouter les animations à l'ensemble
        animationSet.addAnimation(rotate);
        animationSet.addAnimation(pulse);
        animationSet.addAnimation(fadeIn);

        // Animer le logo
        logo.startAnimation(animationSet);

        // Animation de fade-in pour le nom de l'application
        AlphaAnimation textFadeIn = new AlphaAnimation(0.0f, 1.0f);
        textFadeIn.setDuration(SPLASH_DURATION / 2);
        textFadeIn.setStartOffset(SPLASH_DURATION / 2); // Commence après la moitié du temps
        appName.startAnimation(textFadeIn);

        // Gérer la transition vers la MainActivity après la fin des animations
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        }, SPLASH_DURATION);
    }
}