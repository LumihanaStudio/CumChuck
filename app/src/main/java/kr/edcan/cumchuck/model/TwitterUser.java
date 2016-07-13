package kr.edcan.cumchuck.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Chad on 7/4/16.
 */
public class TwitterUser {
    @SerializedName("_json")
    public UserContent content = new UserContent();

    public class UserContent {
        public String name;
        public String gender;
        public String id;
        public Picture picture = new Picture();
    }

    public class Picture {
        public Data data = new Data();
    }

    public class Data {
        public boolean is_silhouette;
        public String url;
    }
}