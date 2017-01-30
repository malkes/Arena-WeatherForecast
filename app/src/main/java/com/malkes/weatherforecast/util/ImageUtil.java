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
    private static final float BITMAP_SCALE = 0.4f;
    private static final float BLUR_RADIUS = 7.5f;

    public static Bitmap blur(Context ctx, Bitmap image) {
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);

        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        RenderScript rs = RenderScript.create(ctx);
        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
        theIntrinsic.setRadius(BLUR_RADIUS);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        tmpOut.copyTo(outputBitmap);

        return outputBitmap;
    }

    public static Drawable getDrawableByName(Context context,String name){
        int id = context.getResources().getIdentifier(name.replace("-","_"), "drawable", context.getPackageName());
        if(id > 0){
            return context.getDrawable(id);
        }
        return  null;
    }
}
