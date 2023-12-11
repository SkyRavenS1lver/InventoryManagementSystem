package revandra.projects.inventorymanagementsystem.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import revandra.projects.inventorymanagementsystem.Entity.User

@Dao
interface LoginDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user:User)
    @Update
    fun update(user:User)

    @Query("SELECT * from users where username = :username and password = :password limit 1")
    fun login(username:String, password:String): User


    @get:Query("SELECT * from users")
    val users:LiveData<List<User>>

    @Query("SELECT COUNT(*) FROM users")
    fun checkIfEmpty():Int


}