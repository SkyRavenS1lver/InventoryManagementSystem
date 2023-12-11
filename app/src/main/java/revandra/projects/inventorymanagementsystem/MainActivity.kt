package revandra.projects.inventorymanagementsystem

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import revandra.projects.inventorymanagementsystem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val navView = binding.navView
        navView.itemIconTintList = null
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        intent.getStringExtra("destination").also {
            when(it){
                "profile"->{
                    navController.popBackStack()
                    navController.navigate(R.id.profileFragment)
                }
                "category"->{
                    navController.popBackStack()
                    navController.navigate(R.id.navigation_category)
                }
                "search"->{
                    navController.popBackStack()
                    navController.navigate(R.id.navigation_search)
                }
                "add"->{
                    navController.popBackStack()
                    navController.navigate(R.id.mainFragment)
                }
            }
        }
        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_dashboard -> {
                    startActivity(Intent(this, Dashboard::class.java))
                    finish()
                }
                R.id.navigation_search -> {
                    if (navController.currentDestination?.id != R.id.navigation_search){
                        navController.navigate(R.id.navigation_search)
                    }
                }
                R.id.navigation_category -> {
                    if (navController.currentDestination?.id != R.id.navigation_category){
                        navController.navigate(R.id.navigation_category)
                    }
                }
            }
            true
        }
    }
}