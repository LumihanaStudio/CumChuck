package kr.edcan.cumchuck.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import kr.edcan.cumchuck.R;
import kr.edcan.cumchuck.model.FacebookUser;
import kr.edcan.cumchuck.model.TwitterUser;
import kr.edcan.cumchuck.model.User;
import kr.edcan.cumchuck.utils.CumChuckHelper;
import kr.edcan.cumchuck.utils.CumChuckNetworkHelper;
import kr.edcan.cumchuck.utils.DataManager;
import kr.edcan.cumchuck.utils.NetworkInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity {

    LoginButton fbLogin;
    TwitterLoginButton twLogin;
    CallbackManager manager;
    NetworkInterface service;
    Call<FacebookUser> loginByFacebook;
    Call<TwitterUser> loginByTwitter;
    DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        initialize();
        validateUserToken();
    }

    private void validateUserToken() {
        Pair<Boolean, User> userPair = dataManager.getActiveUser();
        if (userPair.first == false) {
            setFacebook();
            setTwitter();
        } else {
            // validate
            switch (userPair.second.getUserType()) {
                case 0:
                    new LoadFacebookInfo().execute(dataManager.getFacebookUserCredential());
                    break;
                case 1:
                    new LoadTwitterInfo().execute(dataManager.getTwitterUserCredentials());
            }
        }
    }

    private void initialize() {
        manager = CallbackManager.Factory.create();
        dataManager = new DataManager();
        fbLogin = (LoginButton) findViewById(R.id.auth_facebookbutton);
        twLogin = (TwitterLoginButton) findViewById(R.id.auth_twitterbutton);
        dataManager.initializeManager(getApplicationContext());

        service = CumChuckNetworkHelper.getNetworkInstance();
    }

    private void setFacebook() {
        LoginManager.getInstance().registerCallback(manager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                new LoadFacebookInfo().execute(loginResult.getAccessToken().getToken());
                dataManager.saveUserCredential(loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(AuthActivity.this, "로그인 인증 중에 문제가 발생했습니다.\n서비스 관리자에게 문의해주세요.", Toast.LENGTH_SHORT).show();
                CumChuckHelper.log(AuthActivity.this.getClass().getName(), error.getMessage());
            }
        });
    }

    private void setTwitter() {
        twLogin.setCallback(new com.twitter.sdk.android.core.Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session = result.data;
                String token = session.getAuthToken().token;
                String secret = session.getAuthToken().secret;
                String id = String.valueOf(session.getId());
                new LoadTwitterInfo().execute(token, secret, id);
                dataManager.saveUserCredential(new String[]{token, secret});
            }

            @Override
            public void failure(TwitterException exception) {
                CumChuckHelper.log(getLocalClassName(), exception.getMessage());
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        manager.onActivityResult(requestCode, resultCode, data);
        twLogin.onActivityResult(requestCode, resultCode, data);
    }

    class LoadFacebookInfo extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            loginByFacebook = service.loginByFacebook(strings[0]);
            loginByFacebook.enqueue(new Callback<FacebookUser>() {
                @Override
                public void onResponse(Call<FacebookUser> call, Response<FacebookUser> response) {
                    switch (response.code()) {
                        case 200:
                            FacebookUser facebookUser = response.body();
                            dataManager.saveFacebookUserInfo(facebookUser);
                            startActivity(new Intent(AuthActivity.this, MainActivity.class));
                            Toast.makeText(AuthActivity.this, facebookUser.content.name + " 님 안녕하세요!", Toast.LENGTH_SHORT).show();
                            finish();
                            break;
                        case 401:
                            CumChuckHelper.log(AuthActivity.this.getLocalClassName(), "Unauthorized");
                            Toast.makeText(AuthActivity.this, "세션이 만료되었습니다.\n다시 로그인해주세요.", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }

                @Override
                public void onFailure(Call<FacebookUser> call, Throwable t) {
                    CumChuckHelper.log(AuthActivity.this.getLocalClassName(), t.getMessage());
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    class LoadTwitterInfo extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            String[] twitterCredientials = strings;
            loginByTwitter = service.loginByTwitter(twitterCredientials[0], twitterCredientials[1], twitterCredientials[2]);
            loginByTwitter.enqueue(new Callback<TwitterUser>() {
                @Override
                public void onResponse(Call<TwitterUser> call, Response<TwitterUser> response) {
                    switch (response.code()) {
                        case 200:
                            TwitterUser user = response.body();
                            dataManager.saveTwitterUserInfo(user);
                            CumChuckHelper.log(AuthActivity.this.getLocalClassName(), user.toString());
                            startActivity(new Intent(AuthActivity.this, MainActivity.class));
                            Toast.makeText(AuthActivity.this, user.content.name + " 님 안녕하세요!", Toast.LENGTH_SHORT).show();
                            finish();
                            break;
                        case 401:
                            CumChuckHelper.log(AuthActivity.this.getLocalClassName(), "Unauthorized");
                            Toast.makeText(AuthActivity.this, "세션이 만료되었습니다.\n다시 로그인해주세요.", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }

                @Override
                public void onFailure(Call<TwitterUser> call, Throwable t) {
                    CumChuckHelper.log(AuthActivity.this.getLocalClassName(), t.getMessage());
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}


