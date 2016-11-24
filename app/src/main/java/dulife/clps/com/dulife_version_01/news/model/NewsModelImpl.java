package dulife.clps.com.dulife_version_01.news.model;

import android.util.Log;
import com.squareup.okhttp.Request;
import java.util.List;
import dulife.clps.com.dulife_version_01.bean.NewsBean;
import dulife.clps.com.dulife_version_01.bean.NewsDetailBean;
import dulife.clps.com.dulife_version_01.commons.Urls;
import dulife.clps.com.dulife_version_01.news.NewsJsonUtil;
import dulife.clps.com.dulife_version_01.news.widget.NewsFragment;
import dulife.clps.com.dulife_version_01.util.OkHttpUtil;

/**
 * Created by ${zhangyanfu} on 2016/9/19.
 * Email : zhangyanfu66@gmail.com
 */
public class NewsModelImpl implements NewsModel {

    @Override
    public void loadNews(String url, final int type, final OnLoadNewsListListener listener) {
        Log.e("NewsModelImpl--url",url+"--");
        OkHttpUtil.ResultCallback<String> callback = new OkHttpUtil.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onSuccess(String response) {
                Log.e("NewsModelImpl-onSuccess",response+"--");
                List<NewsBean> beans = NewsJsonUtil.readJsonNewsBeans(response, getID(type));
                Log.e("NewsModelImpl",beans.size()+"beans--");
                listener.onSuccess(beans);
            }
        };
        new OkHttpUtil()._getAsyn(url,callback);
    }

    @Override
    public void loadNewsDetail(final String docid, final OnLoadNewsDetailListener listener) {

        String url = getDetailUrl(docid);
        OkHttpUtil.ResultCallback<String> loadNewsCallback = new OkHttpUtil.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onSuccess(String response) {
               NewsDetailBean bean =  NewsJsonUtil.readJsonNewsDetailBean(response,docid);
                listener.onSuccess(bean);
            }
        };
        new OkHttpUtil()._getAsyn(url,loadNewsCallback);
    }

    public interface OnLoadNewsListListener {
        void onSuccess(List<NewsBean> list);

        void onFailure(String msg, Exception e);
    }

    public interface OnLoadNewsDetailListener {
        void onSuccess(NewsDetailBean bean);

        void onFailure(String msg, Exception e);
    }

    private String getID(int type) {
        String id;
        switch (type) {
            case NewsFragment.NEWS_TYPE_TOP:
                id = Urls.TOP_ID;
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                id = Urls.NBA_ID;
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                id = Urls.CAR_ID;
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                id = Urls.JOKE_ID;
                break;
            default:
                id = null;
        }
        return id;

    }
    public String getDetailUrl(String docid)
    {
        StringBuffer buffer = new StringBuffer(Urls.NEW_DETAIL);
        buffer.append(docid).append(Urls.END_DETAIL_URL);
        return buffer.toString();
    }
}
