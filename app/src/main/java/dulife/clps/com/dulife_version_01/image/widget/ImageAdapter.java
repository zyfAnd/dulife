package dulife.clps.com.dulife_version_01.image.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import dulife.clps.com.dulife_version_01.R;
import dulife.clps.com.dulife_version_01.bean.ImageBean;
import dulife.clps.com.dulife_version_01.image.ImageJsonUtil;
import dulife.clps.com.dulife_version_01.util.ToolUtil;

/**
 * Created by ${zhangyanfu} on 2016/10/10.
 * Email : zhangyanfu66@gmail.com
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ItemViewHolder> {
    private Context mContext;
    private List<ImageBean> mData;
    private int mMaxWidth;
    private int mMaxHeight;

    private OnItemClickListener mOnItemClickListener;

    public ImageAdapter(Context context) {
        this.mContext = context;
        mMaxWidth = ToolUtil.getWidthInPx(mContext) - 20;
        mMaxHeight = ToolUtil.getHeightInPx(mContext) - ToolUtil.getStatusHeight(mContext) -
                ToolUtil.dip2px(mContext, 96);
    }

    public void setmData(List<ImageBean> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    @Override
    public ImageAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_images, parent, false);
        ItemViewHolder mItemViewHolder = new ItemViewHolder(view);
        return mItemViewHolder;
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ItemViewHolder holder, int position) {
        Log.e("iamgeView",position+"");
        ImageBean bean = mData.get(position);
        if (bean == null) {
            return;
        }
        holder.mTitle.setText(bean.getTitle());
        float scale = (float) bean.getWidth() / (float) mMaxWidth;
        int height = (int) (bean.getHeight() / scale);
        if (height > mMaxHeight) {
            height = mMaxHeight;
        }
        holder.mImageView.setLayoutParams(new LinearLayout.LayoutParams(mMaxWidth, height));
        ImageJsonUtil.display(mContext, holder.mImageView, bean.getThumburl());
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTitle;
        public ImageView mImageView;

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, this.getPosition());
            }
        }

        public ItemViewHolder(View view) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.id_des_tv);
            mImageView = (ImageView) view.findViewById(R.id.id_pic_imageview);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
