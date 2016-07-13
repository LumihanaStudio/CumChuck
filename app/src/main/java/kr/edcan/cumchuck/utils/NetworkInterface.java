package kr.edcan.cumchuck.utils;

import kr.edcan.cumchuck.model.FacebookUser;
import kr.edcan.cumchuck.model.TwitterUser;
import kr.edcan.cumchuck.model.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @GET("/auth/twitter/token")
    Call<TwitterUser> loginByTwitter(@Query("access_token") String twitterToken);
}
