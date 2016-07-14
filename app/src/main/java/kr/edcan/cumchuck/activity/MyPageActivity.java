package kr.edcan.cumchuck.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.toolbox.ImageLoader;
import com.facebook.login.LoginManager;

import java.net.MalformedURLException;
import java.util.ArrayList;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.adapter.MyPageListViewAdapter;
import kr.edcan.cumchuck.model.NormalPreferenceListData;
import kr.edcan.cumchuck.model.FacebookUser;
import kr.edcan.cumchuck.model.User;
import kr.edcan.cumchuck.utils.CumChuckHelper;
import kr.edcan.cumchuck.utils.DataManager;
import kr.edcan.cumchuck.utils.ImageSingleTon;
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
    TextView changeProfile, profileName;
    User user;
    View headerView;
    DataManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        helper = new CumChuckHelper(MyPageActivity.this);
        helper.showLoadingDialog();
        setActionbar();
        loadUserData();
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

    private void loadUserData() {
        manager = new DataManager();
        manager.initializeManager(getApplicationContext());
        user = manager.getActiveUser().second;
    }

    private void setDefault() {
        headerView = getLayoutInflater().inflate(R.layout.mypage_listview_header, null);
        profileName = (TextView) headerView.findViewById(R.id.mypage_profile_name);
        listView = (ListView) findViewById(R.id.myPageListView);
        profileBackground = (ImageView) headerView.findViewById(R.id.mypage_profile_background);
        profileImageView = (RoundImageView) headerView.findViewById(R.id.mypage_profile_image);
        changeProfile = (TextView) headerView.findViewById(R.id.mypage_change_profile);
        expProgress = (SeekArc) headerView.findViewById(R.id.mypage_show_exp);
        try {
            profileImageView.setImageUrl((user.getUserType() == 0) ? CumChuckHelper.convertFacebookImgSize(user.getId(), 2) : CumChuckHelper.convertTwitterImgSize(user.getProfileurl(), 3), ImageSingleTon.getInstance(this).getImageLoader());
            CumChuckHelper.log(this.getLocalClassName(), CumChuckHelper.convertTwitterImgSize(user.getProfileurl(), 3));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            CumChuckHelper.log(this.getLocalClassName(), e.getMessage());
        }
        profileImageView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                if (profileImageView.getDrawable() != null) {
                    Bitmap bitmap = ((BitmapDrawable) profileImageView.getDrawable()).getBitmap();
                    profileBackground.setImageBitmap(helper.blur(bitmap));
                    helper.dismissLoadingDialog();
                }
            }
        });
        profileName.setText(user.getName());
        changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MyPageActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setEXP() {
        expProgress.setMax(2000);
        expProgress.setProgress(1000);
    }

    private void setListView() {
        arrayList = new ArrayList<>();
        arrayList.add(new NormalPreferenceListData(R.drawable.ic_myp_review, "개인 리뷰 관리", "내가 작성한 리뷰들을 수정하거나 삭제합니다."));
        arrayList.add(new NormalPreferenceListData(R.drawable.ic_myp_fav, "즐겨찾기 관리", "내가 즐겨찾기한 음식점을 수정하거나 삭제합니다."));
        arrayList.add(new NormalPreferenceListData(R.drawable.ic_myp_logout, "로그아웃", "Cumchuck에서 로그아웃합니다."));
        arrayList.add(new NormalPreferenceListData(R.drawable.ic_myp_goodbye, "회원 탈퇴", "Cumchuck에 있는 모든 데이터를 삭제하고 회원탈퇴합니다."));
        arrayList.add(new NormalPreferenceListData(R.drawable.ic_myp_settings, "설정", "앱의 세부 설정 및 정보를 확인합니다."));
        adapter = new MyPageListViewAdapter(this, arrayList);
        listView.setAdapter(adapter);
        listView.addHeaderView(headerView);
        listView.setOnItemClickListener(listener);
    }

    ListView.OnItemClickListener listener = new ListView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            switch (i) {
                case 1:
                    // 개인 리뷰 관리
                    break;
                case 2:
                    // 즐겨찾기 관리
                    break;
                case 3:
                    // 로그아웃
                    helper.showAlertDialog("로그아웃", "Cumchuck에서 로그아웃하시겠습니까?\n현재 진행중인 레이드에서 모두 나가집니다.", new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            LoginManager.getInstance().logOut();
                            manager.removeAllData();
                            finish();
                            startActivity(new Intent(getApplicationContext(), AuthActivity.class));
                        }
                    });
                    break;
                case 4:
                    // 회원 탈퇴
//                helper.showAlertDialog("회원탈퇴", "Cumchuck에서 완전히 탈퇴합니다", new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                    }
//                });
                    break;
                case 5:
                    // 설정
                    break;
            }
        }
    };

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
