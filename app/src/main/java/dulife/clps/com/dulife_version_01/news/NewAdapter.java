package dulife.clps.com.dulife_version_01.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dulife.clps.com.dulife_version_01.R;
import dulife.clps.com.dulife_version_01.bean.NewsBean;
import dulife.clps.com.dulife_version_01.image.ImageJsonUtil;

/**
 * Created by ${zhangyanfu} on 2016/10/12.
 * Email : zhangyanfu66@gmail.com
 */

public class NewAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder>{
    private List<NewsBean> mData;
    private Context mContext;

    private OnItemClickListener onItemClickListener;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private boolean mShowFooter = true;

    public NewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setmData(List<NewsBean> mData) {
        this.mData = mData;
        this.notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
            ItemViewHolder itemViewHolder = new ItemViewHolder(view);
            return itemViewHolder;
        } else {
            View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer, parent, false);
            view1.setLayoutParams(new ViewGroup.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            FooterViewHolder viewHolder = new FooterViewHolder(view1);
            return viewHolder;
        }
    }

    @Override
    public int getItemCount() {
        int begin = mShowFooter?1:0;
        if(mData == null) {
            return begin;
        }
        return mData.size() + begin;
    }

    @Override
    public int getItemViewType(int position) {
        if (!mShowFooter) {
            return TYPE_ITEM;
        }
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public NewsBean getItem(int position) {
        if (mData == null) {
            return null;
        }
        return mData.get(position);
    }

    public void isShowFooter(boolean showFooter) {
        this.mShowFooter = showFooter;
    }

    public boolean ismShowFooter() {
        return this.mShowFooter;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder) {
            NewsBean bean = mData.get(position);

            if (bean == null) {
                return;
            }
            ((ItemViewHolder) holder).tvDes.setText(bean.getDigest());
            ((ItemViewHolder) holder).tvTitle.setText(bean.getTitle());
            ImageJsonUtil.display(mContext, ((ItemViewHolder) holder).mImageView, bean.getImgsrc());
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvTitle;
        public TextView tvDes;
        public ImageView mImageView;

        public ItemViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.id_title);
            tvDes = (TextView) view.findViewById(R.id.id_des);
            mImageView = (ImageView) view.findViewById(R.id.id_news_imageview);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view, this.getPosition());
            }
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}
