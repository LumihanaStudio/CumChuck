package kr.edcan.cumchuck.data;

/**
 * Created by MalangDesktop on 2016-06-04.
 */
public class CommonRecycleData {
    private String title, address, content;
    private double rating;
    private int rankingCount, visitorsCount;
    private boolean isFavorite;

    public CommonRecycleData(String title, String address, String content, double rating, int rankingCount, int visitorsCount, boolean isFavorite) {
        this.title = title;
        this.address = address;
        this.content = content;
        this.rating = rating;
        this.rankingCount = rankingCount;
        this.visitorsCount = visitorsCount;
        this.isFavorite = isFavorite;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public String getContent() {
        return content;
    }

    public double getRating() {
        return rating;
    }

    public int getRankingCount() {
        return rankingCount;
    }

    public int getVisitorsCount() {
        return visitorsCount;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
}
