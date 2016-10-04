package dulife.clps.com.dulife_version_01.news.view;

import java.util.List;

import dulife.clps.com.dulife_version_01.bean.NewsBean;

/**
 * Created by ${zhangyanfu} on 2016/9/19.
 * Email : zhangyanfu66@gmail.com
 */
public interface NewsView {
    void showProgress();

    void addNews(List<NewsBean> newsList);

    void hideProgress();

    void showLoadFailMsg();

}
