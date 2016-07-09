package kr.edcan.cumchuck.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Chad on 7/4/16.
 */
public class User {
    long id;
    String gender;
    @SerializedName("_json")
    UserContent content;
}

class UserContent{
    String name;
    String gender;
    String id;
    Picture picture;
    public class Picture{
        public class Data{
            boolean is_silhouette;
            public String url;
        }
    }
}


