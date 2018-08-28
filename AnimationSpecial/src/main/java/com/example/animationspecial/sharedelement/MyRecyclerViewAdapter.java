package com.example.animationspecial.sharedelement;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.animationspecial.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{

    private Context context;

    public MyRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).
                inflate(R.layout.item_recycler_share_elements,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                ActivityOptionsCompat activityOptionsCompat = null;
                if (position % 2 == 0){
                    Pair<View , String> pair_1 = new Pair<View, String>(holder.mImageView ,
                            context.getString(R.string.share_element_imageview));

                    Pair<View , String> pair_2 = new Pair<View, String>(holder.mHeader ,
                            context.getString(R.string.share_element_header));

                    Pair<View , String> pair_3 = new Pair<View, String>(holder.mTextView ,
                            context.getString(R.string.share_element_tv_info));

                    activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(((Activity) context), pair_1, pair_1, pair_1);

                }else {
                    final Pair<View , String>[] pairs = TransitionHelper.createSafeTransitionParticipants(
                            ((Activity) context), true,
                            new Pair<>(holder.mImageView, context.getString(R.string.share_element_imageview)),
                            new Pair<>(holder.mHeader, context.getString(R.string.share_element_header)),
                            new Pair<>(holder.mTextView, context.getString(R.string.share_element_tv_info)));
                    activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(((Activity) context), pairs);
                }

                Intent intent = new Intent(context, ShareElementsActivity.class);
                context.startActivity(intent, activityOptionsCompat.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        ImageView mImageView;
        CircleImageView mHeader;
        TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            itemView = view;
            mImageView = view.findViewById(R.id.imageview);
            mHeader = view.findViewById(R.id.header);
            mTextView = view.findViewById(R.id.tv_info);
        }
    }
}
