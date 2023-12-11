package revandra.projects.inventorymanagementsystem.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import revandra.projects.inventorymanagementsystem.Database.Databases
import revandra.projects.inventorymanagementsystem.Entity.Product
import revandra.projects.inventorymanagementsystem.databinding.FragmentSearchBinding
import revandra.projects.inventorymanagementsystem.ui.product.ProductAdapter

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val db by lazy {
        Databases.getDatabase(requireContext())
    }
    private val searchResult by lazy {
        arrayListOf<Product>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        with(binding){

            db!!.productDao()!!.allProducts.observe(requireActivity()){

                rvSearch.adapter = ProductAdapter(it){product ->

                }
                rvSearch.layoutManager = LinearLayoutManager(requireContext())
                searchBar.addTextChangedListener {text->
                    if (it?.isEmpty() == true){
                        (rvSearch.adapter as ProductAdapter).setData(it)
                    }
                    else {(rvSearch.adapter as ProductAdapter).setData(
                        it.filter { product ->
                        product.name.lowercase().contains(text.toString().lowercase())
                    })}
                }
            }
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}