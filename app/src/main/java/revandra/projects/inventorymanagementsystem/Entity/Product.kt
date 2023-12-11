package revandra.projects.inventorymanagementsystem.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "idCategory") val idCategory: Int =0,
    @ColumnInfo(name = "idVariant") val idVariant: Int =0,
    @ColumnInfo(name = "stock") val stock: Int =0,
    @ColumnInfo(name = "description") val description: String ="",
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "idProduct") val idProduct: Int =0,
)