package kr.edcan.cumchuck.model;

/**
 * Created by MalangDesktop on 2016-06-04.
 */
public class CommonRecycleData {
    private String title, address, content, resId;
    private double rating;
    private int rankingCount, visitorsCount;
    private boolean isFavorite;
    private String url;

    public String getResId() {
        return resId;
    }

    public CommonRecycleData(String title, String address, String content, double rating, boolean isFavorite, int rankingCount) {
        /*
        * 0: RankingShowActivity : Sort by Ranking*/
        this.title = title;
        this.address = address;
        this.content = content;
        this.rating = rating;
        this.isFavorite = isFavorite;
        this.rankingCount = rankingCount;

    }

    public CommonRecycleData(String title, String address, String content, int rankingCount, int visitorsCount, boolean isFavorite) {
        /*
        * 1: RankingShowActivity : Sort by VisitCount*/
        this.title = title;
        this.address = address;
        this.content = content;
        this.rankingCount = rankingCount;
        this.visitorsCount = visitorsCount;
        this.isFavorite = isFavorite;
    }



    public CommonRecycleData(String title, String address, String content, boolean isFavorite, double rating) {
        /*
        * 2: RecommendActivity : Full View*/
        this.title = title;
        this.address = address;
        this.content = content;
        this.isFavorite = isFavorite;
        this.rating = rating;
    }

    public CommonRecycleData(String title, String address, double rating, String uRl, String resId) {
        /*
        * 3: RaidGenerateActivity*/
        this.address = address;
        this.title = title;
        this.rating = rating;
        this.url = url;
        this.resId = resId;
    }


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

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getUrl() {
        return url;
    }
}
