package com.malkes.weatherforecast.ui.components;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

/**
 * Created by Malkes on 29/01/17.
 */

public class AnimatedImageView extends ImageView {

    public AnimatedImageView(Context context) {
        super(context);
    }

    public AnimatedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        try {
            animateTransition(bm);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void animateTransition(final Bitmap bitmap){
        Animation fadeOutAnimation = new AlphaAnimation(1.0f, 0.0f);
        final Animation fadeInAnimation = new AlphaAnimation(0.0f, 1.0f);
        fadeInAnimation.setDuration(700);
        fadeOutAnimation.setDuration(700);

        fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                AnimatedImageView.super.setImageBitmap(bitmap);
                AnimatedImageView.super.startAnimation(fadeInAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        startAnimation(fadeOutAnimation);
    }
}
