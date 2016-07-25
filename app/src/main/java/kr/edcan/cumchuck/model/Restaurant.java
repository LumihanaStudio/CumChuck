package kr.edcan.cumchuck.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Chad on 7/2/16.
 */

public class Restaurant {
    public String resTitle, resAddress, resId;
    @SerializedName("photo")
    public WOW wow = new WOW();

    public class WOW{
        public String suffix, prefix, id;
    }
}

