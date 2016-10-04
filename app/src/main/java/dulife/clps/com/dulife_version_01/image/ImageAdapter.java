package dulife.clps.com.dulife_version_01.image;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dulife.clps.com.dulife_version_01.R;
import dulife.clps.com.dulife_version_01.bean.ImageBean;

/**
 * Created by ${zhangyanfu} on 2016/9/18.
 * Email : zhangyanfu66@gmail.com
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private List<ImageBean> mDatas;

    public ImageAdapter(Context context, List<ImageBean> list) {
        this.mContext = context;
        this.mDatas = list;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = null;
        ViewHolder viewHolder = null;
        ImageBean bean  = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            view = View.inflate(mContext, R.layout.item_list_images, null);
            viewHolder.tvDes = (TextView) view.findViewById(R.id.id_des_tv);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.id_pic_imageview);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        bean = mDatas.get(i);
        Log.e("bean",bean.getTitle()+"---");
        viewHolder.tvDes.setText(bean.getTitle());
        ImageJsonUtil.display(mContext,viewHolder.imageView,bean.getThumburl());
        return view;
    }

    class ViewHolder {
        TextView tvDes;
        ImageView imageView;
    }
}
