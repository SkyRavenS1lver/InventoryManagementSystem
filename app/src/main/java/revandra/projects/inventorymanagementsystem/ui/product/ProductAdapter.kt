package revandra.projects.inventorymanagementsystem.ui.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import revandra.projects.inventorymanagementsystem.Entity.Product
import revandra.projects.inventorymanagementsystem.databinding.ProductRvBinding


typealias OnClick = (Product) -> Unit
class ProductAdapter(private var productList:List<Product>, private val onClick: OnClick)
    : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>()   {
    inner class ProductViewHolder(private val binding: ProductRvBinding)
        :RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Product){
            with(binding){
                title.text = data.name
                stockCount.text = data.stock.toString()
                itemView.setOnClickListener {
                    onClick(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ProductRvBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }
    fun setData(list:List<Product>){
        productList = list
        notifyDataSetChanged()
    }
}