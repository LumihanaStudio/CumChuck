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
import android.view.View;

import java.util.ArrayList;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.adapter.CommonRecyclerAdapter;
import kr.edcan.cumchuck.model.CommonRecycleData;

public class RecommendActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recommandRecyclerView;
    ArrayList<CommonRecycleData> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommand);
        setAppbarLayout();
        setDefault();
    }
    private void setAppbarLayout() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        getSupportActionBar().setTitle("추천");
        getSupportActionBar().setElevation(5);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable drawable = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        drawable.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }
    private void setDefault() {
        recommandRecyclerView = (RecyclerView) findViewById(R.id.recommand_recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recommandRecyclerView.setHasFixedSize(true);
        recommandRecyclerView.setLayoutManager(manager);
        arrayList = new ArrayList<>();
        arrayList.add(new CommonRecycleData("청담 시공폭풍 레스토랑", "서울특별시 강남구 청담동 41-2", "설명입니다", true, 1.2));
        arrayList.add(new CommonRecycleData("청담 시공폭풍 레스토랑", "서울특별시 강남구 청담동 41-2", "설명입니다", true, 1.2));
        arrayList.add(new CommonRecycleData("청담 시공폭풍 레스토랑", "서울특별시 강남구 청담동 41-2", "설명입니다", true, 1.2));
        arrayList.add(new CommonRecycleData("청담 시공폭풍 레스토랑", "서울특별시 강남구 청담동 41-2", "설명입니다", true, 1.2));
        arrayList.add(new CommonRecycleData("청담 시공폭풍 레스토랑", "서울특별시 강남구 청담동 41-2", "설명입니다", true, 1.2));
        CommonRecyclerAdapter adapter = new CommonRecyclerAdapter(getApplicationContext(), 2, arrayList, clickListener);
        recommandRecyclerView.setAdapter(adapter);

    }
    private View.OnClickListener clickListener = view -> Log.e("asdf", "cardview clicked");

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
