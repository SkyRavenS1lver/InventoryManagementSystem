package revandra.projects.inventorymanagementsystem

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import revandra.projects.inventorymanagementsystem.Entity.User

@RunWith(AndroidJUnit4::class)
@SmallTest
class LoginTest {
    private var login = Login()

    @Test
    fun testLoginCheck() {
        assertEquals(User(), login.loginCheck("", ""))
    }
    @Test
    fun testLoginCheckTrue() {
        assertEquals(User(
            username = "superAdmin",
            password = "password",
            role = "Super Admin"
        ), login.loginCheck("", ""))
    }

    @Test
    fun loginCheck() {
        assertEquals(User(), login.loginCheck("", ""))
    }
}