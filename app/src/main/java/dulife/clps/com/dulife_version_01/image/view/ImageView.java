package dulife.clps.com.dulife_version_01.image.view;

import java.util.List;

import dulife.clps.com.dulife_version_01.bean.ImageBean;


/**
 * Created by ${zhangyanfu} on 2016/9/17.
 * Email : zhangyanfu66@gmail.com
 */
public interface ImageView {
    void addImages(List<ImageBean> list);
    void showProgress();
    void hideProgress();
    void showLoadFailMsg();
}
