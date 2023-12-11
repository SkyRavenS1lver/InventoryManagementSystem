package revandra.projects.inventorymanagementsystem.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "variants")
data class Variant(
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "idCategory") val idCategory: Int =0,
    @ColumnInfo(name = "imgPath") var imgPath: String = "",
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "idVariant") val idVariant: Int =0,
){
    override fun toString(): String {
        return name
    }

    override fun equals(other: Any?): Boolean {
        return if (this.idVariant == other){
            true
        } else{
            super.equals(other)
        }
    }
}
