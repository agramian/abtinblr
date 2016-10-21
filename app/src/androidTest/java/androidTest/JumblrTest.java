package androidTest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.test.InstrumentationTestCase;

import com.abtingramian.abtinblr.R;
import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Post;
import com.tumblr.jumblr.types.User;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JumblrTest extends InstrumentationTestCase {

    JumblrClient client;
    Context context;

    @BeforeClass
    public void setUpSuite() {

    }

    @Before
    public void setUp() {
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        // get context
        context = getInstrumentation().getTargetContext();
        // Authenticate via OAuth
        // get context
        client = new JumblrClient(context.getResources().getString(R.string.tumblr_consumer_key),
                context.getString(R.string.tumblr_consumer_secret));
        client.setToken(
                context.getString(R.string.tumblr_token),
                context.getString(R.string.tumblr_token_secret)
        );
    }

    @After
    public void tearDown() {

    }

    @AfterClass
    public static void tearDownSuite() {

    }

    @Test
    public void test_01_user_name() {
        User user = client.user();
        assertNotNull(user.getName());
    }

    @Test
    public void test_02_user_dashboard() {
        List<Post> posts = client.userDashboard();
        assertNotNull(posts);
    }

    @Test
    public void test_03_tagged_posts() {
        List<Post> posts = client.tagged("lol");
        assertNotNull(posts);
        assert(posts.size() > 0);
    }

    @Test
    public void test_04_tagged_posts_pagination() {
        Map<String, Integer> options = new HashMap<String, Integer>();
        options.put("limit", 20);
        options.put("offset", 20);
        List<Post> posts = client.tagged("lol");
        assertNotNull(posts);
        assert(posts.size() > 0);
    }

}
