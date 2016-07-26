package kr.edcan.cumchuck.activity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import kr.edcan.cumchuck.R;

public class LoadingActivity extends AppCompatActivity {

    ImageView aniTargetImage;
    public static Activity activity;

    public static void finishThis() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                activity.finish();
            }
        }, 1500);
    }

    public static void finishNow() {
        if (activity != null)
            activity.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setBackgroundWindow();
        setContentView(R.layout.activity_loading);
        setDefault();
    }

    private void setDefault() {
        aniTargetImage = (ImageView) findViewById(R.id.loading_dialogImage);
        aniTargetImage.setImageResource(R.drawable.test_animation);
        AnimationDrawable animationDrawable = (AnimationDrawable) aniTargetImage.getDrawable();
        animationDrawable.start();
    }

    private void setBackgroundWindow() {
        WindowManager.LayoutParams windowManager = getWindow().getAttributes();
        windowManager.dimAmount = 0.75f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
