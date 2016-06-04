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
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.utils.CumChuckHelper;
import kr.edcan.cumchuck.utils.RoundImageView;
import kr.edcan.cumchuck.utils.SeekArc;


public class MyPageActivity extends AppCompatActivity {

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

    private void setDefault() {
        helper = new CumChuckHelper(getApplicationContext());
        profileBackground = (ImageView) findViewById(R.id.mypage_profile_background);
        profileImageView = (RoundImageView) findViewById(R.id.mypage_profile_image);
        changeProfile = (TextView) findViewById(R.id.mypage_change_profile);
        listView = (ListView) findViewById(R.id.myPageListView);
        expProgress = (SeekArc) findViewById(R.id.mypage_show_exp);
        Bitmap bitmap = ((BitmapDrawable) profileImageView.getDrawable()).getBitmap();
        profileBackground.setImageBitmap(helper.blur(bitmap));
        changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyPageActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
