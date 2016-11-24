package dulife.clps.com.dulife_version_01.image.presenter;

import android.util.Log;

import java.util.List;

import dulife.clps.com.dulife_version_01.bean.ImageBean;
import dulife.clps.com.dulife_version_01.image.model.ImageModel;
import dulife.clps.com.dulife_version_01.image.model.ImageModelImpl;
import dulife.clps.com.dulife_version_01.image.view.ImageView;

/**
 * Created by ${zhangyanfu} on 2016/9/17.
 * Email : zhangyanfu66@gmail.com
 */
public class ImagePresenterImpl implements ImagePresenter, ImageModelImpl.OnLoadImageListListener {
    private ImageModel mImageModel;
    private ImageView imageView;

    public ImagePresenterImpl(ImageView mImageView) {
        this.mImageModel = new ImageModelImpl();
        imageView = mImageView;
    }

    @Override
    public void loadImageList() {
        imageView.showLoadFailMsg();
        Log.e("ImagePresenterImpl", "loadImageList");
        mImageModel.loadImageList(this);
    }

    @Override
    public void onSuccess(List<ImageBean> list) {
        imageView.addImages(list);
        imageView.hideProgress();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        imageView.hideProgress();
        imageView.showLoadFailMsg();
    }
}
