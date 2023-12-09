package revandra.projects.inventorymanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import revandra.projects.inventorymanagementsystem.Database.Databases
import revandra.projects.inventorymanagementsystem.Entity.User
import revandra.projects.inventorymanagementsystem.Utility.CustomToastMaker
import revandra.projects.inventorymanagementsystem.databinding.ActivityLoginBinding
import java.util.concurrent.Executors

class Login : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private val db by lazy {
        Databases.getDatabase(this)
    }
    private var mLastClickTime:Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        db?.loginDao()?.users?.observe(this){ users ->
            println(users.contains(User("admin", "password")))
            users.forEach {
                println(it.username)
            }
        }
        binding.username.addTextChangedListener {
            if (it?.isEmpty() == true){
                binding.username.error = "Username cannot be empty!"
            }
            else{
                binding.username.error = null
            }
        }
        binding.password.addTextChangedListener {
            if (it?.isEmpty() == true){
                binding.password.error = "Password cannot be empty!"
            }
            else{
                binding.password.error = null
            }
        }
    }

    fun login(view: View) {
        if (!binding.username.text.isEmpty() && !binding.password.text.isEmpty()){
            Executors.newSingleThreadExecutor().execute {
                if((db?.loginDao()
                        ?.login(
                            binding.username.text.toString(),
                            binding.password.text.toString()
                        ) ?: "") != ""
                ){
                    startActivity(Intent(this, Dashboard::class.java))
                }
            }
        }
    }
    override fun onBackPressed() {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 3000){
            finishAffinity()
            finish()
        }
        else if (SystemClock.elapsedRealtime() - mLastClickTime > 3000){
            mLastClickTime = SystemClock.elapsedRealtime()
            CustomToastMaker.makeCustomToast(this, getString(R.string.toast_back))
        }
        else{
            super.onBackPressed()
        }
    }
}