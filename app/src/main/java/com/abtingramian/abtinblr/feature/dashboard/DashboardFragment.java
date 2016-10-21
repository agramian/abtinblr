package com.abtingramian.abtinblr.feature.dashboard;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abtingramian.abtinblr.R;
import com.abtingramian.abtinblr.base.BaseFragment;
import com.abtingramian.abtinblr.base.SingleFragmentActivity;
import com.abtingramian.abtinblr.feature.home.HomeActivity;
import com.abtingramian.abtinblr.util.SnackbarUtil;
import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindString;

public class DashboardFragment extends BaseFragment implements SingleFragmentActivity.IFab {

    static final String POST_TAG = "lol";

    @BindString(R.string.tumblr_consumer_key) String consumerKey;
    @BindString(R.string.tumblr_consumer_secret) String consumerSecret;
    @BindString(R.string.tumblr_token) String token;
    @BindString(R.string.tumblr_token_secret) String tokenSecret;
    JumblrClient client;
    Map<String, Integer> options = new HashMap<>();
    int offset = 0;
    int limit = 20;

    public static DashboardFragment newInstance() {
        Bundle args = new Bundle();
        DashboardFragment fragment = new DashboardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_placeholder, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // initialize tumblr client
        try {
            client = new JumblrClient(consumerKey, consumerSecret);
            client.setToken(token, tokenSecret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // request options map
        options.put("limit", limit);
        options.put("offset", offset);
        // request for posts
        fetchPosts();
    }

    private void fetchPosts() {
        new AsyncTask<Void, Void, List<Post>>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // TODO show loading if necessary
            }

            @Override
            protected List<Post> doInBackground(Void... params) {
                options.put("offset", offset);
                return client != null ? client.tagged(POST_TAG, options) : null;
            }

            @Override
            protected void onPostExecute(List<Post> posts) {
                super.onPostExecute(posts);
                if (posts != null && !posts.isEmpty()) {
                    // TODO populate recyclerview
                } else if (getActivity() instanceof HomeActivity){
                    // if no posts returned check connection to show toast if necessary
                    ((HomeActivity) getActivity()).checkConnection();
                }
                // update offset
                offset += posts != null ? posts.size() : 0;
            }
        }.execute();
    }

    @Override
    public void onFabClicked() {
        SnackbarUtil.showSnackbarLong(getContext(), getView(), R.string.coming_soon);
    }

}
