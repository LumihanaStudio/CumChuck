package kr.edcan.cumchuck.model;

import java.util.Date;

/**
 * Created by MalangDesktop on 2016-06-07.
 */
public class RankingDetailViewData {
    private boolean isHeader;
    private double headerRating;
    private int headerRanking;
    private boolean isFavorite;
    private String headerTitle, headerAddress, headerDescription;
    private String cardviewTitle, cardviewContent, cardviewAuthor;
    private double cardviewRating;
    private Date cardviewDate;

    public RankingDetailViewData(boolean isHeader, double headerRating, int headerRanking, boolean isFavorite, String headerTitle, String headerAddress, String headerDescription) {
        this.isHeader = isHeader;
        this.headerRating = headerRating;
        this.headerRanking = headerRanking;
        this.isFavorite = isFavorite;
        this.headerTitle = headerTitle;
        this.headerAddress = headerAddress;
        this.headerDescription = headerDescription;
    }

    public RankingDetailViewData( boolean isHeader, String cardviewTitle, String cardviewContent, String cardviewAuthor, double cardviewRating, Date cardviewDate) {
        this.cardviewTitle = cardviewTitle;
        this.cardviewContent = cardviewContent;
        this.cardviewAuthor = cardviewAuthor;
        this.cardviewRating = cardviewRating;
        this.cardviewDate = cardviewDate;
        this.isHeader = isHeader;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public double getHeaderRating() {
        return headerRating;
    }

    public int getHeaderRanking() {
        return headerRanking;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public String getHeaderAddress() {
        return headerAddress;
    }

    public String getHeaderDescription() {
        return headerDescription;
    }

    public String getCardviewTitle() {
        return cardviewTitle;
    }

    public String getCardviewContent() {
        return cardviewContent;
    }

    public String getCardviewAuthor() {
        return cardviewAuthor;
    }

    public double getCardviewRating() {
        return cardviewRating;
    }

    public Date getCardviewDate() {
        return cardviewDate;
    }
}
