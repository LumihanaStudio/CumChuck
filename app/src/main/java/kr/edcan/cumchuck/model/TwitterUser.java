package kr.edcan.cumchuck.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Chad on 7/4/16.
 */
public class TwitterUser {
    @SerializedName("_json")
    public UserContent content = new UserContent();
    public class UserContent{
        @SerializedName("id_str")
        public String id;
        public String name;
        public boolean default_profile;
        public String profile_image_url;
    }
}

