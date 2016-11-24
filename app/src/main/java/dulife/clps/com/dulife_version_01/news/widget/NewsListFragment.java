package dulife.clps.com.dulife_version_01.news.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import dulife.clps.com.dulife_version_01.R;
import dulife.clps.com.dulife_version_01.bean.NewsBean;
import dulife.clps.com.dulife_version_01.commons.Urls;
import dulife.clps.com.dulife_version_01.news.NewAdapter;
import dulife.clps.com.dulife_version_01.news.presenter.NewsPresenter;
import dulife.clps.com.dulife_version_01.news.presenter.NewsPresenterImpl;
import dulife.clps.com.dulife_version_01.news.view.NewsView;

public class NewsListFragment extends Fragment implements NewsView, SwipeRefreshLayout.OnRefreshListener {

    private List<NewsBean> mData;
    private NewsPresenter mPresenter;
    private ListView mListView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int pageIndex = 0;
    private int mType = NewsFragment.NEWS_TYPE_TOP;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView mRecyclerView;
    private NewAdapter adapter;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_news_list, null);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary
                , R.color.colorPrimaryDark, R.color.colorPrimary, R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new NewAdapter(getActivity().getApplicationContext());
        adapter.setOnItemClickListener(onItemClickListener);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnScrollListener(onScrollListener);
        onRefresh();
        return view;

    }

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        private int lastVisibleItem;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 ==
                    adapter.getItemCount() && adapter.ismShowFooter()) {
                mPresenter.loadNews(mType, pageIndex + Urls.PAZE_SIZE);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
        }
    };
    private NewAdapter.OnItemClickListener onItemClickListener = new NewAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            NewsBean bean = adapter.getItem(position);
            Log.e("item",position+"--");
            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
            intent.putExtra("NewsBean", bean);
            startActivity(intent);
        }
    };

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void addNews(List<NewsBean> newsList) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.addAll(newsList);
        if(pageIndex == 0) {
            Log.e("pageIndex",mData.size()+"+");
            adapter.setmData(mData);
        } else {
            //如果没有更多数据了,则隐藏footer布局
            if(newsList == null || newsList.size() == 0) {
                adapter.isShowFooter(false);
            }
            adapter.notifyDataSetChanged();
        }
        pageIndex += Urls.PAZE_SIZE;
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoadFailMsg() {
    }

    @Override
    public void onRefresh() {
        pageIndex = 0;
        if (mData!=null)
        {
            mData.clear();
        }
        mPresenter.loadNews(mType, pageIndex);
    }
}
