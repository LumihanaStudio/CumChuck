package kr.edcan.cumchuck.activity;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.rey.material.widget.Slider;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.utils.CumChuckHelper;

public class RaidGenerateInputActivity extends AppCompatActivity implements View.OnClickListener {

    boolean isCancel = false, isTimeCancel = false;
    Calendar nowCalendar, raidCalendar;
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
        raidCalendar = Calendar.getInstance();
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
//        personCount.setOnPositionChangeListener((view, fromUser, oldPos, newPos, oldValue, newValue) -> personCountTrack.setText(newValue + "명"));
    }

    private void setDate() {
        isCancel = false;
        nowCalendar = Calendar.getInstance();
//        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
//                (view, year, monthOfYear, dayOfMonth) -> raidCalendar.set(year, monthOfYear, dayOfMonth),
//                nowCalendar.get(Calendar.YEAR),
//                nowCalendar.get(Calendar.MONTH),
//                nowCalendar.get(Calendar.DAY_OF_MONTH));
//        datePickerDialog.setThemeDark(false);
//        datePickerDialog.vibrate(true);
//        datePickerDialog.setOnCancelListener(dialog -> isCancel = true);
//        datePickerDialog.setOnDismissListener(dialog -> {
//            if(!isCancel){
//                setTime();
//            }
//        });
//        datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
    }
    private void setTime() {
        nowCalendar = Calendar.getInstance();
        final int year = raidCalendar.get(Calendar.YEAR);
        final int month = raidCalendar.get(Calendar.MONTH);
        final int day = raidCalendar.get(Calendar.DAY_OF_MONTH);
//        TimePickerDialog timePickerDialog =
//                TimePickerDialog.newInstance((view, hourOfDay, minute, second) -> {
//                    raidCalendar.set(year, month, day, hourOfDay, minute);
//                    Log.e("asdf", raidCalendar.getTimeInMillis()+"");
//                }, nowCalendar.get(Calendar.HOUR_OF_DAY), nowCalendar.get(Calendar.MINUTE), true);
//        timePickerDialog.setThemeDark(false);
//        timePickerDialog.vibrate(true);
//        timePickerDialog.setOnCancelListener(dialog -> isTimeCancel = true);
//        timePickerDialog.setOnDismissListener(dialog -> {
//            Log.e("asdf", "dialog close + "+isTimeCancel);
//            if(!isTimeCancel){
//                cardviewDate.setText(raidCalendar.get(Calendar.YEAR)+"년 "+(raidCalendar.get(Calendar.MONTH)+1)+"월 "+raidCalendar.get(Calendar.DAY_OF_MONTH)+"일");
//                cardviewTime.setText(raidCalendar.get(Calendar.HOUR_OF_DAY)+"시 "+raidCalendar.get(Calendar.MINUTE)+"분");
//            }
//        });
//        timePickerDialog.show(getFragmentManager(), "TimePickerDialog");
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//            helper.showAlertDialog("꺼지려고?\n데이터 저장 안해줄거니까 판단은 알아서", (dialog, which) -> finish());
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
                setDate();
        }
    }

}
