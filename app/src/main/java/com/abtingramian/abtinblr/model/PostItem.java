package com.abtingramian.abtinblr.model;


import com.tumblr.jumblr.types.AnswerPost;
import com.tumblr.jumblr.types.AudioPost;
import com.tumblr.jumblr.types.ChatPost;
import com.tumblr.jumblr.types.LinkPost;
import com.tumblr.jumblr.types.PhotoPost;
import com.tumblr.jumblr.types.Post;
import com.tumblr.jumblr.types.QuotePost;
import com.tumblr.jumblr.types.TextPost;
import com.tumblr.jumblr.types.VideoPost;

public class PostItem {

    String title;
    String body;
    String imageUrl;
    String blogName;

    public PostItem() {}

    public PostItem(Post post) {
        blogName = post.getBlogName();
        // convert from jumblr model to extract necessary fields
        if (post instanceof PhotoPost) {
            PhotoPost photoPost = (PhotoPost) post;
            imageUrl = photoPost.getPhotos().get(0).getSizes().get(0).getUrl();
            body = photoPost.getCaption();
        } else if (post instanceof VideoPost) {
            VideoPost videoPost = (VideoPost) post;
            imageUrl = videoPost.getThumbnailUrl();
            body = videoPost.getCaption();
        } else if (post instanceof TextPost) {
            TextPost textPost = (TextPost) post;
            title = textPost.getTitle();
            body = textPost.getBody();
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
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

}
