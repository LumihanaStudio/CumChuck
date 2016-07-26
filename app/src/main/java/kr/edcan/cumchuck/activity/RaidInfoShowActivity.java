package kr.edcan.cumchuck.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.model.Raid;
import kr.edcan.cumchuck.model.User;
import kr.edcan.cumchuck.utils.CumChuckNetworkHelper;
import kr.edcan.cumchuck.utils.NetworkInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RaidInfoShowActivity extends AppCompatActivity {

    ListView listView;
    TextView title, dateandauthor, resTitle, resAddress;
    NetworkInterface service;
    Call<Raid> getRaidInfo;
    Call<User> getFriendInfo;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_raid);
        startActivity(new Intent(getApplicationContext(), LoadingActivity.class));
        setData();

    }

    private void setData() {
        intent = getIntent();
        service = CumChuckNetworkHelper.getNetworkInstance();
        getRaidInfo = service.getRaidInfo(intent.getStringExtra("raidId"));
        getRaidInfo.enqueue(new Callback<Raid>() {
            @Override
            public void onResponse(Call<Raid> call, Response<Raid> response) {
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
        dateandauthor = (TextView) findViewById(R.id.raidshow_date_and_author);
        resTitle = (TextView) findViewById(R.id.raidshow_resTitle);
        resAddress = (TextView) findViewById(R.id.raidshow_resAddress);
        getFriendInfo = service.showFriendInfo(body.getHost());
        getFriendInfo.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                switch (response.code()) {
                    case 200:
                        title.setText(body.getTitle());
                        dateandauthor.setText(body.getRaidDate().toLocaleString());
                        resTitle.setText(body.getResTitle());
                        dateandauthor.setText(response.body().getName());
                        resAddress.setText(body.getResAddress());
                        break;
                    case 400:
                        Toast.makeText(RaidInfoShowActivity.this, "레이드 정보를 불러오는 중 문제가 발생했습니다.", Toast.LENGTH_SHORT).show();
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
    }
}
