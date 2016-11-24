package dulife.clps.com.dulife_version_01.image.widget;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
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
import dulife.clps.com.dulife_version_01.bean.ImageBean;
import dulife.clps.com.dulife_version_01.image.presenter.ImagePresenter;
import dulife.clps.com.dulife_version_01.image.presenter.ImagePresenterImpl;
import dulife.clps.com.dulife_version_01.image.view.ImageView;

public class ImageFragment extends Fragment implements ImageView, SwipeRefreshLayout.OnRefreshListener {

    private List<ImageBean> mDatas;

    private ListView mListView;
    private ImagePresenter mImagePresenter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ImageAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public ImageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImagePresenter = new ImagePresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mImagePresenter.loadImageList();
        Log.e("createView", "--");
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        //?
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ImageAdapter(getActivity().getApplicationContext());

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        onRefresh();
        return view;
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        private int lastVisibleItem;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                    lastVisibleItem + 1 == mAdapter.getItemCount()) {
                //加载更多
                Snackbar.make(getActivity().findViewById(R.id.drawer_layout),
                        "一次只可以加载20条记录哟", Snackbar.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void addImages(List<ImageBean> list) {
        if (mDatas == null) {
            mDatas = new ArrayList<ImageBean>();
        }
        mDatas.addAll(list);
        Log.e("list--", list.size() + "--");
        mAdapter.setmData(mDatas);
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
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
        if (mDatas != null) {
            mDatas.clear();
        }
        mImagePresenter.loadImageList();
    }
}
