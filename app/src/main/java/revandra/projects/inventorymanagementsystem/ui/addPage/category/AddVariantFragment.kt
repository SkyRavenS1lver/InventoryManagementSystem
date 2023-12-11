package revandra.projects.inventorymanagementsystem.ui.addPage.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import revandra.projects.inventorymanagementsystem.Database.Databases
import revandra.projects.inventorymanagementsystem.Entity.Category
import revandra.projects.inventorymanagementsystem.databinding.FragmentAddVariantBinding


class AddVariantFragment : Fragment() {
    private var _binding: FragmentAddVariantBinding? = null
    private val binding get() = _binding!!
    private val db by lazy {
        Databases.getDatabase(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddVariantBinding.inflate(inflater, container, false)
        with(binding){
            spinnerCat.adapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,
                Category.categoryList)
        }
        return binding.root
    }

}