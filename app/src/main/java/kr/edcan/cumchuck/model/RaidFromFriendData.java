package kr.edcan.cumchuck.model;

/**
 * Created by Chad on 7/1/16.
 */
public class RaidFromFriendData {
    private String title, author_date, resTitle, resAddress;
    private int profileImage;

    public RaidFromFriendData(String title, String author_date, String resTitle, String resAddress, int profileImage) {
        this.title = title;
        this.author_date = author_date;
        this.resTitle = resTitle;
        this.resAddress = resAddress;
        this.profileImage = profileImage;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor_date() {
        return author_date;
    }

    public String getResTitle() {
        return resTitle;
    }

    public String getResAddress() {
        return resAddress;
    }

    public int getProfileImage() {
        return profileImage;
    }
}
