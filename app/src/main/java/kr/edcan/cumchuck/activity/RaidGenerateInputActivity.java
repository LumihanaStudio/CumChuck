package kr.edcan.cumchuck.activity;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.rey.material.widget.Slider;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.utils.CumChuckHelper;

public class RaidGenerateInputActivity extends AppCompatActivity {

    CumChuckHelper helper;
    Toolbar toolbar;
    // Input Window
    TextView headerTitle, headerAddress, headerChangeRest;
    TextInputLayout raidNameInput;
    TextView cardviewDate, cardviewTime, setDateTime, personCountTrack;
    Slider personCount;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raid_generate_input);
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
        headerAddress = (TextView) findViewById(R.id.raid_input_headerAddress);
        headerChangeRest = (TextView) findViewById(R.id.raid_input_headerChangePlace);
        headerTitle = (TextView) findViewById(R.id.raid_input_headerTitle);
        raidNameInput = (TextInputLayout) findViewById(R.id.raid_input_raidnameInput);
        cardviewDate = (TextView) findViewById(R.id.raid_input_cardview_date);
        cardviewTime = (TextView) findViewById(R.id.raid_input_cardview_time);
        setDateTime = (TextView) findViewById(R.id.raid_input_changeCalendar);
        personCountTrack = (TextView) findViewById(R.id.raid_input_personCountTrack);
        personCount = (Slider) findViewById(R.id.raid_input_personSlider);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            helper.showAlertDialog("레이드 생성을 취소하고 뒤로 돌아가시겠습니까?\n입력하신 정보는 저장되지 않습니다", new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    finish();
                }
            });
        }
         else finish();
        return super.onKeyDown(keyCode, event);
    }
}
