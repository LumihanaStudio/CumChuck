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
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.rey.material.app.DatePickerDialog;
import com.rey.material.app.TimePickerDialog;
import com.rey.material.widget.DatePicker;
import com.rey.material.widget.Slider;
import com.rey.material.widget.TimePicker;

import java.util.Calendar;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.utils.CumChuckHelper;

public class RaidGenerateInputActivity extends AppCompatActivity implements View.OnClickListener {

    boolean calendarComplete = false;
    Calendar raidCalendar;
    CumChuckHelper helper;
    Toolbar toolbar;
    // Input Window
    TextView headerTitle, headerAddress;
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
        headerTitle = (TextView) findViewById(R.id.raid_input_headerTitle);
        raidNameInput = (TextInputLayout) findViewById(R.id.raid_input_raidnameInput);
        cardviewDate = (TextView) findViewById(R.id.raid_input_cardview_date);
        cardviewTime = (TextView) findViewById(R.id.raid_input_cardview_time);
        setDateTime = (TextView) findViewById(R.id.raid_input_changeCalendar);
        setDateTime.setOnClickListener(this);
        personCountTrack = (TextView) findViewById(R.id.raid_input_personCountTrack);
        personCount = (Slider) findViewById(R.id.raid_input_personSlider);
        personCountTrack.setText("1명");
        personCount.setOnPositionChangeListener(new Slider.OnPositionChangeListener() {
            @Override
            public void onPositionChanged(Slider view, boolean fromUser, float oldPos, float newPos, int oldValue, int newValue) {
                personCountTrack.setText(newValue + "명");
            }
        });
    }

    private void setCalendar() {
        new DatePickerDialog(RaidGenerateInputActivity.this)
                .onDateChangedListener(new DatePickerDialog.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(int oldDay, int oldMonth, int oldYear, int newDay, int newMonth, int newYear) {
                        raidCalendar.set(newYear, newMonth, newDay);
                    }
                })
                .positiveActionClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new TimePickerDialog(RaidGenerateInputActivity.this)
                                .onTimeChangedListener(new TimePickerDialog.OnTimeChangedListener() {
                                    @Override
                                    public void onTimeChanged(int oldHour, int oldMinute, int newHour, int newMinute) {
                                        raidCalendar.set(Calendar.HOUR, newHour);
                                        raidCalendar.set(Calendar.MINUTE, newMinute);
                                    }
                                })
                                .positiveActionClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        calendarComplete = true;
                                        cardviewDate.setText(raidCalendar.get(Calendar.YEAR)+"년 "+raidCalendar.get(Calendar.MONTH)+"월 "+raidCalendar.get(Calendar.DAY_OF_MONTH));
                                        cardviewTime.setText(raidCalendar.get(Calendar.HOUR_OF_DAY)+"시 "+raidCalendar.get(Calendar.MINUTE)+"분");
                                    }
                                }).show();
                    }
                }).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            helper.showAlertDialog("꺼지려고?\n데이터 저장 안해줄거니까 판단은 알아서", new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    finish();
                }
            });
        } else finish();
        return super.onKeyDown(keyCode, event);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.raid_input_changeCalendar:
//                setCalendar();
        }
    }

}
