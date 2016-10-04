package dulife.clps.com.dulife_version_01.news;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dulife.clps.com.dulife_version_01.R;
import dulife.clps.com.dulife_version_01.bean.NewsBean;
import dulife.clps.com.dulife_version_01.image.ImageJsonUtil;

/**
 * Created by ${zhangyanfu} on 2016/9/26.
 * Email : zhangyanfu66@gmail.com
 */

public class NewsAdapter extends BaseAdapter {

    public Context mContext;
    public List<NewsBean> beans;

    public NewsAdapter(Context mContext, List<NewsBean> beans) {
        this.mContext = mContext;
        this.beans = beans;
    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public Object getItem(int i) {
        return beans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = null;
        ViewHolder viewHolder;
        NewsBean bean;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            view = View.inflate(mContext, R.layout.item_news, null);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.id_news_imageview);
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.id_title);
            viewHolder.tvDes = (TextView) view.findViewById(R.id.id_des);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        bean = beans.get(i);
        ImageJsonUtil.display(mContext,viewHolder.imageView,bean.getImgsrc());
        viewHolder.tvDes.setText(bean.getDigest());
        viewHolder.tvTitle.setText(bean.getTitle());
        return view;
    }

    class ViewHolder {
        ImageView imageView;
        TextView tvTitle;
        TextView tvDes;
    }
}
