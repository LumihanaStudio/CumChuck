package kr.edcan.cumchuck.model;

/**
 * Created by Chad on 7/2/16.
 */

public class Restaurant {
    private String name, address, number;
    private int resRanking;
    private double currentResRating;

    public Restaurant(String name, String address, String number, int resRanking, double currentResRating) {
        this.name = name;
        this.address = address;
        this.number = number;
        this.resRanking = resRanking;
        this.currentResRating = currentResRating;
    }

    public int getResRanking() {
        return resRanking;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getCurrentResRating() {
        return currentResRating;
    }

    public String getNumber() {
        return number;
    }
}
