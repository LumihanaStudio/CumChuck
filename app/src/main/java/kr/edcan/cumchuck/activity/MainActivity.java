package kr.edcan.cumchuck.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kr.edcan.cumchuck.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    FloatingActionButton floatingActionButton;
    Toolbar toolbar;
    ImageView favorite, mypage;
    TextView currentRaidJoin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDefault();
    }

    private void setDefault() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        favorite = (ImageView) toolbar.findViewById(R.id.open_favorite);
        mypage = (ImageView) toolbar.findViewById(R.id.open_myaccount);
        currentRaidJoin = (TextView) findViewById(R.id.current_raid_join);
        favorite.setOnClickListener(this);
        mypage.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);
        currentRaidJoin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.open_favorite:
                startActivity(new Intent(getApplicationContext(), FavoriteViewActivity.class));
                break;
            case R.id.open_myaccount:
                startActivity(new Intent(getApplicationContext(), MyPageActivity.class));
                break;
            case R.id.fab:
                break;
            case R.id.current_raid_join:
                break;
        }
    }
}
