package kr.edcan.cumchuck.model;

/**
 * Created by MalangDesktop on 2016-05-08.
 */
public class NormalPreferenceListData {
    private int image;
    private String title;
    private String content;

    public NormalPreferenceListData(int image, String title, String content) {
        this.image = image;
        this.title = title;
        this.content = content;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
