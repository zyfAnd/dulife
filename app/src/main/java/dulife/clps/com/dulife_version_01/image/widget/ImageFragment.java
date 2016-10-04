package dulife.clps.com.dulife_version_01.image.widget;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import dulife.clps.com.dulife_version_01.R;
import dulife.clps.com.dulife_version_01.bean.ImageBean;
import dulife.clps.com.dulife_version_01.image.ImageAdapter;
import dulife.clps.com.dulife_version_01.image.presenter.ImagePresenter;
import dulife.clps.com.dulife_version_01.image.presenter.ImagePresenterImpl;
import dulife.clps.com.dulife_version_01.image.view.ImageView;

public class ImageFragment extends Fragment implements ImageView {

    private List<ImageBean> mDatas;

    private ListView mListView;
    private ImagePresenter mImagePresenter;

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
        mListView = (ListView) view.findViewById(R.id.id_pic_listview);
        return view;
    }

    @Override
    public void addImages(List<ImageBean> list) {
        if (mDatas == null) {
            mDatas = new ArrayList<ImageBean>();
        }
        mDatas.addAll(list);
        mListView.setAdapter(new ImageAdapter(getContext(),mDatas));
        Log.e("list--", list.size() + "--");

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showLoadFailMsg() {

    }
}
