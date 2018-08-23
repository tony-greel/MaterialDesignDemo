package com.example.pictureview.video.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pictureview.R;
import com.example.pictureview.video.utils.VideoConstant;
import com.squareup.picasso.Picasso;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoListAdapter extends BaseAdapter {

    // 0是文本 , 1是数据
    private int[] viewtype = {0, 0, 0, 1, 0, 0, 0, 1, 0, 0}; // 两种类型 : 1 = 数据, 0 = 文本

    private Context context;
    private LayoutInflater mInflater;

    public VideoListAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return viewtype.length;
    }

    @Override
    public Object getItem(int iposition) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //This is the point
        if (convertView != null && convertView.getTag() != null && convertView.getTag() instanceof VideoHolder) {
            ((VideoHolder) convertView.getTag()).jcVideoPlayer.release();
        }

        // 如果位置是一的话显示视频
        if (getItemViewType(position) == 1) {
            VideoHolder viewHolder;

            if (convertView != null && convertView.getTag() != null && convertView.getTag() instanceof VideoHolder) {
                viewHolder = (VideoHolder) convertView.getTag();
            } else {
                viewHolder = new VideoHolder();
                convertView = mInflater.inflate(R.layout.item_videoview, null);
                viewHolder.jcVideoPlayer = (JCVideoPlayerStandard) convertView.findViewById(R.id.videoplayer);
                convertView.setTag(viewHolder);
            }
            viewHolder.jcVideoPlayer.setUp(
                    VideoConstant.videoUrls[0][position], JCVideoPlayer.SCREEN_LAYOUT_LIST,
                    VideoConstant.videoTitles[0][position]);

            Picasso.with(context)
                    .load(VideoConstant.videoThumbs[0][position])
                    .into(viewHolder.jcVideoPlayer.thumbImageView);
        } else {
            // 如果位置不是一的话显示文本

            TextViewHolder textViewHolder;
            if (convertView != null && convertView.getTag() != null && convertView.getTag() instanceof TextViewHolder) {
                textViewHolder = (TextViewHolder) convertView.getTag();
            } else {
                textViewHolder = new TextViewHolder();
                LayoutInflater mInflater = LayoutInflater.from(context);
                convertView = mInflater.inflate(R.layout.item_textview, null);
                textViewHolder.textView = (TextView) convertView.findViewById(R.id.textview);
                convertView.setTag(textViewHolder);
            }
        }
        return convertView;
    }

    /**
     * 根据位置的到对应的类型，去找不同的类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return viewtype[position];
    }

    /**
     * 的到视图类型总数
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    class VideoHolder {
        JCVideoPlayerStandard jcVideoPlayer;
    }

    class TextViewHolder {
        TextView textView;
    }
}


