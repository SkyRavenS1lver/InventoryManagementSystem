package revandra.projects.inventorymanagementsystem

import android.Manifest
import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.provider.MediaStore
import android.support.test.rule.ActivityTestRule
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.isInternal
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.GrantPermissionRule
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class UpdateProductTest {
    private lateinit var scenarios: ActivityScenario<Login>
    private lateinit var view: View
    @get:Rule
    var mRuntimePermissionRule = GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE)
    @Before
    fun setup(){
        scenarios = launchActivity()
        scenarios.moveToState(Lifecycle.State.RESUMED)
        scenarios.onActivity {
            view = it.window.decorView
        }
    }
    @Test
    fun updateProduct(){
        onView(withId(R.id.username)).perform(typeText("superAdmin"))
        onView(withId(R.id.password)).perform(typeText("password"))
        onView(withId(R.id.btn_login)).perform(click())
        onView(withText("Categories")).perform(click())
        onView(withText("Electronics")).perform(click())
        onView(withText("Laptop")).perform(click())
        onView(withText("Toshiba")).perform(click())
        onView(withId(R.id.edit_btn)).perform(click())
        onView(withId(R.id.name_tv)).perform(typeText(" New"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.save_btn)).perform(click())
        onView(withText("Toshiba New")).check(matches(isDisplayed()))
    }
//    @Test
//    fun addProduct(){
//        IntentsTestRule
//        val resultData = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//        intending(not(isInternal())).respondWith(
//            Instrumentation.ActivityResult(
//                Activity.RESULT_OK,
//                null
//            )
//        )
//        onView(withId(R.id.username)).perform(typeText("superAdmin"))
//        onView(withId(R.id.password)).perform(typeText("password"))
//        onView(withId(R.id.btn_login)).perform(click())
//        onView(withText("Add")).perform(click())
//        onView(withId(R.id.et_product)).perform(typeText("test"))
//        onView(withId(R.id.et_description)).perform(typeText("test"))
//        onView(withId(R.id.et_stock)).perform(click())
//        onView(withText("Office Supplies")).perform(click())
//        onView(withId(R.id.et_variant)).perform(click())
//        onView(withText("Desk")).perform(click())
//        onView(withId(R.id.image)).perform(click())
//        onView(withId(R.id.insert_btn)).perform(click())
//        onView(withText("Product Added Successfully"))
//            .inRoot(RootMatchers.withDecorView(Matchers.not(view)))
//            .check(matches(isDisplayed()))
//    }
}