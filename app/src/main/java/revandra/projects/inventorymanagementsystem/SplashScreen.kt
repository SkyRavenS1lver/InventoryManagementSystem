package revandra.projects.inventorymanagementsystem

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import revandra.projects.inventorymanagementsystem.Database.Databases
import revandra.projects.inventorymanagementsystem.Entity.User
import revandra.projects.inventorymanagementsystem.Utility.SharedPrefManager
import java.util.concurrent.Executors


class SplashScreen : AppCompatActivity() {
    private val prefManager by lazy {
        SharedPrefManager.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Handler(Looper.getMainLooper()).postDelayed({
            if (prefManager.isLogged() == ""){
                startActivity(Intent(this, Login::class.java))
            }
            else{
                startActivity(Intent(this, Dashboard::class.java))
            }
            finish()
        }, 3000)
    }
}