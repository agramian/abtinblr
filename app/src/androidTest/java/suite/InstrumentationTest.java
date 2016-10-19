package suite;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.test.InstrumentationTestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InstrumentationTest extends InstrumentationTestCase {

    static Context sContext;

    @BeforeClass
    public void setUpSuite() {

    }

    @Before
    public void setUp() {
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        // get context
        sContext = getInstrumentation().getTargetContext();
    }

    @After
    public void tearDown() {

    }

    @AfterClass
    public static void tearDownSuite() {

    }

    @Test
    public void test_01_get_app_data_path() {
        assertTrue(sContext.getFilesDir() != null);
    }

}
