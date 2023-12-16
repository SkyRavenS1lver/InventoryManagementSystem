package revandra.projects.inventorymanagementsystem

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import revandra.projects.inventorymanagementsystem.Utility.SharedPrefManager
import android.Manifest
import android.content.pm.PackageManager


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
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ,Manifest.permission.READ_EXTERNAL_STORAGE), PackageManager.PERMISSION_GRANTED)
            }
            else{
                startActivity(Intent(this, Dashboard::class.java))
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ,Manifest.permission.READ_EXTERNAL_STORAGE), PackageManager.PERMISSION_GRANTED)
            }
            finish()
        }, 3000)
    }
}