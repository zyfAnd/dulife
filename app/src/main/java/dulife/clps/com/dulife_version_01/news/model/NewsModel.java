package dulife.clps.com.dulife_version_01.news.model;

/**
 * Created by ${zhangyanfu} on 2016/9/19.
 * Email : zhangyanfu66@gmail.com
 */
public interface NewsModel {
    void loadNews(String url,int type,NewsModelImpl.OnLoadNewsListListener listener);
    void loadNewsDetail(String url,NewsModelImpl.OnLoadNewsDetailListener listener);
}
