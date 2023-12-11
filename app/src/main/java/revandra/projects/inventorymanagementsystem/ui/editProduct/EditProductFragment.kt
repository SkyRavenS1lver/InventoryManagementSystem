package revandra.projects.inventorymanagementsystem.ui.editProduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import revandra.projects.inventorymanagementsystem.Database.Databases
import revandra.projects.inventorymanagementsystem.Entity.Category
import revandra.projects.inventorymanagementsystem.Entity.Product
import revandra.projects.inventorymanagementsystem.R
import revandra.projects.inventorymanagementsystem.databinding.FragmentEditProductBinding
import java.util.concurrent.Executors

class EditProductFragment : Fragment() {
    private var _binding: FragmentEditProductBinding? = null
    private val binding get() = _binding!!
    private val db by lazy {
        Databases.getDatabase(requireContext())
    }
    private val stock = MutableLiveData(0)
    private val currentProduct = MutableLiveData<Product?>(Product())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val args:EditProductFragmentArgs by navArgs()

        _binding = FragmentEditProductBinding.inflate(LayoutInflater.from(requireContext()))
        with(binding){
            Executors.newSingleThreadExecutor().execute {
                db!!.productDao()!!.productFromId(args.idProduct.toString()).also {
                    currentProduct.postValue(it)
                    categoryTv.text = Category.categoryList[it.idCategory-1].name
                    Executors.newSingleThreadExecutor().execute {
                        variant.text = db!!.variantDao()!!.variantFromId(it.idVariant.toString())
                    }
                    nameTv.text = it.name
                    descriptionTv.text = it.description
                    stock.postValue(it.stock)
                    if (it.imagePath == ""){ image.setImageResource(R.drawable.resource_default) }
                    else{ Glide.with(requireContext()).load(it.imagePath).into(image) }
                }
            }
            stock.observe(requireActivity()){
                stockCount.setText(stock.value.toString())
            }
            addButton.setOnClickListener {
                stockCount.setText((stockCount.text.toString().toInt()+1).toString())
            }
            minusButton.setOnClickListener {
                val stock = stockCount.text.toString().toInt()
                if (stock > 0){ stockCount.setText((stock-1).toString()) }
            }
            saveBtn.setOnClickListener {
                val stocks = stockCount.text.toString().toInt()
                Executors.newSingleThreadExecutor().execute {
                    currentProduct.value!!.stock = stocks
                    db!!.productDao()!!.update(currentProduct.value!!)
                    currentProduct.postValue(null)
                }
                currentProduct.observe(requireActivity()){
                    if (currentProduct.value == null){
                        findNavController().popBackStack()
                    }
                }
            }
        }

        return binding.root
    }

}