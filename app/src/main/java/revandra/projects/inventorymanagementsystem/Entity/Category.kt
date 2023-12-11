package revandra.projects.inventorymanagementsystem.Entity

import revandra.projects.inventorymanagementsystem.R


data class Category(
    val name: String = "",
    val idPhoto: Int = 0,
) {
    companion object {
        val categoryList by lazy {
            arrayListOf(
                Category("Electronics", R.drawable.electronics),
                Category("Office Supplies", R.drawable.office),
                Category("Kitchen Appliances", R.drawable.kitchen),
                Category("Medical Equipment", R.drawable.med),
                Category("Tools", R.drawable.tool),
                Category("Clothing and Accessories", R.drawable.clothes),
                Category("Sports Equipment", R.drawable.basket),
            )
        }
    }
}
