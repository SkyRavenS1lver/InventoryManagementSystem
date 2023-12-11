package revandra.projects.inventorymanagementsystem.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import revandra.projects.inventorymanagementsystem.Entity.Variant

@Dao
interface VariantDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(variant: Variant)

    @Query("SELECT * from variants where idCategory = :string")
    fun variantFromId(string: String): LiveData<List<Variant>>

}