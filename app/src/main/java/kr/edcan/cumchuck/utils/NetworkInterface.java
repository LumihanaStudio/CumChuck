package kr.edcan.cumchuck.utils;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by KOHA_DESKTOP on 2016. 6. 29..
 */
public interface NetworkInterface {

    /*
    * ````APIs here
    * */
    @POST("/auth/login")
    @FormUrlEncoded
    Call<String> call(@Field("userId") String id);

}
