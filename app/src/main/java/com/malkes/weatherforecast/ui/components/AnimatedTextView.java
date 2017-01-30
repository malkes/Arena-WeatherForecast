package com.malkes.weatherforecast.ui.components;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Malkes on 29/01/17.
 */

public class AnimatedTextView extends TextView {
    public AnimatedTextView(Context context) {
        super(context);
    }

    public AnimatedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAnimatedText(double value){

        try {
            startAnim((int) value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void startAnim(int finalValue){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, finalValue);
        valueAnimator.setDuration(3000);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                setText(valueAnimator.getAnimatedValue().toString());

            }
        });
        valueAnimator.start();
    }
}
