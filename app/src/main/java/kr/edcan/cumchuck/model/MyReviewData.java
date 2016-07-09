package kr.edcan.cumchuck.model;

import java.util.Date;

/**
 * Created by Chad on 7/2/16.
 */

public class MyReviewData {
    private String reviewid;
    private String title, reviewcontent;
    private double rating;
    private Restaurant res;
    private User user;
    private Date date;

    public MyReviewData(String reviewId, String title, String reviewcontent, double rating, Restaurant res, User user, Date date) {
        this.reviewid = reviewId;
        this.title = title;
        this.reviewcontent = reviewcontent;
        this.rating = rating;
        this.res = res;
        this.user = user;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getReviewid() {
        return reviewid;
    }

    public String getReviewcontent() {
        return reviewcontent;
    }

    public double getRating() {
        return rating;
    }

    public Restaurant getRes() {
        return res;
    }

    public User getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }
}
