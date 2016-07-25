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
import java.util.List;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.adapter.RaidFromFriendRecyclerAdapter;
import kr.edcan.cumchuck.model.Raid;
import kr.edcan.cumchuck.model.RaidFromFriendData;
import kr.edcan.cumchuck.utils.CumChuckNetworkHelper;
import kr.edcan.cumchuck.utils.DataManager;
import kr.edcan.cumchuck.utils.NetworkInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RaidFromFriendActivity extends AppCompatActivity {

    RaidFromFriendRecyclerAdapter adapter;
    RecyclerView recyclerView;
    Toolbar toolbar;
    Call<List<Raid>> getFriendRaidList;
    ArrayList<RaidFromFriendData> arrayList;
    DataManager manager;
    NetworkInterface service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raid_from_friend);
        setAppbarLayout();
        setNetwork();
        setDefault();
    }

    private void setNetwork() {
        manager = new DataManager();
        manager.initializeManager(this);
        service = CumChuckNetworkHelper.getNetworkInstance();
        getFriendRaidList = service.getFriendRaidList(manager.getActiveUser().second.getId());
        getFriendRaidList.enqueue(new Callback<List<Raid>>() {
            @Override
            public void onResponse(Call<List<Raid>> call, Response<List<Raid>> response) {
                Log.e("asdf", response.code() + "");
                switch (response.code()) {
                    case 200:
                        for (Raid r : response.body()) {
                            Log.e("asdf", r.getResTitle());
                        }
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Raid>> call, Throwable t) {
                Log.e("asdf", t.getMessage());
            }
        });
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
