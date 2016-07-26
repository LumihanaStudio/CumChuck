package kr.edcan.cumchuck.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.adapter.RunningRaidListViewAdapter;
import kr.edcan.cumchuck.model.Raid;
import kr.edcan.cumchuck.model.User;
import kr.edcan.cumchuck.utils.CumChuckNetworkHelper;
import kr.edcan.cumchuck.utils.NetworkInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RunningRaidActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView listView;
    TextView title, dateandauthor, resTitle, resAddress, currentStatus, writeReview, refresh;
    NetworkInterface service;
    Call<Raid> getRaidInfo;
    Call<User> getFriendInfo;
    ArrayList<User> userList = new ArrayList<>();
    Intent intent;
    String resId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_raid);
        setData();
        setAppbarLayout();
    }

    private void setAppbarLayout() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        getSupportActionBar().setTitle("레이드 진행중");
        getSupportActionBar().setElevation(5);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable drawable = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        drawable.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }

    private void setData() {
        intent = getIntent();
        service = CumChuckNetworkHelper.getNetworkInstance();
        getRaidInfo = service.getRaidInfo(intent.getStringExtra("raidId"));
        Log.e("asdf raidid", intent.getStringExtra("raidId"));
        getRaidInfo.enqueue(new Callback<Raid>() {
            @Override
            public void onResponse(Call<Raid> call, Response<Raid> response) {
                Log.e("asdf", response.code() + "");
                switch (response.code()) {
                    case 200:
                        setDefault(response.body());
                        break;
                    case 400:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Raid> call, Throwable t) {
                Log.e("asdf_raidinfo", t.getMessage());
            }
        });
    }

    private void setDefault(final Raid body) {
        listView = (ListView) findViewById(R.id.myraid_listview);
        title = (TextView) findViewById(R.id.raidshow_title);
        refresh = (TextView) findViewById(R.id.go_on_refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });
        dateandauthor = (TextView) findViewById(R.id.raidshow_date_and_author);
        resTitle = (TextView) findViewById(R.id.raidshow_resTitle);
        writeReview = (TextView) findViewById(R.id.go_on_review);
        currentStatus = (TextView) findViewById(R.id.raidinfo_currentstatus);
        resAddress = (TextView) findViewById(R.id.raidshow_resAddress);
        Log.e("asdf", body.getHost());
        getFriendInfo = service.showFriendInfo(body.getHost());
        getFriendInfo.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                switch (response.code()) {
                    case 200:
                        resId = body.getResId();
                        title.setText(body.getTitle());
                        dateandauthor.setText(body.getRaidDate().toLocaleString());
                        resTitle.setText(body.getResTitle());
                        dateandauthor.setText(response.body().getName());
                        resAddress.setText(body.getResAddress());
                        currentStatus.setText(body.getRaidMax() + "명 중 " + (userList.size() + 1) + "명 참여중");
                        for (User u : body.getMember()) {
                            userList.add(u);
                        }
                        listView.setAdapter(new RunningRaidListViewAdapter(getApplicationContext(), userList));
                        break;
                    case 400:
                        Toast.makeText(RunningRaidActivity.this, "레이드 정보를 불러오는 중 문제가 발생했습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                }
                LoadingActivity.activity.finish();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("asdf_friendinfo", t.getMessage());
            }
        });
        writeReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PostReviewActivity.class).putExtra("resId", resId).putExtra("resTitle", resTitle.getText().toString()).putExtra("resAddress", resAddress.getText().toString().trim()));
            }
        });

    }
}

