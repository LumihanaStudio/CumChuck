package kr.edcan.cumchuck.activity;

import android.app.Application;

import com.facebook.FacebookSdk;

/**
 * Created by Chad on 7/8/16.
 */

public class ApplicationLauncher extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
