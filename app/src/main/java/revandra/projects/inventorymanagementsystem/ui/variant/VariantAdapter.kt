package revandra.projects.inventorymanagementsystem.ui.variant

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import revandra.projects.inventorymanagementsystem.Database.Databases
import revandra.projects.inventorymanagementsystem.Entity.Variant
import revandra.projects.inventorymanagementsystem.R
import revandra.projects.inventorymanagementsystem.databinding.VariantRvBinding

typealias OnClick = (Variant) -> Unit

class VariantAdapter(private val context: FragmentActivity, private val variantList:List<Variant>, private val onClick: OnClick)
    : RecyclerView.Adapter<VariantAdapter.VariantViewHolder>()   {
    private val db by lazy {
        Databases.getDatabase(context)
    }
    private val defaultPhotos by lazy {
        arrayListOf(
            R.drawable.laptop, R.drawable.smartphone_2, R.drawable.tablet_2,
            R.drawable.camera, R.drawable.printer,R.drawable.desk, R.drawable.desk_chair, R.drawable.filling_cabinet,
            R.drawable.desk_lamp, R.drawable.workplace,R.drawable.microwave_1, R.drawable.oven_1, R.drawable.chef_1,
            R.drawable.fridge_1,R.drawable.blood_pressure_gauge, R.drawable.thermometer, R.drawable.surgical_instrument,
            R.drawable.hearing_aid,R.drawable.tool_box_1, R.drawable.drill_1, R.drawable.sawing_1,
            R.drawable.child_labour_1,R.drawable.polo, R.drawable.shoes, R.drawable.school_bag,
            R.drawable.hat,R.drawable.juggling__1__1, R.drawable.badminton_1, R.drawable.gym_1,
            R.drawable.football_shoes
        )
    }
    inner class VariantViewHolder(private val binding: VariantRvBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Variant){
            with(binding){
                db!!.productDao()!!.variantStock(data.idVariant.toString()).observe(context){
                    var message = "Stock: "
                    message += it?.toString() ?: "0"
                    subtitle.text = message
                }
                title.text = data.name
                if (data.imgPath == ""){
                    image.setBackgroundResource(defaultPhotos[data.idVariant-1])
                    data.imgPath = defaultPhotos[data.idVariant-1].toString()
                }
                else{
                    try {
                        Glide.with(itemView.context).load(Uri.parse(data.imgPath)).into(image)
                    }
                    catch (e:Exception){
                        image.setImageResource(R.drawable.resource_default)
                    }
                }
                itemView.setOnClickListener {
                    onClick(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VariantAdapter.VariantViewHolder {
        val binding = VariantRvBinding.inflate(LayoutInflater.from(parent.context))
        return VariantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VariantViewHolder, position: Int) {
        val variant = variantList[position]
        holder.bind(variant)

    }

    override fun getItemCount(): Int {
        return variantList.size
    }

}