package kr.edcan.cumchuck.utils;

import java.util.Date;
import java.util.List;

import kr.edcan.cumchuck.model.FacebookUser;
import kr.edcan.cumchuck.model.Raid;
import kr.edcan.cumchuck.model.Restaurant;
import kr.edcan.cumchuck.model.Review;
import kr.edcan.cumchuck.model.TwitterUser;
import kr.edcan.cumchuck.model.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
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
    Call<TwitterUser> loginByTwitter(@Query("oauth_token") String twitterToken,
                                     @Query("oauth_token_secret") String twitterSecretToken,
                                     @Query("user_id") String user_id);

    @GET("/auth/user/destroyUser")
    Call<String> destroyUser(@Query("userId") String userId);

    @GET("/auth/user/getFacebookFriendList")
    Call<List<User>> getFacebookFriendList(@Query("userId") String userId, @Query("access_token") String accessToken);

//    @GET("/auth/user/getTwitterFriendList")
//    Call<List<User>> getTwitterFriendList()

    @GET("/auth/user/showFriendInfo")
    Call<User> showFriendInfo(@Query("friendId") String friendId);

    //    @POST("/user/getSelfRaidStatus")
//    Call<>
    @POST("/user/userSelfInfo")
    @FormUrlEncoded
    Call<User> userSelfInfo(@Field("userType") int userType, @Field("userId") String userId);

    @POST("/user/selfReview")
    @FormUrlEncoded
    Call<List<Review>> userSelfReview(@Field("userId") String userId);

    @POST("/user/selfReview/postArticle")
    @FormUrlEncoded
    Call<Review> postReview(@Field("userId") String userId, @Field("resId") String resId, @Field("reviewTitle") String reviewTitle, @Field("reviewContent") String reviewContent, @Field("rating") double rating);

    @POST("/user/selfReview/editArticle")
    @FormUrlEncoded
    Call<Review> editReview(@Field("articleKey") String articleKey, @Field("userId") String userId, @Field("resId") String resId, @Field("reviewTitle") String reviewTitle, @Field("reviewContent") String reviewContent, @Field("rating") double rating);

    @POST("/user/selfReview/deleteArticle")
    @FormUrlEncoded
    Call<String> removeReview(@Field("userId") String userId, @Field("articleKey") String articleKey);

    @POST("/user/review/rateReview")
    @FormUrlEncoded
    Call<String> rateReview(@Field("userId") String userId, @Field("reviewId") String reviewId);

    @POST("/user/review/cancelRateReview")
    @FormUrlEncoded
    Call<String> cancelRateReview(@Field("userId") String userId, @Field("reviewId") String reviewId);

    @POST("/user/favorite")
    @FormUrlEncoded
    Call<List<Restaurant>> getFavRestaurant(@Field("userId") String userId);

    @POST("/user/favorite/addFavorite")
    @FormUrlEncoded
    Call<String> addFavoriteRestaurant(@Field("userId") String userId, @Field("resId") String resID);

    @POST("/user/favorite/removeFavorite")
    @FormUrlEncoded
    Call<String> removeFavoriteRestaurant(@Field("userId") String userId, @Field("resId") String resId);

    @GET("/restaurant/search/{place}/{query}")
    Call<List<Restaurant>> getRestaurantInfo(@Path("place") String place, @Path("query") String query);

//    @POST("/restaurant/recommend")
//    Call<List<Restaurant>> getRecommendRestaurant()

    @POST("/restaurant/ranking/byRating")
    Call<List<Restaurant>> getRestaurantByRating();

    @POST("/restaurant/ranking/byVisitCount")
    Call<List<Restaurant>> getRestaurantByVisitCount();

    @POST("/restaurant/info")
    @FormUrlEncoded
    Call<Restaurant> getRestaurantInfo(@Field("resId") String resId);

    @POST("/review/list")
    @FormUrlEncoded
    Call<List<Review>> getRestaurantReviewList(@Field("resId") String resID);

    @POST("/review/list/showArticle")
    @FormUrlEncoded
    Call<Review> getReviewArticle(@Field("reviewId") String reviewId);

    @POST("/raid/friendRaidList")
    @FormUrlEncoded
    Call<List<Raid>> getFriendRaidList(@Field("userId") String userId);

    @POST("/raid/getRaidInfo")
    @FormUrlEncoded
    Call<Raid> getRaidInfo(@Field("raidId") String raidID);

    @POST("/raid/admin/newRaid")
    @FormUrlEncoded
    Call<Raid> newRaid(@Field("title") String title, @Field("content") String content, @Field("resId") String resID, @Field("raidDate") Date date, @Field("raidMax") int raidMax);

    @POST("/raid/admin/addUser")
    @FormUrlEncoded
    Call<String> addUserToRaid(@Field("raidId") String raidId, @Field("userId") String userId, @Field("targetId") String targetId);

    @POST("/raid/admin/addUser")
    @FormUrlEncoded
    Call<String> removeUserFromRaid(@Field("raidId") String raidId, @Field("userId") String userId, @Field("targetId") String targetId);


}
