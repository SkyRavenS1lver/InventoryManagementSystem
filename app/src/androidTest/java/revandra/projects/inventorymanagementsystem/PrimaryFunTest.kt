package revandra.projects.inventorymanagementsystem
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class PrimaryFunTest {
    private lateinit var scenarios: ActivityScenario<MainActivity>
    private lateinit var view: View
    @Before
    fun setup(){
        scenarios = launchActivity()
        scenarios.moveToState(Lifecycle.State.RESUMED)
        scenarios.onActivity {
            val navController =
                Navigation.findNavController(it, R.id.nav_host_fragment_activity_main)
            navController.navigate(R.id.navigation_category)
            view = it.window.decorView
        }
    }
    @Test
    fun selectCategory(){
        onView(withText("Electronics")).perform(click())
        onView(withText("Laptop")).check(matches(isDisplayed()))
    }
    @Test
    fun selectVariant(){
        onView(withText("Electronics")).perform(click())
        onView(withText("Laptop")).perform(click())
        onView(withText("Toshiba")).check(matches(isDisplayed()))
    }
    @Test
    fun selectProduct(){
        onView(withText("Electronics")).perform(click())
        onView(withText("Laptop")).perform(click())
        onView(withText("Toshiba")).perform(click())
        onView(withText("Toshiba")).check(matches(isDisplayed()))
    }

}