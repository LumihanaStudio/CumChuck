package kr.edcan.cumchuck.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.utils.CumChuckHelper;
import kr.edcan.cumchuck.utils.RoundImageView;

public class MyPageActivity extends AppCompatActivity {

    CumChuckHelper helper;
    ImageView profileBackground;
    RoundImageView profileImageView;
    ListView listView;
    TextView changeProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        setActionbar();
        setDefault();
    }

    private void setActionbar() {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        getSupportActionBar().setTitle("마이페이지");
        getSupportActionBar().setElevation(0);
    }

    private void setDefault() {
        helper = new CumChuckHelper(getApplicationContext());
        profileBackground = (ImageView) findViewById(R.id.mypage_profile_background);
        profileImageView = (RoundImageView) findViewById(R.id.mypage_profile_image);
        changeProfile = (TextView)findViewById(R.id.mypage_change_profile);
        listView = (ListView) findViewById(R.id.myPageListView);
        
        Bitmap bitmap = ((BitmapDrawable)profileImageView.getDrawable()).getBitmap();
        profileBackground.setImageBitmap(helper.blur(bitmap));
        changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyPageActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
