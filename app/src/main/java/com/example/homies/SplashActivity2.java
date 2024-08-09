package com.example.homies;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

public class SplashActivity2 extends AppCompatActivity
{
    private AppCompatImageView splash_IMG_logo;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        findViews();
        startAnimation(splash_IMG_logo);

    }

    private void startAnimation(View view)
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;

        //set start location

        view.setY(-height / 2.0f);
        view.setScaleX(0.0f);
        view.setScaleY(0.0f);
        view.setAlpha(0.0f);

        view.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).translationY(0).setDuration(4000).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationEnd(@NonNull Animator animation)
            {
                transactToMainActivity();

            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {

            }
        });
    }

    private void transactToMainActivity()
    {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void findViews()
    {
        splash_IMG_logo = findViewById(R.id.splash_IMG_logo) ;
    }
}