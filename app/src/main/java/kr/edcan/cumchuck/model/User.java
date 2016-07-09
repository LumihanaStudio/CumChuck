package kr.edcan.cumchuck.model;

/**
 * Created by Chad on 7/9/16.
 */

public class User {
    public User(String name, String id, String gender, boolean isSilhouette, String url) {
        this.name = name;
        this.id = id;
        this.gender = gender;
        this.isSilhoutte = isSilhouette;
        this.profileurl = url;
    }

    String name, gender, profileurl, id;
    boolean isSilhoutte;

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getProfileurl() {
        return profileurl;
    }

    public String getId() {
        return id;
    }

    public boolean isSilhoutte() {
        return isSilhoutte;
    }
}
