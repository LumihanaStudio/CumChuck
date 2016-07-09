package kr.edcan.cumchuck.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Chad on 7/4/16.
 */
public class FacebookUser {
    @SerializedName("_json")
    public UserContent content;

    public class UserContent {
        public String name;
        public String gender;
        public String id;
        public Picture picture;
    }

    public class Picture {
        public Data data;
    }

    public class Data {
        public boolean is_silhouette;
        public String url;
    }
}