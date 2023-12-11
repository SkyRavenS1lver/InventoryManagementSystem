package revandra.projects.inventorymanagementsystem.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import revandra.projects.inventorymanagementsystem.Entity.User
import java.util.concurrent.Executors


@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class Databases : RoomDatabase(){
    abstract fun loginDao():LoginDao?

    companion object{
        @Volatile
        private var INSTANCE: Databases? = null
        fun getDatabase(context: Context): Databases? {
            if (INSTANCE == null) {
                synchronized(Databases::class.java) {
                    INSTANCE = databaseBuilder(
                        context.applicationContext,
                        Databases::class.java,
                        "inventory_management_system_database"
                    ).build()
                }
                Executors.newSingleThreadExecutor().execute {
                    if (INSTANCE!!.loginDao()!!.checkIfEmpty() == 0) {
                        INSTANCE!!.loginDao()!!.insert(
                            User(
                                username = "admin",
                                password = "password",
                                role = "Admin"
                            )
                        )
                        INSTANCE!!.loginDao()!!.insert(
                            User(
                                username = "superAdmin",
                                password = "password",
                                role = "Super Admin"
                            )
                        )
                    }
                }
                return INSTANCE
            }
            else{
                return INSTANCE
            }
        }
    }
}