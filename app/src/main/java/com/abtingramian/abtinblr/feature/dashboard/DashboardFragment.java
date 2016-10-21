package com.abtingramian.abtinblr.feature.dashboard;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abtingramian.abtinblr.R;
import com.abtingramian.abtinblr.base.BaseFragment;
import com.abtingramian.abtinblr.base.SingleFragmentActivity;
import com.abtingramian.abtinblr.cache.PostCache;
import com.abtingramian.abtinblr.feature.home.HomeActivity;
import com.abtingramian.abtinblr.model.PostItem;
import com.abtingramian.abtinblr.util.SnackbarUtil;
import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Blog;
import com.tumblr.jumblr.types.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindString;
import butterknife.BindView;
import de.devland.esperandro.Esperandro;

public class DashboardFragment extends BaseFragment implements SingleFragmentActivity.IFab {

    static final String TEST_BLOG = "ifpaintingscouldtext.tumblr.com";

    @BindString(R.string.tumblr_consumer_key) String consumerKey;
    @BindString(R.string.tumblr_consumer_secret) String consumerSecret;
    @BindString(R.string.tumblr_token) String token;
    @BindString(R.string.tumblr_token_secret) String tokenSecret;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.posts_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.loading_posts)
    View loading;
    PostsRecyclerViewAdapter postsAdapter;
    LinearLayoutManager layoutManager;
    JumblrClient client;
    Map<String, Integer> options = new HashMap<>();
    int offset = 0;
    int limit = 20;
    boolean isFetchingPosts = false;
    PostCache postCache;

    public static DashboardFragment newInstance() {
        Bundle args = new Bundle();
        DashboardFragment fragment = new DashboardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postCache = Esperandro.getPreferences(PostCache.class, getContext().getApplicationContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // set up recycler view
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        postsAdapter = new PostsRecyclerViewAdapter();
        recyclerView.setAdapter(postsAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // if the user has scrolled to the bottom, fetch more posts
                if (!isFetchingPosts && layoutManager.findLastVisibleItemPosition() == postsAdapter.getItemCount() - 1) {
                    fetchPosts();
                }
            }
        });
        // initialize tumblr client and blog to use for content
        try {
            client = new JumblrClient(consumerKey, consumerSecret);
            client.setToken(token, tokenSecret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // request options map
        options.put("limit", limit);
        options.put("offset", offset);
        // intitial load
        performInitialLoad();
        // swipe refresh
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isFetchingPosts) {
                    // refresh from top so update offset then fetch posts
                    offset = 0;
                    fetchPosts();
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });

    }

    private void performInitialLoad() {
        new AsyncTask<Void, Void, List<PostItem>>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                recyclerView.setVisibility(View.GONE);
                loading.setVisibility(View.VISIBLE);
            }

            @Override
            protected List<PostItem> doInBackground(Void... params) {
                // check for posts in cache
                return postCache.postList();
            }

            @Override
            protected void onPostExecute(List<PostItem> posts) {
                super.onPostExecute(posts);
                recyclerView.setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
                if (posts != null && !posts.isEmpty()) {
                    // load from cache
                    postsAdapter.setAllItems(posts);
                    offset = posts.size();
                } else {
                    // network request
                    fetchPosts();
                }
            }
        }.execute();
    }

    private void fetchPosts() {
        isFetchingPosts = true;
        new AsyncTask<Void, Void, List<PostItem>>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // show loading bar base if this was not triggered by swipe to refresh
                if (!swipeRefreshLayout.isRefreshing()) {
                    loading.setVisibility(View.VISIBLE);
                }
            }

            @Override
            protected List<PostItem> doInBackground(Void... params) {
                options.put("offset", offset);
                List<Post> posts = null;
                try {
                    posts = client != null ? client.blogPosts(TEST_BLOG, options) : null;
                }
                catch (Exception e) {
                    // most likely oauth connection exception due to no network
                    // but catching all in case since Jumblr is largely an unknown
                    e.printStackTrace();
                }
                // convert and cache posts
                List<PostItem> postItems = new ArrayList();
                if (posts != null && !posts.isEmpty()) {
                    // convert
                    for (Post post : posts) {
                        postItems.add(new PostItem(post));
                    }
                    // cache
                    List<PostItem> all = postsAdapter.getAllItems();
                    all.addAll(postItems);
                    postCache.postList(new ArrayList<>(all));
                }
                return postItems;
            }

            @Override
            protected void onPostExecute(List<PostItem> posts) {
                super.onPostExecute(posts);
                if (posts != null && !posts.isEmpty()) {
                    if (offset == 0) {
                        // replace since this is the initial load or swipe to refresh
                        postsAdapter.setAllItems(posts);
                    } else {
                        // append to bottom
                        postsAdapter.addItems(posts);
                    }
                } else if (getActivity() instanceof HomeActivity){
                    // if no posts returned check connection to show toast if necessary
                    ((HomeActivity) getActivity()).checkConnection();
                }
                // update offset
                offset += posts != null ? posts.size() : 0;
                // update refreshing state
                if (loading != null) {
                    loading.setVisibility(View.GONE);
                }
                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(false);
                }
                isFetchingPosts = false;
            }
        }.execute();
    }

    @Override
    public void onFabClicked() {
        SnackbarUtil.showSnackbarLong(getContext(), getView(), R.string.coming_soon);
    }

}
