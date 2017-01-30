package com.malkes.weatherforecast.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import static android.R.attr.data;

/**
 * Created by Malkes on 29/01/17.
 */

public class ImageUtil {

    public static Drawable getDrawableByName(Context context,String name){
        int id = context.getResources().getIdentifier(name.replace("-","_"), "drawable", context.getPackageName());
        if(id > 0){
            return context.getDrawable(id);
        }
        return  null;
    }
}
