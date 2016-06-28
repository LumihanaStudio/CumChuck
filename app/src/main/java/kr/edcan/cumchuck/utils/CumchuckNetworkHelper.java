package kr.edcan.cumchuck.utils;

import android.content.Context;

import retrofit2.Retrofit;

/**
 * Created by KOHA_DESKTOP on 2016. 6. 29..
 */
public class CumChuckNetworkHelper {
    private Context context;

    public CumChuckNetworkHelper(Context context) {
        this.context = context;
    }
    public static Retrofit retrofit;
    public static NetworkInterface getNetworkInstance(){
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://malang.moe:9000/")
                    .build();
        }
        return retrofit.create(NetworkInterface.class);
    }
}
