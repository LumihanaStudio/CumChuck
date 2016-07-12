package kr.edcan.cumchuck.activity;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;
import kr.edcan.cumchuck.R;

/**
 * Created by Chad on 7/8/16.
 */

public class ApplicationLauncher extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        TwitterAuthConfig authConfig = new TwitterAuthConfig(getResources().getString(R.string.twitter_apikey), getResources().getString(R.string.twitter_apikey_secret));
        Fabric.with(this, new Twitter(authConfig));
    }
}
