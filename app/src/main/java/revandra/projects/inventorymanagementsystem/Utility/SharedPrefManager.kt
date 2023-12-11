package revandra.projects.inventorymanagementsystem.Utility

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager private constructor(context: Context){
    private val sharedPreferences: SharedPreferences
        companion object{
            private const val PREFS_FILENAME = "InventoryManagementSystem"
            private const val KEY_ROLE = "ROLE"
            private const val KEY_UNAME = "USERNAME"
            @Volatile
            private var instance: SharedPrefManager? = null
            fun getInstance(context: Context): SharedPrefManager {
                return instance ?: synchronized(this) {
                    instance ?: SharedPrefManager(context.applicationContext).also {
                        instance = it
                    }
                }
            }
        }

    init {
        sharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
    }
    fun login(role: String, uname: String){
        sharedPreferences.edit().putString(KEY_ROLE,role).apply()
        sharedPreferences.edit().putString(KEY_UNAME,uname).apply()
    }
    fun logout(){
        sharedPreferences.edit().putString(KEY_ROLE,"").apply()
        sharedPreferences.edit().putString(KEY_UNAME,"").apply()
    }
    fun isLogged():String{
        return sharedPreferences.getString(KEY_ROLE, "") ?: ""
    }
    fun currentUname():String{
        return sharedPreferences.getString(KEY_UNAME, "") ?: ""
    }
}
