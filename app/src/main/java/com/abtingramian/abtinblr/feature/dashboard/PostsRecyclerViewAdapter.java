package com.abtingramian.abtinblr.feature.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abtingramian.abtinblr.R;
import com.tumblr.jumblr.types.Post;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostsRecyclerViewAdapter extends RecyclerView.Adapter<PostsRecyclerViewAdapter.ViewHolder> {

    private List<Post> postList = new ArrayList<>();

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

    public void setAllItems(List<Post> posts) {
        this.postList.clear();
        if (posts != null && !posts.isEmpty()) {
            this.postList = posts;
        }
        notifyDataSetChanged();
    }

    public Post getItem(int position) {
        return position >= 0 && position < postList.size() ? postList.get(position) : null;
    }

    public List<Post> getAllItems() {
        return postList;
    }

    public void addItem(@NonNull Post post) {
        postList.add(post);
        notifyItemInserted(postList.size() - 1);
    }

    public void addItems(List<Post> posts) {
        int rangeStart = getItemCount();
        postList.addAll(posts);
        notifyItemRangeInserted(rangeStart, postList.size() - 1);
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
        @BindView(R.id.post_text)
        TextView text;
        @BindView(R.id.post_image)
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void configure(final Post post) {
            author.setText(post.getAuthorId());
            text.setText(post.getSourceTitle());
        }
    }

}
