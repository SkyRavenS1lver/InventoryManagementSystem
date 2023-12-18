package revandra.projects.inventorymanagementsystem

import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginTest {
    private lateinit var scenarios: ActivityScenario<Login>
    private lateinit var view: View

    @Before
    fun setup(){
        scenarios = launchActivity()
        scenarios.moveToState(Lifecycle.State.RESUMED)
        scenarios.onActivity {
            view = it.window.decorView
        }

    }
    @Test
    fun loginBlank(){
        onView(withId(R.id.btn_login)).perform(click())
        onView(withId(R.id.username)).check(matches(hasErrorText("Username cannot be empty!")))
        onView(withId(R.id.password)).check(matches(hasErrorText("Password cannot be empty!")))
    }
    @Test
    fun loginBlankAfter(){
        onView(withId(R.id.username)).perform(typeText("a"))
        onView(withId(R.id.username)).perform(pressKey(KeyEvent.KEYCODE_DEL))
        onView(withId(R.id.password)).perform(typeText("a"))
        onView(withId(R.id.password)).perform(pressKey(KeyEvent.KEYCODE_DEL))
        onView(withId(R.id.username)).check(matches(hasErrorText("Username cannot be empty!")))
        onView(withId(R.id.password)).check(matches(hasErrorText("Password cannot be empty!")))
    }
    @Test
    fun loginWrong(){
        onView(withId(R.id.username)).perform(typeText("a"))
        onView(withId(R.id.password)).perform(typeText("a"))
        onView(withId(R.id.btn_login)).perform(click())
        onView(withId(R.id.username)).check(matches(hasErrorText("Username or password is incorrect!")))
        onView(withId(R.id.password)).check(matches(hasErrorText("Username or password is incorrect!")))
    }
    @Test
    fun loginSuccessSuperAdmin(){
        onView(withId(R.id.username)).perform(typeText("superAdmin"))
        onView(withId(R.id.password)).perform(typeText("password"))
        onView(withId(R.id.btn_login)).perform(click())
        onView(withText("Login Success")).inRoot(withDecorView(not(view))).check(matches(isDisplayed()))
    }
    @Test
    fun loginSuccessAdmin(){
        onView(withId(R.id.username)).perform(typeText("admin"))
        onView(withId(R.id.password)).perform(typeText("password"))
        onView(withId(R.id.btn_login)).perform(click())
        onView(withText("Login Success")).inRoot(withDecorView(not(view))).check(matches(isDisplayed()))
    }

}