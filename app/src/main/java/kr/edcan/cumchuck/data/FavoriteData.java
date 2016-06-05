package kr.edcan.cumchuck.data;

/**
 * Created by MalangDesktop on 2016-06-04.
 */
public class FavoriteData {
    private String title, address, content;
    private double star;
    private boolean isFavorite;

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public String getContent() {
        return content;
    }

    public double getStar() {
        return star;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public FavoriteData(boolean isFavorite, String title, String address, String content, double star) {
        this.isFavorite = isFavorite;
        this.title = title;
        this.address = address;
        this.content = content;
        this.star = star;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
