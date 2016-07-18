package kr.edcan.cumchuck.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.model.Raid;
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
//        getRaidInfo = service.getRaidInfo(intent.getStringExtra("raidId"));
        getRaidInfo = service.getRaidInfo("0");
        getRaidInfo.enqueue(new Callback<Raid>() {
            @Override
            public void onResponse(Call<Raid> call, Response<Raid> response) {
                Log.e("asdf", response.code() + "");
                switch (response.code()) {
                    case 200:
                        Log.e("asdf", response.body().title);
                        setDefault(response.body());
                        break;
                    case 400:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Raid> call, Throwable t) {
                Log.e("asdf", t.getMessage());
            }
        });
    }

    private void setDefault(Raid body) {
        listView = (ListView) findViewById(R.id.myraid_listview);
        title = (TextView) findViewById(R.id.raidshow_title);
        dateandauthor = (TextView) findViewById(R.id.raidshow_date_and_author);
        resTitle = (TextView) findViewById(R.id.raidshow_resTitle);
        resAddress = (TextView) findViewById(R.id.raidshow_resAddress);
        title.setText(body.getTitle());
        dateandauthor.setText(body.getRaidDate().toLocaleString());
        resTitle.setText(body.getResTitle());
        dateandauthor.setText(body.getHost());
        resAddress.setText(body.getResAddress());
        LoadingActivity.activity.finish();
    }
}
