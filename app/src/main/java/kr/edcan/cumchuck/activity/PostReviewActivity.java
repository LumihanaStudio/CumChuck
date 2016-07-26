package kr.edcan.cumchuck.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rey.material.widget.Slider;

import java.text.DecimalFormat;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.model.Raid;
import kr.edcan.cumchuck.model.Restaurant;
import kr.edcan.cumchuck.model.Review;
import kr.edcan.cumchuck.utils.CumChuckNetworkHelper;
import kr.edcan.cumchuck.utils.DataManager;
import kr.edcan.cumchuck.utils.NetworkInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostReviewActivity extends AppCompatActivity {

    Toolbar toolbar;
    Slider slider;
    TextView ratingText, postArticle, headerTitle, headerContent;
    Intent intent;
    String resId, resTitle, resAddress;
    EditText title, content;
    Call<Review> postReview;
    NetworkInterface service;
    DataManager manager;
    Call<Raid> commitRaid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_review);
        setDefault();
        intent = getIntent();
        setAppbarLayout();
    }


    private void setDefault() {
        slider = (Slider) findViewById(R.id.review_post_rating);
        intent = getIntent();
        resId = intent.getStringExtra("resId");
        resAddress = intent.getStringExtra("resAddress");
        resTitle = intent.getStringExtra("resTitle");
        manager = new DataManager();
        manager.initializeManager(this);
        service = CumChuckNetworkHelper.getNetworkInstance();
        postArticle = (TextView) findViewById(R.id.review_post_confirm);
        headerTitle = (TextView) findViewById(R.id.review_post_title);
        headerContent = (TextView) findViewById(R.id.review_post_address);
        headerTitle.setText(resTitle);
        headerContent.setText(resAddress);
        title = (EditText) findViewById(R.id.review_post_title_input);
        content = (EditText) findViewById(R.id.review_post_content_input);
        ratingText = (TextView) findViewById(R.id.review_post_ratingText);
        ratingText.setText("0.0");
        slider.setOnPositionChangeListener(new Slider.OnPositionChangeListener() {
            @Override
            public void onPositionChanged(Slider view, boolean fromUser, float oldPos, float newPos, int oldValue, int newValue) {
                ratingText.setText(new DecimalFormat("0.0").format((double) newValue / 2) + "");
            }
        });
        postArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoadingActivity.class));
                postReview = service.postReview(manager.getActiveUser().second.getId(), resId, title.getText().toString().trim(), content.getText().toString().trim(), Double.parseDouble(ratingText.getText().toString()));
                postReview.enqueue(new Callback<Review>() {
                    @Override
                    public void onResponse(Call<Review> call, Response<Review> response) {
                        switch (response.code()) {
                            case 200:
                                commitRaid = service.commitRaid(intent.getStringExtra("raidId"), manager.getActiveUser().second.getId());
                                commitRaid.enqueue(new Callback<Raid>() {
                                    @Override
                                    public void onResponse(Call<Raid> call, Response<Raid> response) {
                                        LoadingActivity.finishNow();
                                        switch (response.code()) {
                                            case 200:
                                                Toast.makeText(PostReviewActivity.this, "리뷰가 정상적으로 작성되었습니다\n레이드가 종료되었습니다!", Toast.LENGTH_SHORT).show();
                                                finish();
                                                break;
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Raid> call, Throwable t) {
                                        Log.e("asdf", t.getMessage());
                                    }
                                });
                                finish();
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<Review> call, Throwable t) {
                        Log.e("asdf", t.getMessage());
                    }
                });
            }
        });
    }


    private void setAppbarLayout() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        getSupportActionBar().setTitle("리뷰 작성하기");
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
