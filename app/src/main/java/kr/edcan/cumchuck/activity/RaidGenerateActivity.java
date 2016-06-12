package kr.edcan.cumchuck.activity;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.WindowDecorActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.utils.CumChuckHelper;
import kr.edcan.cumchuck.utils.RoundImageView;

public class RaidGenerateActivity extends AppCompatActivity {
    CumChuckHelper helper;// Toolbar
    Toolbar toolbar;
    // View shown
    LinearLayout defaultView;
    RecyclerView raidGenerateView;
    // EditText to input search
    EditText searchQuery;
    // boolean value to check state
    boolean isFirst = false, isAvailable = false;
    ImageView searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raid_generate);
        setAppbarLayout();
        setDefault();

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

    private void setDefault() {
        helper = new CumChuckHelper(this);
        searchQuery = (EditText) findViewById(R.id.raid_generate_searchQuery);
        defaultView = (LinearLayout) findViewById(R.id.raid_generate_defaultView);
        searchButton = (ImageView) findViewById(R.id.raid_generate_searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
    }

    private void search() {
        String s = searchQuery.getText().toString().trim();
        if(s.equals("")){
            searchQuery.setError("음식점 이름을 입력해주세요!");
            searchQuery.setText("");
        } else {

            // Search AsyncTask
        }
    }

    private void setView() {
        TextView title = (TextView) findViewById(R.id.raid_generate_defaultViewTitle);
        TextView content = (TextView) findViewById(R.id.raid_generate_defaultViewContent);
        RoundImageView statusImage = (RoundImageView) findViewById(R.id.raid_generate_defaultViewImage);

        /*
        * 1. if isFirst = false, must find restaurant first
        * 2. if isAvailable = false, no searched restaurant
        * 3. if isAvailable, show input view*/
        if (!isFirst) {
            defaultView.setVisibility(View.VISIBLE);
            raidGenerateView.setVisibility(View.GONE);
        } else if (!isAvailable) {
//            statusImage.setImageResource();
            title.setText("검색된 음식점이 없습니다!");
            content.setText("다른 검색어로 시도해보세요!");
        } else if (isAvailable) {
            defaultView.setVisibility(View.GONE);
            raidGenerateView.setVisibility(View.VISIBLE);
        }
    }

    private void showInputWIndow() {
        helper.showLoadingDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                helper.dismissLoadingDialog();
            }
        },300);

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
