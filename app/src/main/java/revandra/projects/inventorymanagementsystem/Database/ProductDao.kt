package revandra.projects.inventorymanagementsystem.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import revandra.projects.inventorymanagementsystem.Entity.Product

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(product: Product)

    @get:Query("SELECT * from products")
    val allProducts: LiveData<List<Product>>

    @Query("SELECT * from products where idVariant = :idV")
    fun allProduct(idV: String): LiveData<List<Product>>
    @Query("SELECT SUM(stock) from products where idVariant = :string")
    fun variantStock(string: String): LiveData<Int>
}