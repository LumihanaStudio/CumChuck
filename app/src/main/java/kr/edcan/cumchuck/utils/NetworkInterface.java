package kr.edcan.cumchuck.utils;

import kr.edcan.cumchuck.model.FacebookUser;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by KOHA_DESKTOP on 2016. 6. 29..
 */
public interface NetworkInterface {

    /*
    * ````APIs here
    * */
    @GET("/auth/facebook/token")
    Call<FacebookUser> loginByFacebook(@Query("access_token") String token);
}
