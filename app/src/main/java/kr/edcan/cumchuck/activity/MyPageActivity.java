package kr.edcan.cumchuck.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.adapter.MyPageListViewAdapter;
import kr.edcan.cumchuck.data.NormalPreferenceListData;
import kr.edcan.cumchuck.utils.CumChuckHelper;
import kr.edcan.cumchuck.utils.RoundImageView;
import kr.edcan.cumchuck.utils.SeekArc;


public class MyPageActivity extends AppCompatActivity {

    ArrayList<NormalPreferenceListData> arrayList;
    MyPageListViewAdapter adapter;
    SeekArc expProgress;
    CumChuckHelper helper;
    ImageView profileBackground;
    RoundImageView profileImageView;
    ListView listView;
    Toolbar toolbar;
    TextView changeProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        setActionbar();
        setDefault();
        setEXP();
        setListView();
    }

    private void setActionbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        getSupportActionBar().setTitle("마이페이지");
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable drawable = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        drawable.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }

    private void setEXP() {
        expProgress.setMax(2000);
        expProgress.setProgress(1000);
    }

    private void setListView() {
        arrayList = new ArrayList<>();
        arrayList.add(new NormalPreferenceListData(R.drawable.ic_myp_review, "개인 리뷰 관리", "내가 작성한 리뷰들을 수정하거나 삭제합니다."));
        arrayList.add(new NormalPreferenceListData(R.drawable.ic_myp_fav, "즐겨찾기 관리", "내가 즐겨찾기한 음식점을 수정하거나 삭제합니다."));
        arrayList.add(new NormalPreferenceListData(R.drawable.ic_myp_goodbye, "회원 탈퇴", "Cumchuck에 있는 모든 데이터를 삭제하고 회원탈퇴합니다."));
        adapter = new MyPageListViewAdapter(this, arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(listener);
    }

    ListView.OnItemClickListener listener = (parent, view, position, id) -> {
        switch (position){
            case 0:
                // 개인 리뷰 관리
                break;
            case 1:
                // 즐겨찾기 관리
                break;
            case 2:
                // 회원 탈퇴
                break;
        }
    };
    private void setDefault() {
        listView = (ListView) findViewById(R.id.myPageListView);
        helper = new CumChuckHelper(getApplicationContext());
        profileBackground = (ImageView) findViewById(R.id.mypage_profile_background);
        profileImageView = (RoundImageView) findViewById(R.id.mypage_profile_image);
        changeProfile = (TextView) findViewById(R.id.mypage_change_profile);
        listView = (ListView) findViewById(R.id.myPageListView);
        expProgress = (SeekArc) findViewById(R.id.mypage_show_exp);
        Bitmap bitmap = ((BitmapDrawable) profileImageView.getDrawable()).getBitmap();
        profileBackground.setImageBitmap(helper.blur(bitmap));
        changeProfile.setOnClickListener(v -> Toast.makeText(MyPageActivity.this, "", Toast.LENGTH_SHORT).show());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
