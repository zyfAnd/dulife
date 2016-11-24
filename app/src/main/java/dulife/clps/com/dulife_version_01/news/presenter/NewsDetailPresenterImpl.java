package dulife.clps.com.dulife_version_01.news.presenter;

import android.content.Context;

import dulife.clps.com.dulife_version_01.bean.NewsDetailBean;
import dulife.clps.com.dulife_version_01.news.model.NewsModel;
import dulife.clps.com.dulife_version_01.news.model.NewsModelImpl;
import dulife.clps.com.dulife_version_01.news.view.NewsDetailView;

/**
 * Created by ${zhangyanfu} on 2016/10/5.
 * Email : zhangyanfu66@gmail.com
 */

public class NewsDetailPresenterImpl implements NewsDetailPresenter, NewsModelImpl.OnLoadNewsDetailListener {
    private Context mContext;
    private NewsDetailView mNewsDetailView;
    private NewsModel mNewsModel;

    public NewsDetailPresenterImpl(Context mContext, NewsDetailView mNewsDetailView) {
        this.mContext = mContext;
        this.mNewsDetailView = mNewsDetailView;
        this.mNewsModel = new NewsModelImpl();
    }

    @Override
    public void loadNewsDetail(String docid) {
        mNewsModel.loadNewsDetail(docid, this);
    }

    @Override
    public void onSuccess(NewsDetailBean bean) {
        if (bean != null) {
            mNewsDetailView.showNewsDetailContent(bean.getBody());
            mNewsDetailView.hideProgress();
        }
    }

    @Override
    public void onFailure(String msg, Exception e) {

    }
}
