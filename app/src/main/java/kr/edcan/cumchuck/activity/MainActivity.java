package kr.edcan.cumchuck.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.utils.CumChuckHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView one, two, three;
    FloatingActionButton floatingActionButton;
    Toolbar toolbar;
    RelativeLayout recommend;
    ImageView favorite, mypage;
    TextView currentRaidJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDefault();
        startActivity(new Intent(getApplicationContext(), MyReviewActivity.class));
        overridePendingTransition(R.anim.slide_up, R.anim.no_change);
    }

    private void setDefault() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        recommend = (RelativeLayout) findViewById(R.id.recommand_recyclerView);
        favorite = (ImageView) toolbar.findViewById(R.id.open_favorite);
        mypage = (ImageView) toolbar.findViewById(R.id.open_myaccount);
        currentRaidJoin = (TextView) findViewById(R.id.current_raid_join);
        one = (ImageView) findViewById(R.id.main_firstImage);
        two = (ImageView) findViewById(R.id.main_secondImage);
        three = (ImageView) findViewById(R.id.main_thirdImage);
        favorite.setOnClickListener(this);
        mypage.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);
        currentRaidJoin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open_favorite:
                startActivity(new Intent(getApplicationContext(), FavoriteViewActivity.class));
                break;
            case R.id.open_myaccount:
                startActivity(new Intent(getApplicationContext(), MyPageActivity.class));
                break;
            case R.id.fab:
                startActivity(new Intent(getApplicationContext(), RaidGenerateActivity.class));
                break;
            case R.id.current_raid_join:
                startActivity(new Intent(getApplicationContext(), RankingShowActivity.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        one.setImageResource(CumChuckHelper.returnRandomAyano());
        two.setImageResource(CumChuckHelper.returnRandomAyano());
        three.setImageResource(CumChuckHelper.returnRandomAyano());
    }
}
