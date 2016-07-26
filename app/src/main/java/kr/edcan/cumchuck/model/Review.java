package kr.edcan.cumchuck.model;

import java.util.Date;

/**
 * Created by Chad on 7/17/16.
 */
public class Review {
    String writer, user_id, restrauntId, reviewTitle, reviewContent;
    Date uploadDate;
    double reviewScore;

    public String getWriter() {
        return writer;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getRestrauntId() {
        return restrauntId;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public double getReviewScore() {
        return reviewScore;
    }
}
