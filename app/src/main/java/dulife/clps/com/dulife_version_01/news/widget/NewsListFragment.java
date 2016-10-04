package dulife.clps.com.dulife_version_01.news.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import dulife.clps.com.dulife_version_01.R;
import dulife.clps.com.dulife_version_01.bean.NewsBean;
import dulife.clps.com.dulife_version_01.news.NewsAdapter;
import dulife.clps.com.dulife_version_01.news.presenter.NewsPresenter;
import dulife.clps.com.dulife_version_01.news.presenter.NewsPresenterImpl;
import dulife.clps.com.dulife_version_01.news.view.NewsView;

public class NewsListFragment extends Fragment implements NewsView {

    private List<NewsBean> mData;
    private NewsPresenter mPresenter;
    private ListView mListView;
    private int pageIndex = 0;
    private int mType = NewsFragment.NEWS_TYPE_TOP;

    public static NewsListFragment newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt("type");
        mPresenter = new NewsPresenterImpl(this);
        mPresenter.loadNews(mType, pageIndex);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_news_list, null);
        mListView = (ListView) view.findViewById(R.id.id_news_listview);
        return view;

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void addNews(List<NewsBean> newsList) {
        if(mData==null)
        {
            mData = new ArrayList<>();
        }
        mData.addAll(newsList);
        mListView.setAdapter(new NewsAdapter(getContext(),mData));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(getContext(),NewsDetailActivity.class);
                intent.putExtra("NewsBean",mData.get(i));
                startActivity(intent);
            }
        });
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showLoadFailMsg() {

    }
}
