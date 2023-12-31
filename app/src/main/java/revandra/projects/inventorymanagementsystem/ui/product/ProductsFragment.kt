package revandra.projects.inventorymanagementsystem.ui.product

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import revandra.projects.inventorymanagementsystem.Database.Databases
import revandra.projects.inventorymanagementsystem.R
import revandra.projects.inventorymanagementsystem.databinding.FragmentProductsBinding
import revandra.projects.inventorymanagementsystem.ui.search.SearchFragmentDirections


class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!
    private val db by lazy {
        Databases.getDatabase(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        with(binding){
            val args: ProductsFragmentArgs by navArgs()
            title.text = args.name
            try {
                val img = args.photo.toInt()
                image.setBackgroundResource(img)
            }
            catch (e:Exception){
                try {
                    Glide.with(requireContext()).load(Uri.parse(args.photo)).into(image)
                }
                catch (e:Exception){
                    image.setBackgroundResource(R.drawable.resource_default)
                }
            }
            db!!.productDao()!!.allProduct(args.idVariant.toString()).observe(requireActivity()){
                if (it.isEmpty()){placeHolder.visibility = View.VISIBLE}
                else{placeHolder.visibility = View.GONE}
                rvProduct.adapter = ProductAdapter(it){product ->
                    findNavController().navigate(
                        ProductsFragmentDirections.actionProductsFragmentToEditProductFragment(
                            product.idProduct
                        )
                    )
                }
                rvProduct.layoutManager = LinearLayoutManager(context)
            }

        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}