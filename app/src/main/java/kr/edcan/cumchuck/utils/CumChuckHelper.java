package kr.edcan.cumchuck.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.Random;

import kr.edcan.cumchuck.R;

/**
 * Created by MalangDesktop on 2016-05-07.
 */
public class CumChuckHelper {

    private Context context;
    private MaterialDialog builder;

    public CumChuckHelper(Context c) {
        this.context = c;
    }

    public static void Log(String ClassName, String Message) {
        Log.e("Cumchuck! " + ClassName, Message);
    }

    public static int[] star = {R.drawable.btn_fav_favstar, R.drawable.btn_fav_favstar_off};
    private static final float BITMAP_SCALE = 0.4f;
    private static final float BLUR_RADIUS = 7.5f;
    private static int ayanoPic[] = {
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

    public static int returnRandomAyano() {
        return ayanoPic[new Random().nextInt(10)];
    }

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


    public void showAlertDialog(String contentString, MaterialDialog.SingleButtonCallback callback) {
        View view = LayoutInflater.from(context).inflate(R.layout.common_dialog_view, null);
        TextView title = (TextView) view.findViewById(R.id.dialog_title);
        TextView content = (TextView) view.findViewById(R.id.dialog_content);
        TextView subContent = (TextView) view.findViewById(R.id.dialog_subContent);
        content.setVisibility(View.GONE);
        subContent.setText(contentString);
        subContent.setTextColor(context.getResources().getColor(R.color.textColor));
        title.setText("CumChuck!");
        new MaterialDialog.Builder(context)
                .customView(view, false)
                .positiveColor(context.getResources().getColor(R.color.colorPrimary))
                .positiveText("확인")
                .negativeText("취소")
                .onPositive(callback)
                .show();
    }

    public void showAlertDialog(String titleString, String contentString, MaterialDialog.SingleButtonCallback callback) {
        View view = LayoutInflater.from(context).inflate(R.layout.common_dialog_view, null);
        TextView title = (TextView) view.findViewById(R.id.dialog_title);
        TextView content = (TextView) view.findViewById(R.id.dialog_content);
        TextView subConent = (TextView) view.findViewById(R.id.dialog_subContent);
        content.setVisibility(View.GONE);
        subConent.setText(contentString);
        subConent.setTextColor(context.getResources().getColor(R.color.textColor));
        title.setText(titleString);
        new MaterialDialog.Builder(context)
                .customView(view, false)
                .positiveColor(context.getResources().getColor(R.color.colorPrimary))
                .positiveText("확인")
                .negativeText("취소")
                .onPositive(callback)
                .show();
    }

    public void showAlertDialog(String titleString, String contentString, String subContentString, MaterialDialog.SingleButtonCallback callback) {
        View view = LayoutInflater.from(context).inflate(R.layout.common_dialog_view, null);
        TextView title = (TextView) view.findViewById(R.id.dialog_title);
        TextView content = (TextView) view.findViewById(R.id.dialog_content);
        TextView subConent = (TextView) view.findViewById(R.id.dialog_subContent);
        content.setText(contentString);
        title.setText(titleString);
        subConent.setText(subContentString);

        new MaterialDialog.Builder(context)
                .customView(view, false)
                .positiveColor(context.getResources().getColor(R.color.colorPrimary))
                .positiveText("확인")
                .negativeText("취소")
                .onPositive(callback)
                .show();
    }

    //
    public void showLoadingDialog() {
        View view = LayoutInflater.from(context).inflate(R.layout.loading_dialog_view, null);
        ImageView animationImage = (ImageView) view.findViewById(R.id.loading_dialog_view_image);
        animationImage.setImageResource(R.drawable.test_animation);
        final AnimationDrawable animationDrawable = (AnimationDrawable) animationImage.getDrawable();
        builder = new MaterialDialog.Builder(context)
                .customView(view, false)
                .showListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        animationDrawable.start();
                    }
                })
                .show();
    }

    public void dismissLoadingDialog() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                builder.dismiss();

            }
        }, 1500);
    }
}
