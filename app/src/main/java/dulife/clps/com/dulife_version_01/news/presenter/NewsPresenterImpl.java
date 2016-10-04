package dulife.clps.com.dulife_version_01.news.presenter;

import android.util.Log;

import java.util.List;

import dulife.clps.com.dulife_version_01.bean.NewsBean;
import dulife.clps.com.dulife_version_01.commons.Urls;
import dulife.clps.com.dulife_version_01.news.model.NewsModel;
import dulife.clps.com.dulife_version_01.news.model.NewsModelImpl;
import dulife.clps.com.dulife_version_01.news.view.NewsView;
import dulife.clps.com.dulife_version_01.news.widget.NewsFragment;

/**
 * Created by ${zhangyanfu} on 2016/9/19.
 * Email : zhangyanfu66@gmail.com
 */
public class NewsPresenterImpl implements NewsPresenter, NewsModelImpl.OnLoadNewsListListener {
    private NewsView mNewsView;
    private NewsModel mNewsModel;

    public NewsPresenterImpl(NewsView mNewsView) {
        this.mNewsView = mNewsView;
        this.mNewsModel = new NewsModelImpl();
    }

    @Override
    public void loadNews(int type, final int page) {
        String url = getUrl(type, page);
        mNewsModel.loadNews(url, type, this);
    }

    private String getUrl(int type, int pageIndex) {
        StringBuffer buffer = new StringBuffer();

        switch (type) {
            case NewsFragment.NEWS_TYPE_TOP:
                buffer.append(Urls.COMMON_URL).append(Urls.TOP_ID);
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                buffer.append(Urls.COMMON_URL).append(Urls.NBA_ID);
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                buffer.append(Urls.COMMON_URL).append(Urls.CAR_ID);
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                buffer.append(Urls.COMMON_URL).append(Urls.JOKE_ID);
                break;
        }
        buffer.append("/").append(pageIndex).append(Urls.END_URL);
        return buffer.toString();
    }

    @Override
    public void onSuccess(List<NewsBean> list) {
        Log.e("NewsPresenter-onSuccess","--"+list.size());
        mNewsView.addNews(list);
    }

    @Override
    public void onFailure(String msg, Exception e) {

    }
}
