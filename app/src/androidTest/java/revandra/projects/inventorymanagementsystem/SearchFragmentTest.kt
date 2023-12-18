package revandra.projects.inventorymanagementsystem

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import org.junit.jupiter.api.Assertions.*
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
class SearchFragmentTest{
    private lateinit var scenarios: ActivityScenario<MainActivity>
    private lateinit var view: View
    @Before
    fun setup(){
        scenarios = launchActivity()
        scenarios.moveToState(Lifecycle.State.RESUMED)
        scenarios.onActivity {
            val navController =
                Navigation.findNavController(it, R.id.nav_host_fragment_activity_main)
            navController.navigate(R.id.navigation_search)
            view = it.window.decorView
        }
    }
    @Test
    fun searchEmpty(){
        onView(withId(R.id.search_bar)).perform(typeText("BlaaBlaaBlaa"))
        onView(withText("No Data Shown")).check(matches(isDisplayed()))
    }
    @Test
    fun searchFound(){
        onView(withId(R.id.search_bar)).perform(typeText("shiba"))
        onView(withText("Toshiba")).check(matches(isDisplayed()))
    }

}