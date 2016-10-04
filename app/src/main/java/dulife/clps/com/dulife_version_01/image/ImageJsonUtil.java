package dulife.clps.com.dulife_version_01.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import dulife.clps.com.dulife_version_01.R;


/**
 * Created by ${zhangyanfu} on 2016/9/18.
 * Email : zhangyanfu66@gmail.com
 */
public class ImageJsonUtil {
    public static void display(Context context, ImageView imageView, String url)
    {
        if(imageView==null)
        {
            throw new IllegalArgumentException("argument is error");
        }
        Glide.with(context).
                load(url).placeholder(R.drawable.ic_image_loading).
                error(R.drawable.ic_image_loadfail).crossFade().into(imageView);
    }

}
