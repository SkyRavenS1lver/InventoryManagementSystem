package revandra.projects.inventorymanagementsystem.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import revandra.projects.inventorymanagementsystem.Database.Databases
import revandra.projects.inventorymanagementsystem.databinding.FragmentSearchBinding
import revandra.projects.inventorymanagementsystem.ui.product.ProductAdapter

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val db by lazy {
        Databases.getDatabase(requireContext())
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
                    findNavController().navigate(
                        SearchFragmentDirections.actionNavigationSearchToEditProductFragment(
                            product.idProduct
                        )
                    )

                }
                rvSearch.layoutManager = LinearLayoutManager(context)
                searchBar.addTextChangedListener {text->
                    if (it?.isEmpty() == true){
                        (rvSearch.adapter as ProductAdapter).setData(it)
                    }
                    else {(rvSearch.adapter as ProductAdapter).setData(
                        it.filter { product ->
                        product.name.lowercase().contains(text.toString().lowercase())
                    }.also {
                        if (it.isEmpty()){
                            placeHolder.visibility = View.VISIBLE
                        }
                            else{
                            placeHolder.visibility = View.GONE
                            }
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