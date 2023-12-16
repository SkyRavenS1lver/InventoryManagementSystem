package revandra.projects.inventorymanagementsystem.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import revandra.projects.inventorymanagementsystem.Entity.Category
import revandra.projects.inventorymanagementsystem.databinding.CategoryRvBinding
typealias OnClick = (Category, Int) -> Unit


class CategoryAdapter(private val categoryList:ArrayList<Category>, private val onClick: OnClick)
    : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>()  {

    inner class CategoryViewHolder(private val binding: CategoryRvBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: Category, int: Int){
            with(binding){
                title.text = data.name
                image.setBackgroundResource(data.idPhoto)
                itemView.setOnClickListener {
                    onClick(data, int)
                }
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
        holder.bind(categoryList[position], position)
    }
}