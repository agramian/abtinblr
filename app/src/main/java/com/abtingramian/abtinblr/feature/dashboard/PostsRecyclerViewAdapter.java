package com.abtingramian.abtinblr.feature.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abtingramian.abtinblr.R;
import com.bumptech.glide.Glide;
import com.tumblr.jumblr.types.AnswerPost;
import com.tumblr.jumblr.types.AudioPost;
import com.tumblr.jumblr.types.ChatPost;
import com.tumblr.jumblr.types.LinkPost;
import com.tumblr.jumblr.types.PhotoPost;
import com.tumblr.jumblr.types.Post;
import com.tumblr.jumblr.types.QuotePost;
import com.tumblr.jumblr.types.TextPost;
import com.tumblr.jumblr.types.VideoPost;

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

        public void configure(final Post post) {
            // set blog name
            author.setText(post.getBlogName());
            // common vars
            String titleText = "";
            String bodyText = null;
            String imageUrl = "";
            // set content based on post type
            if (post instanceof PhotoPost) {
                PhotoPost photoPost = (PhotoPost) post;
                imageUrl = photoPost.getPhotos().get(0).getSizes().get(0).getUrl();
                bodyText = photoPost.getCaption();
            } else if (post instanceof VideoPost) {
                VideoPost videoPost = (VideoPost) post;
                imageUrl = videoPost.getThumbnailUrl();
                bodyText = videoPost.getCaption();
            } else if (post instanceof TextPost) {
                TextPost textPost = (TextPost) post;
                titleText = textPost.getTitle();
                bodyText = textPost.getBody();
            } else if (post instanceof LinkPost) {
                // TODO
            } else if (post instanceof AudioPost) {
                // TODO
            } else if (post instanceof QuotePost) {
                // TODO
            } else if (post instanceof ChatPost) {
                // TODO
            } else if (post instanceof AnswerPost) {
                // TODO
            }
            // load content and set visibility based on vars
            if (!TextUtils.isEmpty(titleText)) {
                title.setText(titleText);
                title.setVisibility(View.VISIBLE);
            } else {
                title.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(titleText)) {
                body.setText(Html.fromHtml(bodyText));
                body.setVisibility(View.VISIBLE);
            } else {
                body.setVisibility(View.GONE);
            }
            Glide.with(itemView.getContext())
                    .load(imageUrl)
                    .placeholder(R.color.placeholder_color)
                    .error(R.color.placeholder_color)
                    .centerCrop()
                    .into(image);
        }
    }

}
