package kr.edcan.cumchuck.activity;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.adapter.RaidFromFriendRecyclerAdapter;
import kr.edcan.cumchuck.model.RaidFromFriendData;

public class RaidFromFriendActivity extends AppCompatActivity {

    RaidFromFriendRecyclerAdapter adapter;
    RecyclerView recyclerView;
    Toolbar toolbar;
    ArrayList<RaidFromFriendData> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raid_from_friend);
        setAppbarLayout();
        setDefault();
        int a = (2 == 3) ? 2 : 3;

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

    private void setDefault() {
        recyclerView = (RecyclerView) findViewById(R.id.raidfromfriend_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        arrayList = new ArrayList<>();
        arrayList.add(new RaidFromFriendData("우리 서민들도 고오급 레스토랑 가야지", "오준석님의 레이드, 2016년 06월 03일 일정", "청담 시공폭풍 레스토랑", "서울시 강남구 청담동 41-2", 0));
        arrayList.add(new RaidFromFriendData("우리 서민들도 고오급 레스토랑 가야지", "오준석님의 레이드, 2016년 06월 03일 일정", "청담 시공폭풍 레스토랑", "서울시 강남구 청담동 41-2", 0));
        arrayList.add(new RaidFromFriendData("우리 서민들도 고오급 레스토랑 가야지", "오준석님의 레이드, 2016년 06월 03일 일정", "청담 시공폭풍 레스토랑", "서울시 강남구 청담동 41-2", 0));
        arrayList.add(new RaidFromFriendData("우리 서민들도 고오급 레스토랑 가야지", "오준석님의 레이드, 2016년 06월 03일 일정", "청담 시공폭풍 레스토랑", "서울시 강남구 청담동 41-2", 0));
        arrayList.add(new RaidFromFriendData("우리 서민들도 고오급 레스토랑 가야지", "오준석님의 레이드, 2016년 06월 03일 일정", "청담 시공폭풍 레스토랑", "서울시 강남구 청담동 41-2", 0));
        arrayList.add(new RaidFromFriendData("우리 서민들도 고오급 레스토랑 가야지", "오준석님의 레이드, 2016년 06월 03일 일정", "청담 시공폭풍 레스토랑", "서울시 강남구 청담동 41-2", 0));
        arrayList.add(new RaidFromFriendData("우리 서민들도 고오급 레스토랑 가야지", "오준석님의 레이드, 2016년 06월 03일 일정", "청담 시공폭풍 레스토랑", "서울시 강남구 청담동 41-2", 0));
        arrayList.add(new RaidFromFriendData("우리 서민들도 고오급 레스토랑 가야지", "오준석님의 레이드, 2016년 06월 03일 일정", "청담 시공폭풍 레스토랑", "서울시 강남구 청담동 41-2", 0));
        adapter = new RaidFromFriendRecyclerAdapter(getApplicationContext(), arrayList);
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
