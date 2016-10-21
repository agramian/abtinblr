package com.abtingramian.abtinblr.feature.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abtingramian.abtinblr.R;
import com.abtingramian.abtinblr.model.PostItem;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostsRecyclerViewAdapter extends RecyclerView.Adapter<PostsRecyclerViewAdapter.ViewHolder> {

    private List<PostItem> postList = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHolder viewHolder = new ViewHolder(inflater.inflate(R.layout.item_post, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (postList != null && position < getItemCount() && postList.get(position) != null) {
            holder.configure(postList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void setAllItems(List<PostItem> posts) {
        this.postList.clear();
        if (posts != null && !posts.isEmpty()) {
            this.postList = posts;
        }
        notifyDataSetChanged();
    }

    public PostItem getItem(int position) {
        return position >= 0 && position < postList.size() ? postList.get(position) : null;
    }

    public List<PostItem> getAllItems() {
        return postList;
    }

    public void addItem(@NonNull PostItem post) {
        postList.add(post);
        notifyItemInserted(postList.size() - 1);
    }

    public void addItems(List<PostItem> posts) {
        int rangeStart = getItemCount();
        postList.addAll(posts);
        notifyItemRangeInserted(rangeStart, posts.size());
    }

    public void removeItem(int position) {
        postList.remove(position);
        notifyItemRemoved(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.post_avatar)
        ImageView avatar;
        @BindView(R.id.post_author)
        TextView author;
        @BindView(R.id.post_image)
        ImageView image;
        @BindView(R.id.post_title)
        TextView title;
        @BindView(R.id.post_body)
        TextView body;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void configure(final PostItem post) {
            // set blog name
            author.setText(post.getBlogName());
            // load content and set visibility based on vars
            if (!TextUtils.isEmpty(post.getTitle())) {
                title.setText(post.getTitle());
                title.setVisibility(View.VISIBLE);
            } else {
                title.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(post.getBody())) {
                body.setText(Html.fromHtml(post.getBody()));
                body.setVisibility(View.VISIBLE);
            } else {
                body.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(post.getImageUrl())) {
                image.setVisibility(View.VISIBLE);
                Glide.with(itemView.getContext())
                        .load(post.getImageUrl())
                        .placeholder(R.color.placeholder_color)
                        .error(R.color.placeholder_color)
                        .fitCenter()
                        .into(image);
            } else {
                image.setVisibility(View.GONE);
            }
        }
    }

}
