package revandra.projects.inventorymanagementsystem

import android.view.View
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ProfileFragmentTest{
    private lateinit var scenarios: ActivityScenario<MainActivity>
    private lateinit var view: View


    @Before
    fun setup(){
        scenarios = launchActivity()
        scenarios.moveToState(Lifecycle.State.RESUMED)
//        scenarios.onActivity {
//            it.findNavController(R.id.nav_view).navigate()
//        }
        scenarios.onActivity {
            view = it.window.decorView
        }
    }


    @Test
    fun logOut(){
        onView(withId(R.id.logout)).perform(click())
        onView(withText("Yes")).perform(click())
        onView(withText("Successfully Logged Out"))
            .inRoot(RootMatchers.withDecorView(Matchers.not(view)))
            .check(matches(isDisplayed()))
    }
}