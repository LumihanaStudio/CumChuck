package kr.edcan.cumchuck.activity;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.rey.material.widget.Slider;

import java.text.DecimalFormat;

import kr.edcan.cumchuck.R;

public class PostReviewActivity extends AppCompatActivity {

    Toolbar toolbar;
    Slider slider;
    TextView ratingText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_review);
        setDefault();
        setAppbarLayout();
    }

    private void setDefault() {
        slider=  (Slider) findViewById(R.id.review_post_rating);
        ratingText = (TextView)findViewById(R.id.review_post_ratingText);
        slider.setOnPositionChangeListener((view, fromUser, oldPos, newPos, oldValue, newValue) -> ratingText.setText(new DecimalFormat("0.0").format((double)newValue/2)+""));
    }


    private void setAppbarLayout() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        getSupportActionBar().setTitle("친구들의 레이드 목록");
        getSupportActionBar().setElevation(5);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable drawable = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        drawable.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
