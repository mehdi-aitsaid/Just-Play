package com.example.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class activity_splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(activity_splash_screen.this, FirstPage.class));
                finish();
            }
        },4000);
        ImageView splashImage = findViewById(R.id.imageViewSplash);
        TextView splashTxt = findViewById(R.id.textView_splash);
        Animation fadeIn = new AlphaAnimation(0, 1);
        //fadeIn.setInterpolator(new DecelerateInterpolator()); // add this
        fadeIn.setDuration(4000);

        AnimationSet animation = new AnimationSet(true);
        animation.addAnimation(fadeIn);
        splashImage.setAnimation(animation);
        splashTxt.setAnimation(animation);
/*
        View view = findViewById(android.R.id.content);
        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        mLoadAnimation.setDuration(4000);
        view.startAnimation(mLoadAnimation);
*/

        /*
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

         */

    }
}
