package revandra.projects.inventorymanagementsystem

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ChangePWTest {
    private lateinit var scenarios: ActivityScenario<MainActivity>
    private lateinit var view: View

    fun hasTextInputLayoutHintText(expectedErrorText: String): Matcher<View> = object : TypeSafeMatcher<View>() {

        override fun describeTo(description: Description?) { }

        override fun matchesSafely(item: View?): Boolean {
            if (item !is TextInputLayout) return false
            val error = item.error ?: return false
            val hint = error.toString()
            return expectedErrorText == hint
        }
    }
    @Before
    fun setup(){
        scenarios = launchActivity()
        scenarios.moveToState(Lifecycle.State.RESUMED)
        scenarios.onActivity {
            val navController = findNavController(it, R.id.nav_host_fragment_activity_main)
            navController.navigate(R.id.changePassword)
            view = it.window.decorView
        }
    }
    @Test
    fun changePWEmpty(){
        Espresso.onView(ViewMatchers.withId(R.id.resetPW)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.newPW)).check(
            ViewAssertions.matches(hasTextInputLayoutHintText("Please fill out this form"))
        )
        Espresso.onView(ViewMatchers.withId(R.id.confirm_pw)).check(
            ViewAssertions.matches(hasTextInputLayoutHintText("Please fill out this form"))
        )
    }
    @Test
    fun changePWSuccess(){
        Espresso.onView(ViewMatchers.withId(R.id.et_conf_pw)).perform(ViewActions.typeText("password"))
        Espresso.onView(ViewMatchers.withId(R.id.et_new_pw)).perform(ViewActions.typeText("password"))
        Espresso.onView(ViewMatchers.withId(R.id.resetPW)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("Password Changed Successfully!"))
            .inRoot(RootMatchers.withDecorView(Matchers.not(view)))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}