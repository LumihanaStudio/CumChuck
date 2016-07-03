package kr.edcan.cumchuck.activity;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Date;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.adapter.MyReviewRecyclerAdapter;
import kr.edcan.cumchuck.model.MyReviewData;
import kr.edcan.cumchuck.model.Restaurant;
import kr.edcan.cumchuck.model.User;

public class MyReviewActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<MyReviewData> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_review);
        setAppbarLayout();
        setDefault();
    }

    private void setAppbarLayout() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        arrayList.add(new MyReviewData("히어로즈 오브 더 스톰", "굳이 롤을 앞지를 필요가 있나요", 5.0,
                new Restaurant("청담 시공폭풍 레스토랑", "서울특별시 강남구 청담동", "010-9944-4144", 1, 5.0),
                new User("오준석", "a0a0a0a0a0a0a0a0a0a"),
                new Date(System.currentTimeMillis())));
        arrayList.add(new MyReviewData("히어로즈 오브 더 스톰", "굳이 롤을 앞지를 필요가 있나요", 5.0,
                new Restaurant("청담 시공폭풍 레스토랑", "서울특별시 강남구 청담동", "010-9944-4144", 1, 5.0),
                new User("오준석", "a0a0a0a0a0a0a0a0a0a"),
                new Date(System.currentTimeMillis())));
        arrayList.add(new MyReviewData("히어로즈 오브 더 스톰", "굳이 롤을 앞지를 필요가 있나요", 5.0,
                new Restaurant("청담 시공폭풍 레스토랑", "서울특별시 강남구 청담동", "010-9944-4144", 1, 5.0),
                new User("오준석", "a0a0a0a0a0a0a0a0a0a"),
                new Date(System.currentTimeMillis())));
        MyReviewRecyclerAdapter adapter = new MyReviewRecyclerAdapter(getApplicationContext(), arrayList);
        recyclerView.setAdapter(adapter);
    }
}
