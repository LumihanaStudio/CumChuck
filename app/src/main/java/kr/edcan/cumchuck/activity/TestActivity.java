package kr.edcan.cumchuck.activity;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import kr.edcan.cumchuck.R;

public class TestActivity extends AppCompatActivity {

    ImageView imageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        imageview = (ImageView) findViewById(R.id.imageview);
        imageview.setImageDrawable(getResources().getDrawable(R.drawable.test_animation));
        AnimationDrawable drawable = (AnimationDrawable)imageview.getDrawable();
        drawable.start();

    }
}
