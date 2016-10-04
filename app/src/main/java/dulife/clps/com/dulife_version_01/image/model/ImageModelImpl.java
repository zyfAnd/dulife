package dulife.clps.com.dulife_version_01.image.model;

import android.util.Log;

import com.squareup.okhttp.Request;

import java.util.List;

import dulife.clps.com.dulife_version_01.bean.ImageBean;
import dulife.clps.com.dulife_version_01.commons.Urls;
import dulife.clps.com.dulife_version_01.util.ImageLoadUtil;
import dulife.clps.com.dulife_version_01.util.OkHttpUtil;

/**
 * Created by ${zhangyanfu} on 2016/9/17.
 * Email : zhangyanfu66@gmail.com
 */
public class ImageModelImpl implements ImageModel {


    public interface OnLoadImageListListener
    {
        void onSuccess(List<ImageBean> list);
        void onFailure(String msg, Exception e);
    }

    @Override
    public void loadImageList(final OnLoadImageListListener listener) {

        Log.e("ImageModelImpl","loadImageList");
        String url = Urls.IMAGE_URL;
        OkHttpUtil.ResultCallback<String> callback = new OkHttpUtil.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.e("ImageModelImpl","onError");
            }

            @Override
            public void onSuccess(String response) {
                Log.e("ImageModelImpl","onSuccess");
               List<ImageBean> imageList = ImageLoadUtil.readJsonImageBeans(response);
                Log.e("ImageModelImpl",imageList.size()+"imageList的大小");
                listener.onSuccess(imageList);
            }
        };
        new OkHttpUtil()._getAsyn(url,callback);
    }
}
