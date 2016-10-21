package androidTest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.test.InstrumentationTestCase;

import com.abtingramian.abtinblr.R;
import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.User;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

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
        // Make the request
        User user = client.user();
        assertNotNull(user.getName());
    }

}
