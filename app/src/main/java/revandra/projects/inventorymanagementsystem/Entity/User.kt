package revandra.projects.inventorymanagementsystem.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey @ColumnInfo(name = "username") val username: String = "",
    @ColumnInfo(name = "password") val password: String = "",
    @ColumnInfo(name = "role") val role: String = "",
    ){
    override fun equals(other: Any?): Boolean {
        other as User
        if (other.username == this.username && other.password == this.password){
            return true
        }
        return super.equals(other)
    }
}
