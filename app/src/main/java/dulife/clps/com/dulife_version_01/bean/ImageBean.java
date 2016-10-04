package dulife.clps.com.dulife_version_01.bean;

import java.io.Serializable;

/**
 * Created by ${zhangyanfu} on 2016/9/17.
 * Email : zhangyanfu66@gmail.com
 */
public class ImageBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String thumburl;
    private String sourceurl;
    private int width;
    private int height;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumburl() {
        return thumburl;
    }

    public void setThumburl(String thumburl) {
        this.thumburl = thumburl;
    }

    public String getSourceurl() {
        return sourceurl;
    }

    public void setSourceurl(String sourceurl) {
        this.sourceurl = sourceurl;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
