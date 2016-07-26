package kr.edcan.cumchuck.activity;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.adapter.MyReviewRecyclerAdapter;
import kr.edcan.cumchuck.model.MyReviewData;
import kr.edcan.cumchuck.model.Restaurant;
import kr.edcan.cumchuck.model.Review;
import kr.edcan.cumchuck.utils.CumChuckNetworkHelper;
import kr.edcan.cumchuck.utils.DataManager;
import kr.edcan.cumchuck.utils.NetworkInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyReviewActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<MyReviewData> arrayList;
    NetworkInterface service;
    Call<List<Review>> userSelfReview;

    DataManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_review);
        setAppbarLayout();
        setDefault();
    }

    private void setAppbarLayout() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        manager = new DataManager();
        manager.initializeManager(this);
        setSupportActionBar(toolbar);
        service = CumChuckNetworkHelper.getNetworkInstance();
        userSelfReview = service.userSelfReview(manager.getActiveUser().second.getId());
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        getSupportActionBar().setTitle("내 리뷰 관리");
        getSupportActionBar().setElevation(5);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable drawable = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        drawable.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }


    private void setDefault() {
        recyclerView = (RecyclerView) findViewById(R.id.myreview_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        arrayList = new ArrayList<>();
        userSelfReview.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                Log.e("asdf", response.code() + "");
                switch (response.code()) {
                    case 200:
                        for (Review r : response.body()) {
                            arrayList.add(new MyReviewData("", r.getReviewTitle(), r.getReviewContent(), r.getReviewScore(), null, null, r.getUploadDate()));
                        }
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {
                Log.e("asdf", t.getMessage());
            }
        });

        MyReviewRecyclerAdapter adapter = new MyReviewRecyclerAdapter(getApplicationContext(), arrayList);
        recyclerView.setAdapter(adapter);
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
