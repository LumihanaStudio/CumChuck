package kr.edcan.cumchuck.model;

/**
 * Created by Chad on 7/4/16.
 */
public class User {
    private String id, name, apikey;

    public User(String name, String apikey) {
        this.name = name;
        this.apikey = apikey;
    }

    public User(String id, String name, String apikey) {
        this.id = id;
        this.name = name;
        this.apikey = apikey;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getApikey() {
        return apikey;
    }
}
