package kr.edcan.cumchuck.activity;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.rey.material.widget.Slider;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.model.Raid;
import kr.edcan.cumchuck.model.Restaurant;
import kr.edcan.cumchuck.utils.CumChuckHelper;
import kr.edcan.cumchuck.utils.CumChuckNetworkHelper;
import kr.edcan.cumchuck.utils.DataManager;
import kr.edcan.cumchuck.utils.NetworkInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RaidGenerateInputActivity extends AppCompatActivity implements View.OnClickListener {

    boolean isCancel = false, isTimeCancel = false;
    Calendar nowCalendar, raidCalendar;
    CumChuckHelper helper;
    Toolbar toolbar;
    // Input Window
    TextView headerTitle, headerAddress;
    TextInputLayout raidNameInput, raidContentInput;
    TextView cardviewDate, cardviewTime, setDateTime, personCountTrack, raidConfirm;
    Slider personCount;
    NetworkInterface service;
    Call<Restaurant> getRestaurantInfo;
    Call<Raid> newRaid;
    Call<String> raidLength;
    DataManager manager;
    Intent intent;
    String resId;
    int raidId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raid_generate_input);
        startActivity(new Intent(getApplicationContext(), LoadingActivity.class));
        setAppbarLayout();
        intent = getIntent();

        setNetwork();
        setDefault();
    }

    private void setNetwork() {
        manager = new DataManager();
        manager.initializeManager(this);
        resId = intent.getStringExtra("resId");
        service = CumChuckNetworkHelper.getNetworkInstance();
        raidLength = service.getRaidLength();
        raidLength.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                switch (response.code()) {
                    case 200:
                        raidId = Integer.parseInt(response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("asdf", t.getMessage());
            }
        });
        getRestaurantInfo = service.getRestaurantInfo(resId);
        getRestaurantInfo.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                switch (response.code()) {
                    case 200:
                        Restaurant r = response.body();
                        headerTitle.setText(r.resTitle);
                        headerAddress.setText(r.resAddress);
                        break;
                }
                LoadingActivity.finishNow();
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
                Log.e("asdf", t.getMessage());
            }
        });
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
        raidConfirm = (TextView) findViewById(R.id.raid_input_raidConfirm);
        raidConfirm.setOnClickListener(this);
        raidContentInput = (TextInputLayout) findViewById(R.id.raid_input_raidcontentinput);
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

    private void setDate() {
        isCancel = false;
        nowCalendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        raidCalendar.set(year, monthOfYear, dayOfMonth);
                    }
                },
                nowCalendar.get(Calendar.YEAR),
                nowCalendar.get(Calendar.MONTH),
                nowCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setThemeDark(false);
        datePickerDialog.vibrate(true);
        datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                isCancel = true;
            }
        });
        datePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (!isCancel) {
                    RaidGenerateInputActivity.this.setTime();
                }
            }
        });
        datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
    }

    private void setTime() {
        nowCalendar = Calendar.getInstance();
        final int year = raidCalendar.get(Calendar.YEAR);
        final int month = raidCalendar.get(Calendar.MONTH);
        final int day = raidCalendar.get(Calendar.DAY_OF_MONTH);
        TimePickerDialog timePickerDialog;
        timePickerDialog = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
                raidCalendar.set(year, month, day, hourOfDay, minute);
            }
        }, nowCalendar.get(Calendar.HOUR_OF_DAY), nowCalendar.get(Calendar.MINUTE), true);
        timePickerDialog.setThemeDark(false);
        timePickerDialog.vibrate(true);
        timePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                isTimeCancel = true;
            }
        });
        timePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Log.e("asdf", "dialog close + " + isTimeCancel);
                if (!isTimeCancel) {
                    cardviewDate.setText(raidCalendar.get(Calendar.YEAR) + "년 " + (raidCalendar.get(Calendar.MONTH) + 1) + "월 " + raidCalendar.get(Calendar.DAY_OF_MONTH) + "일");
                    cardviewTime.setText(raidCalendar.get(Calendar.HOUR_OF_DAY) + "시 " + raidCalendar.get(Calendar.MINUTE) + "분");
                }
            }
        });
        timePickerDialog.show(getFragmentManager(), "TimePickerDialog");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            helper.showAlertDialog("취소하시겠습니까?\n입력한 데이터는 저장되지 않습니다.", new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    RaidGenerateInputActivity.this.finish();
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
                setDate();
                break;
            case R.id.raid_input_raidConfirm:
                helper.showAlertDialog("레이드 생성", raidNameInput.getEditText().getText().toString().trim()
                        + "[" + raidContentInput.getEditText().getText().toString().trim() + "]\n입력하신 정보로 레이드를 생성합니다.", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        newRaid = service.newRaid(raidNameInput.getEditText().getText().toString().trim(),
                                raidContentInput.getEditText().getText().toString().trim(),
                                manager.getActiveUser().second.getId(), resId, raidCalendar.getTime(), personCount.getValue(), raidId);
                        Log.e("asdf raidinputid", raidId + "");
                        newRaid.enqueue(new Callback<Raid>() {
                            @Override
                            public void onResponse(Call<Raid> call, Response<Raid> response) {
                                switch (response.code()) {
                                    case 200:
                                        Log.e("asdf", response.body().getId()+"");
                                        Toast.makeText(RaidGenerateInputActivity.this, "레이드 생성에 성공했습니다!", Toast.LENGTH_SHORT).show();
                                        RaidGenerateInputActivity.this.finish();
                                        startActivity(new Intent(getApplicationContext(), RaidInfoShowActivity.class).putExtra("raidId", response.body().getId()));
                                        break;
                                    case 406:
                                        Toast.makeText(RaidGenerateInputActivity.this, "이미 진행중인 레이드가 있습니다.", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }

                            @Override
                            public void onFailure(Call<Raid> call, Throwable t) {
                                Log.e("asdf", t.getMessage());
                            }
                        });
                    }
                });
                break;

        }
    }

}
