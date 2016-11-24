package dulife.clps.com.dulife_version_01.news.widget;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import dulife.clps.com.dulife_version_01.R;
import dulife.clps.com.dulife_version_01.bean.NewsBean;
import dulife.clps.com.dulife_version_01.image.ImageJsonUtil;
import dulife.clps.com.dulife_version_01.news.presenter.NewsDetailPresenter;
import dulife.clps.com.dulife_version_01.news.presenter.NewsDetailPresenterImpl;
import dulife.clps.com.dulife_version_01.news.view.NewsDetailView;
import dulife.clps.com.dulife_version_01.util.ToolUtil;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class NewsDetailActivity extends SwipeBackActivity implements NewsDetailView {

    private Toolbar mToolbar;
    private ImageView mImageView;
    private HtmlTextView htmlTextView;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private NewsDetailPresenter mNewsDetailPresenter;
    private ProgressBar mProgressBar;
    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        NewsBean bean = (NewsBean) getIntent().getSerializableExtra("NewsBean");

        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeSize(ToolUtil.getWidthInPx(this));
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        initViews();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ImageJsonUtil.display(getBaseContext(), mImageView, bean.getImgsrc());
        mCollapsingToolbarLayout.setTitle(bean.getTitle());
        mNewsDetailPresenter = new NewsDetailPresenterImpl(getApplication(), this);
        mNewsDetailPresenter.loadNewsDetail(bean.getDocid());

    }

    public void initViews() {
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mImageView = (ImageView) findViewById(R.id.ivImage);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        htmlTextView = (HtmlTextView) findViewById(R.id.htmlTextView);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
    }

    @Override
    public void showNewsDetailContent(String newsDetailContent) {
        htmlTextView.setHtmlFromString(newsDetailContent, new HtmlTextView.LocalImageGetter());
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }
}
