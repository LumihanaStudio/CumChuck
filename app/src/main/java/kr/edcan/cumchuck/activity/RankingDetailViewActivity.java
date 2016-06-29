package kr.edcan.cumchuck.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Date;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.adapter.RankingDetailRecyclerAdapter;
import kr.edcan.cumchuck.model.RankingDetailViewData;

public class RankingDetailViewActivity extends AppCompatActivity {

    LinearLayout showPreferences;
    ArrayList<RankingDetailViewData> arrayList;
    Toolbar toolbar;
    RecyclerView detailRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking_detail_view);
        setAppbarLayout();
        setDefault();
    }

    private void setAppbarLayout() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        getSupportActionBar().setTitle("상세보기");
        getSupportActionBar().setElevation(5);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable drawable = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        drawable.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }

    //    public RankingDetailViewData(boolean isHeader, double headerRating, int headerRanking, boolean isFavorite, String headerTitle, String headerAddress, String headerDescription) {
//        public RankingDetailViewData( boolean isHeader, String cardviewTitle, String cardviewContent, String cardviewAuthor, double cardviewRating, Date cardviewDate) {
    private void setDefault() {
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        detailRecyclerView = (RecyclerView) findViewById(R.id.detailViewRecyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        showPreferences = (LinearLayout) findViewById(R.id.ranking_detail_view_focusview);
        detailRecyclerView.setHasFixedSize(true);
        detailRecyclerView.setLayoutManager(manager);
        arrayList = new ArrayList<>();
        arrayList.add(new RankingDetailViewData(true, 5.0, 1, true, "청담 시공폭풍 레스토랑", "서울특별시 강남구 청담동 41-2", "분식집 맛에 질려 일하기싫다진짜 이렇게일하기싫은건 처음이야"));
        arrayList.add(new RankingDetailViewData(false, "히엉어ㅗ어어ㅓㅇ로즈오브더스톰ㄴ", "굳이 롤을 앞지를 이유가 있나요? 일하기싫을뿐입니다\n일하기싫을뿐입니다일하기싫다ㅗㄱ", "Junseok Oh", 2.4, date));
        arrayList.add(new RankingDetailViewData(false, "히엉어ㅗ어어ㅓㅇ로즈오브더스톰ㄴ", "굳이 롤을 앞지를 이유가 있나요? 일하기싫을뿐입니다\n일하기싫을뿐입니다일하기싫다ㅗㄱ", "Junseok Oh", 2.4, date));
        arrayList.add(new RankingDetailViewData(false, "히엉어ㅗ어어ㅓㅇ로즈오브더스톰ㄴ", "굳이 롤을 앞지를 이유가 있나요? 일하기싫을뿐입니다\n일하기싫을뿐입니다일하기싫다ㅗㄱ", "Junseok Oh", 2.4, date));
        arrayList.add(new RankingDetailViewData(false, "히엉어ㅗ어어ㅓㅇ로즈오브더스톰ㄴ", "굳이 롤을 앞지를 이유가 있나요? 일하기싫을뿐입니다\n일하기싫을뿐입니다일하기싫다ㅗㄱ", "Junseok Oh", 2.4, date));
        arrayList.add(new RankingDetailViewData(false, "히엉어ㅗ어어ㅓㅇ로즈오브더스톰ㄴ", "굳이 롤을 앞지를 이유가 있나요? 일하기싫을뿐입니다\n일하기싫을뿐입니다일하기싫다ㅗㄱ", "Junseok Oh", 2.4, date));
        RankingDetailRecyclerAdapter adapter = new RankingDetailRecyclerAdapter(getApplicationContext(), arrayList, onCardListener);
        detailRecyclerView.setAdapter(adapter);
    }
    private View.OnClickListener onCardListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), RankingDetailPopupViewActivity.class));
            overridePendingTransition(R.anim.slide_up, R.anim.no_change);
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
