package revandra.projects.inventorymanagementsystem.ui.categories.recyclerViewComp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import revandra.projects.inventorymanagementsystem.Entity.Category
import revandra.projects.inventorymanagementsystem.databinding.CategoryRvBinding

class CategoryAdapter(private val categoryList:ArrayList<Category>)
    : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>()  {



    inner class CategoryViewHolder(private val binding:CategoryRvBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data:Category){
            with(binding){
                title.text = data.name
                image.setBackgroundResource(data.idPhoto)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }
}