package revandra.projects.inventorymanagementsystem.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import revandra.projects.inventorymanagementsystem.Entity.Category
import revandra.projects.inventorymanagementsystem.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        with(binding){
            categoryRv.apply {
                val list = Category.categoryList
                adapter = CategoryAdapter(list) {data, position ->
                    val action =
                        CategoryFragmentDirections.actionNavigationCategoryToVariantFragment(
                            data.name,
                            data.idPhoto,
                            position
                        )
                    findNavController().navigate(action)
                }
                layoutManager = GridLayoutManager(context,2)
            }

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}