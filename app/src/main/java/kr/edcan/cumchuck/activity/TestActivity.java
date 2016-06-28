package kr.edcan.cumchuck.activity;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

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
        setDefault();
    }

    private void setDefault() {
        service = CumChuckNetworkHelper.getNetworkInstance();
        Call<String> call = service.call("id");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                switch (response.code()){
                    case 200:
                        // Success
                        break;
                    case 400:
                        // Bad Request
                        break;
                    case 401:
                        // No Query
                        break;
                    case 409:
                        // Conflict
                        break;
                    case 500:
                        // Server Failed
                        break;
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("asdf", t.getMessage());
            }
        });
    }
}
