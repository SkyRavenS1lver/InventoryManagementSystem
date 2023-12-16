package revandra.projects.inventorymanagementsystem.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import revandra.projects.inventorymanagementsystem.Entity.Product
import revandra.projects.inventorymanagementsystem.Entity.User
import revandra.projects.inventorymanagementsystem.Entity.Variant
import java.util.concurrent.Executors


@Database(entities = [User::class, Variant::class, Product::class], version = 1, exportSchema = false)
abstract class Databases : RoomDatabase(){
    abstract fun loginDao():LoginDao?
    abstract fun variantDao():VariantDAO?
    abstract fun productDao():ProductDao?

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
                        addLoginUser()
                        addDefaultVariants()
                        addDefaultProducts()
                    }
                }
                return INSTANCE
            }
            else{
                return INSTANCE
            }
        }

        private fun addDefaultProducts() {
            INSTANCE!!.productDao()!!.insert(
                Product(
                    "Toshiba",
                    1,
                    1,
                    10,
                    "Configurable with powerful and efficient CPUs, up to 64GB of memory, " +
                            "Wi-Fi 6E and Thunderbolt™ 4, it is smarter, faster, and more capable than ever. " +
                            "It’s tough and travels well too, with a sleek and durable chassis that is " +
                            "uncompromisingly thin and lightweight.",
                ),
            )
            INSTANCE!!.productDao()!!.insert(
                Product(
                    "Mac Book",
                    1,
                    1,
                    10,
                    "16-core Neural Engine\n" +
                            "15.3-inch Liquid Retina display with True Tone³\n" +
                            "1080p FaceTime HD camera\n" +
                            "MagSafe 3 charging port\n" +
                            "Two Thunderbolt / USB 4 ports\n" +
                            "Magic Keyboard with Touch ID\n" +
                            "Force Touch trackpad\n" +
                            "35W Dual USB-C Port Compact Power Adapter",
                ),
            )
            INSTANCE!!.productDao()!!.insert(
                Product(
                    "Dell",
                    1,
                    1,
                    10,
                    "12th Gen Intel® Core™ i3-1215U (10 MB cache, 6 cores, 8 threads, up to 4.40 GHz Turbo)"
                ),
            )
            INSTANCE!!.productDao()!!.insert(
                Product(
                    "A Normal Desk",
                    2,
                    6,
                    10,
                    "Just your regular desk"
                ),
            )
            INSTANCE!!.productDao()!!.insert(
                Product(
                    "A Normal Spoon",
                    3,
                    13,
                    10,
                    "Just your regular spoon"
                ),
            )
            INSTANCE!!.productDao()!!.insert(
                Product(
                    "A Normal Celcius Thermometer",
                    4,
                    16,
                    10,
                    "In Celcius"
                ),
            )
            INSTANCE!!.productDao()!!.insert(
                Product(
                    "A Jack's Hammer",
                    5,
                    19,
                    10,
                    "It's Jack's"
                ),
            )
            INSTANCE!!.productDao()!!.insert(
                Product(
                    "A Blue Shirt",
                    6, 23,
                    10,
                    "It's Free"
                ),
            )
            INSTANCE!!.productDao()!!.insert(
                Product(
                    "A Ball",
                    7,
                    27,
                    10,
                    "It's Ligma"
                ),
            )
        }

        private fun addDefaultVariants() {
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Laptop",
                    1
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Handphone",
                    1
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Tablet",
                    1
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Camera",
                    1
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Printer",
                    1
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Desk",
                    2
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Chair",
                    2
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Filing Cabinet",
                    2
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Desk Lamp",
                    2
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Desktop Computer",
                    2
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Microwave",
                    3
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Oven",
                    3
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Cooking Utensils",
                    3
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Refrigerator",
                    3
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Blood Pressure Monitor",
                    4
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Thermometer",
                    4
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Surgical Equipment",
                    4
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Hearing Aids",
                    4
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Hammer",
                    5
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Screwdriver",
                    5
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Saw",
                    5
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Drill",
                    5
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Clothes",
                    6
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Shoes",
                    6
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Bags",
                    6
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Hats",
                    6
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Ball",
                    7
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Racket",
                    7
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Fitness Equipment",
                    7
                ),
            )
            INSTANCE!!.variantDao()!!.insert(
                Variant(
                    "Sport Shoes",
                    7
                ),
            )
        }

        private fun addLoginUser() {
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
}