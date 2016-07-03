package kr.edcan.cumchuck.activity;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.widget.ListView;

import java.util.ArrayList;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.adapter.RunningRaidListViewAdapter;

public class RunningRaidActivity extends AppCompatActivity {


    ArrayList<Pair<String, String>> arrayList;
    Toolbar toolbar;
    ListView currentPersonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_raid);
        setDefault();
        setAppbarLayout();
    }

    private void setDefault() {
        arrayList = new ArrayList<>();
        currentPersonList = (ListView) findViewById(R.id.runningraid_personlist);
        arrayList.add(Pair.create("루미논 카노네스", "ㅁㄴㅇㄹ"));
        arrayList.add(Pair.create("새벽이라니 이게뭐야", "ㅁㄴㅇㄹ"));
        arrayList.add(Pair.create("졸려죽겠다고", "ㅁㄴㅇㄹ"));
        arrayList.add(Pair.create("잠자게해줘", "ㅁㄴㅇㄹ"));
        arrayList.add(Pair.create("모콘마감기간죽여버릴거야엄ㄹㄴㅈㄷ람즈ㅏㅣㄷㄹ", "ㅁㄴㅇㄹ"));
        RunningRaidListViewAdapter adapter = new RunningRaidListViewAdapter(getApplicationContext(), arrayList);
        currentPersonList.setAdapter(adapter);
    }

    private void setAppbarLayout() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        getSupportActionBar().setTitle("새로운 레이드 생성하기");
        getSupportActionBar().setElevation(5);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable drawable = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        drawable.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }
}

