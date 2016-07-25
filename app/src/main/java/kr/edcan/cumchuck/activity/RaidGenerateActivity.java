package kr.edcan.cumchuck.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.adapter.CommonRecyclerAdapter;
import kr.edcan.cumchuck.model.CommonRecycleData;
import kr.edcan.cumchuck.model.DummyRest;
import kr.edcan.cumchuck.model.Restaurant;
import kr.edcan.cumchuck.utils.CumChuckHelper;
import kr.edcan.cumchuck.utils.CumChuckNetworkHelper;
import kr.edcan.cumchuck.utils.NetworkInterface;
import kr.edcan.cumchuck.utils.RoundImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RaidGenerateActivity extends AppCompatActivity {
    private CumChuckHelper helper;
    // Toolbar
    private Toolbar toolbar;
    //Animation
    private Animation fadeinAnim, fadeoutAnim;
    // View shown
    private LinearLayout defaultView;
    private RecyclerView raidGenerateView;
    // EditText to input search
    private EditText searchQuery;
    // boolean value to check state
    private boolean isFirst = false, isAvailable = false;
    private ImageView searchButton;

    NetworkInterface service;
    Call<List<DummyRest>> search;
    ArrayList<CommonRecycleData> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raid_generate);
        service = CumChuckNetworkHelper.getNetworkInstance();
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

        raidGenerateView = (RecyclerView) findViewById(R.id.raid_generate_recyclerview);
        searchQuery = (EditText) findViewById(R.id.raid_generate_searchQuery);
        defaultView = (LinearLayout) findViewById(R.id.raid_generate_defaultView);
        searchButton = (ImageView) findViewById(R.id.raid_generate_searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();

            }
        });
        fadeinAnim = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        fadeoutAnim = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
    }

    private void search() {
        arrayList = new ArrayList<>();
        String s = searchQuery.getText().toString().trim();
        if (s.equals("")) {
            searchQuery.setError("음식점 이름을 입력해주세요!");
            searchQuery.setText("");
        } else {
            startActivity(new Intent(getApplicationContext(), LoadingActivity.class));
            InputMethodManager imm = (InputMethodManager) searchQuery.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(searchQuery.getWindowToken(), 0);
            search = service.searchRestaurant(searchQuery.getText().toString().trim());
            search.enqueue(new Callback<List<DummyRest>>() {
                @Override
                public void onResponse(Call<List<DummyRest>> call, Response<List<DummyRest>> response) {
                    Log.e("asdf", response.code() + "");
                    switch (response.code()) {
                        case 200:
                            List<DummyRest> r = response.body();
                            isFirst = false;
                            if (r.size() == 0) {
                                isAvailable = false;
                                setView();
                                LoadingActivity.finishNow();
                            } else {
                                isAvailable = true;
                                setView();
                                LinearLayoutManager manager = new LinearLayoutManager(RaidGenerateActivity.this);
                                raidGenerateView.setHasFixedSize(true);
                                raidGenerateView.setLayoutManager(manager);
                                new getRestaurantInfo(response.body()).execute();
                            }
                    }
                }

                @Override
                public void onFailure(Call<List<DummyRest>> call, Throwable t) {
                    Log.e("asdf", t.getMessage());
                }
            });
        }
    }

    private View.OnClickListener cardClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView resId = (TextView) v.findViewById(R.id.common_resId);
            startActivity(new Intent(getApplicationContext(), RaidGenerateInputActivity.class).putExtra("resId", resId.getText().toString().trim()));
        }
    };

    private void setView() {
        TextView title = (TextView) findViewById(R.id.raid_generate_defaultViewTitle);
        TextView content = (TextView) findViewById(R.id.raid_generate_defaultViewContent);
        ImageView statusImage = (ImageView) findViewById(R.id.raid_generate_defaultViewImage);
        /*
        * 1. if isFirst = false, must find restaurant first
        * 2. if isAvailable = false, no searched restaurant
        * 3. if isAvailable, show input view*/
        if (isFirst) {
            defaultView.setVisibility(View.VISIBLE);
            raidGenerateView.setVisibility(View.GONE);
        } else if (!isAvailable) {
            statusImage.setImageDrawable(getResources().getDrawable(R.drawable.chara_searchplace_notsearched));
            title.setText("검-색된 음식점이 없습니다!");
            content.setText("다른 검색어로 시도해보세요!");
        } else if (isAvailable) {
            defaultView.setVisibility(View.GONE);
            raidGenerateView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (isAvailable) {
                    helper.showAlertDialog("레이드 생성을 취소하고 뒤로 돌아가시겠습니까?", new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            finish();
                        }
                    });
                } else finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (isAvailable) {
                helper.showAlertDialog("레이드 생성을 취소하고 뒤로 돌아가시겠습니까?", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        finish();
                    }
                });
            } else finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    class getRestaurantInfo extends AsyncTask<String, String, ArrayList<CommonRecycleData>> {

        public List<DummyRest> resid;

        public getRestaurantInfo(List<DummyRest> arr) {
            this.resid = arr;
        }

        @Override
        protected ArrayList<CommonRecycleData> doInBackground(String... strings) {
            final ArrayList<CommonRecycleData> arr = new ArrayList<>();
            for (DummyRest r : resid) {
                Call<Restaurant> getInfo = service.getRestaurantInfo(r.getId());
                getInfo.enqueue(new Callback<Restaurant>() {
                    @Override
                    public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                        Log.e("asdf", response.body().resId+ "");
                        Restaurant r = response.body();
                        arrayList.add(new CommonRecycleData(r.resTitle, r.resAddress, 0.0, ""));
                        if (arrayList.size() == resid.size()) {
                            CommonRecyclerAdapter adapter = new CommonRecyclerAdapter(RaidGenerateActivity.this, 3, arrayList, cardClickListener);
                            raidGenerateView.setAdapter(adapter);
                            LoadingActivity.finishNow();
                        }
                    }

                    @Override
                    public void onFailure(Call<Restaurant> call, Throwable t) {
                        Log.e("asdf", t.getMessage());
                    }
                });
            }
            return arr;
        }

        @Override
        protected void onPostExecute(ArrayList<CommonRecycleData> commonRecycleDatas) {
            super.onPostExecute(commonRecycleDatas);
        }
    }
}
