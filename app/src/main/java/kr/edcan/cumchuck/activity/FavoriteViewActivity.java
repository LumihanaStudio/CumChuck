package kr.edcan.cumchuck.activity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.adapter.FavRecyclerAdapter;
import kr.edcan.cumchuck.model.FavoriteData;

public class FavoriteViewActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView favRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_view);
        setToolbar();
        setDefault();

    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        getSupportActionBar().setTitle("즐겨찾기");
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable drawable = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        drawable.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }

    private void setDefault() {
        favRecycler = (RecyclerView) findViewById(R.id.favoriteView);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        favRecycler.setHasFixedSize(true);
        favRecycler.setLayoutManager(manager);

        // Throw NetworkManager to Adapter when is able
        ArrayList<FavoriteData> arrayList = new ArrayList<>();
        // if(star>5/0) return;
        arrayList.add(new FavoriteData(false, "청담 시공폭풍 레스토랑", "서울특별시 동작구 사당동", "분식집 맛에 질려 ㅗㅗㅗ", 5.0));
        arrayList.add(new FavoriteData(true, "청담 시공폭풍 레스토랑", "서울특별시 관악구 신림동", "분식집 맛에 질려 ㅗㅗㅗ", 2.0));
        arrayList.add(new FavoriteData(false, "청담 시공폭풍 레스토랑", "서울특별시 서초구 반포동", "분식집 맛에 질려 ㅗㅗㅗ", 1.222));
        arrayList.add(new FavoriteData(false, "청담 시공폭풍 레스토랑", "서울특별시 동작구 사당동", "분식집 맛에 질려 ㅗㅗㅗ", 5.0));
        arrayList.add(new FavoriteData(false, "청담 시공폭풍 레스토랑", "서울특별시 동작구 사당동", "분식집 맛에 질려 ㅗㅗㅗ", 5.0));
        arrayList.add(new FavoriteData(false, "청담 시공폭풍 레스토랑", "서울특별시 동작구 사당동", "분식집 맛에 질려 ㅗㅗㅗ", 5.0));
        FavRecyclerAdapter adapter = new FavRecyclerAdapter(getApplicationContext(), arrayList);
        favRecycler.setAdapter(adapter);
    }

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
