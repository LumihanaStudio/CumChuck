package kr.edcan.cumchuck.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.utils.CumChuckNetworkHelper;
import kr.edcan.cumchuck.utils.NetworkInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {

    NetworkInterface service;
    CumChuckNetworkHelper helper;

    //    ImageView imageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        imageview = (ImageView) findViewById(R.id.imageview);
//        imageview.setImageDrawable(getResources().getDrawable(R.drawable.test_animation));
//        AnimationDrawable drawable = (AnimationDrawable)imageview.getDrawable();
//        drawable.start();
    }
}
