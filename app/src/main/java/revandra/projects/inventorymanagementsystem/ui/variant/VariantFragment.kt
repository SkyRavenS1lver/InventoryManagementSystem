package revandra.projects.inventorymanagementsystem.ui.variant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import revandra.projects.inventorymanagementsystem.Database.Databases
import revandra.projects.inventorymanagementsystem.databinding.FragmentVariantBinding


class VariantFragment : Fragment() {
    private var _binding: FragmentVariantBinding? = null
    private val binding get() = _binding!!
    private val db by lazy {
        Databases.getDatabase(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVariantBinding.inflate(inflater, container, false)

        with(binding){
            val args: VariantFragmentArgs by navArgs()
            title.text = args.name
            image.setBackgroundResource(args.IDPHOTO)
            db!!.variantDao()!!.variantFromIdCategory((args.position+1).toString()).observe(requireActivity()){
                rvVariant.adapter = VariantAdapter(requireActivity(), it){variant->
                    findNavController().navigate(
                        VariantFragmentDirections.actionVariantFragmentToProductsFragment(
                            variant.name,
                            variant.imgPath,
                            variant.idVariant
                        )
                    )
                }
                rvVariant.layoutManager = LinearLayoutManager(context)
            }
        }
        return binding.root
    }

}