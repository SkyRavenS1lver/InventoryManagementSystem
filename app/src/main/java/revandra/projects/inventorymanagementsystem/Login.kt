package revandra.projects.inventorymanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MutableLiveData
import revandra.projects.inventorymanagementsystem.Database.Databases
import revandra.projects.inventorymanagementsystem.Entity.User
import revandra.projects.inventorymanagementsystem.Utility.CustomToastMaker
import revandra.projects.inventorymanagementsystem.Utility.SharedPrefManager
import revandra.projects.inventorymanagementsystem.databinding.ActivityLoginBinding
import java.util.concurrent.Executors

class Login : AppCompatActivity() {
    private val prefManager by lazy {
        SharedPrefManager.getInstance(this)
    }
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private val db by lazy {
        Databases.getDatabase(this)
    }
    private var mLastClickTime:Long = 0
    private val currentUser = MutableLiveData<User?>(null)


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
        currentUser.observe(this){
            if (it != null){
                if(it.username != ""){
                    prefManager.login(it.role,it.username)
                    CustomToastMaker.makeCustomToast(this, "Login Success")
                    startActivity(Intent(this, Dashboard::class.java))
                }
                else{
                    binding.username.error = "Username or password is incorrect!"
                    binding.password.error = "Username or password is incorrect!"
                }
            }
        }
    }

    fun login(view: View) {
        if (binding.username.text.isNotEmpty() && binding.password.text.isNotEmpty()){
            Executors.newSingleThreadExecutor().execute {
                currentUser.postValue(loginCheck(binding.username.text.toString(),
                    binding.password.text.toString()))
            }
        }
        else{
            if (binding.username.text.isEmpty()){
                binding.username.error = "Username cannot be empty!"
            }
            if (binding.password.text.isEmpty()){
                binding.password.error = "Password cannot be empty!"
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

    fun loginCheck(username:String, password:String):User{
        return db?.loginDao()?.login(username.trim(), password.trim()) ?: User()
    }
}