package kr.edcan.cumchuck.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.util.Pair;

import kr.edcan.cumchuck.model.FacebookUser;
import kr.edcan.cumchuck.model.User;

/**
 * Created by KOHA on 7/9/16.
 */

public class DataManager {
    /* Data Keys */
    public static final String USER_PROFILE_URL = "user_profile_url";
    public static final String IS_SILHOUETTE = "is_silhouette";
    public static final String HAS_ACTIVE_USER = "has_active_user";
    public static final String USER_TOKEN = "user_token";
    public static final String USER_NAME = "user_name";
    public static final String USER_GENDER = "user_gender";
    public static final String USER_ID = "user_id";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;
    public DataManager instance;

    public DataManager() {
    }

    public void initializeManager(Context c) {
        this.context = c;
        preferences = context.getSharedPreferences("CumChuck", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void save(String key, String data) {
        editor.putString(key, data);
        editor.apply();
    }

    public void saveUserToken(String token) {
        editor.putString(USER_TOKEN, token);
        editor.apply();
    }

    public void saveUserInfo(FacebookUser user) {
        editor.putBoolean(HAS_ACTIVE_USER, true);
        editor.putString(USER_GENDER, user.content.gender);
        editor.putString(USER_ID, user.content.id);
        editor.putString(USER_NAME, user.content.name);
        editor.putString(USER_PROFILE_URL, user.content.picture.data.url);
        editor.putBoolean(IS_SILHOUETTE, user.content.picture.data.is_silhouette);
        editor.apply();
    }

    public Pair<Boolean, User> getActiveUser() {
        if (preferences.getBoolean(HAS_ACTIVE_USER, false)) {
            String gender = preferences.getString(USER_GENDER, "");
            String id = preferences.getString(USER_ID, "");
            String name = preferences.getString(USER_NAME, "");
            boolean isSilhouette= preferences.getBoolean(IS_SILHOUETTE, true);
            String url = preferences.getString(USER_PROFILE_URL, "");
            User user = new User(name, id, gender, isSilhouette, url);
            return Pair.create(true, user);
        } else return Pair.create(false, null);
    }
    public void removeAllData(){
        editor.clear();
        editor.apply();
    }

    public String getString(String key) {
        return preferences.getString(key, "");
    }

    public int getInt(String key) {
        return preferences.getInt(key, 0);
    }

    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public long getLong(String key) {
        return preferences.getLong(key, 0);
    }

}
