package dulife.clps.com.dulife_version_01.news.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import dulife.clps.com.dulife_version_01.R;
import dulife.clps.com.dulife_version_01.bean.NewsBean;
import dulife.clps.com.dulife_version_01.commons.Urls;
import dulife.clps.com.dulife_version_01.image.ImageJsonUtil;

public class NewsDetailActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private WebView view;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        NewsBean detail = (NewsBean) getIntent().getSerializableExtra("NewsBean");
        StringBuffer buffer = new StringBuffer(Urls.NEW_DETAIL);
        buffer.append(detail.getDocid());
        String url = buffer.toString() + Urls.END_DETAIL_URL;

        initViews();

        view.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        view.loadUrl(url);
        ImageJsonUtil.display(this, imageView, detail.getImgsrc());
//        mToolbar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
    }

    public void initViews() {
//        mToolbar = (Toolbar) findViewById(R.id.appbar);
        view = (WebView) findViewById(R.id.id_web_detail);
        imageView = (ImageView) findViewById(R.id.id_detailnews_image);
    }


}
