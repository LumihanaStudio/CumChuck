package kr.edcan.cumchuck.utils;

import android.content.Context;
import android.content.RestrictionEntry;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import java.util.Random;

import kr.edcan.cumchuck.R;

/**
 * Created by MalangDesktop on 2016-05-07.
 */
public class CumChuckHelper {

    private Context context;
    public CumChuckHelper(Context c) {
        this.context = c;
    }

    public static int returnRandomAyano(){
        int ayanoPic[] = {
                R.drawable.ayano1,
                R.drawable.ayano2,
                R.drawable.ayano3,
                R.drawable.ayano4,
                R.drawable.ayano5,
                R.drawable.ayano6,
                R.drawable.ayano7,
                R.drawable.ayano8,
                R.drawable.ayano9,
                R.drawable.ayano10,
        };
        return ayanoPic[new Random().nextInt(10)];
    }
    private static final float BITMAP_SCALE = 0.4f;
    private static final float BLUR_RADIUS = 7.5f;

    public Bitmap blur(Bitmap image) {
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);

        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        RenderScript rs = RenderScript.create(context);
        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
        theIntrinsic.setRadius(BLUR_RADIUS);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        tmpOut.copyTo(outputBitmap);

        return outputBitmap;
    }
}
