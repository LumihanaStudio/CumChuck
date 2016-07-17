package kr.edcan.cumchuck.activity;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import kr.edcan.cumchuck.R;

public class LoadingActivity extends AppCompatActivity {

    ImageView aniTargetImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
