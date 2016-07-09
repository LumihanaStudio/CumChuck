package kr.edcan.cumchuck.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookActivity;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.utils.CumChuckNetworkHelper;
import kr.edcan.cumchuck.utils.NetworkInterface;

public class AuthActivity extends AppCompatActivity {

    LoginButton fbLogin;
    CallbackManager manager;
    NetworkInterface service;
    Call<>
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        initialize();
        setDefault();
    }

    private void setDefault() {
        fbLogin = (LoginButton) findViewById(R.id.auth_facebookbutton);
        LoginManager.getInstance().registerCallback(manager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("asdf", loginResult.getAccessToken().getToken());
                Log.e("asdf", loginResult.getAccessToken().getUserId());
                Log.e("asdf", loginResult.getAccessToken().getApplicationId());
            }

            @Override
            public void onCancel() {
                Log.e("asdf", "canceled");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("asdf", error.getMessage());

            }
        });
    }

    private void initialize() {
        AppEventsLogger.activateApp(this);
        manager = CallbackManager.Factory.create();

        service = CumChuckNetworkHelper.getNetworkInstance();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        manager.onActivityResult(requestCode, resultCode, data);
    }
}
