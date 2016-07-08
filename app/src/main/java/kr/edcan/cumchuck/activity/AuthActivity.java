package kr.edcan.cumchuck.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.FacebookActivity;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import kr.edcan.cumchuck.R;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        initialize();
    }

    private void initialize() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }
}
