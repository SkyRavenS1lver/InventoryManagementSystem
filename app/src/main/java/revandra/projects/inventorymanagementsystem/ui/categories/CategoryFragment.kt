package revandra.projects.inventorymanagementsystem.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import revandra.projects.inventorymanagementsystem.Entity.Category
import revandra.projects.inventorymanagementsystem.databinding.FragmentCategoryBinding
import revandra.projects.inventorymanagementsystem.ui.categories.recyclerViewComp.CategoryAdapter

class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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
                adapter = CategoryAdapter(list)
                layoutManager = GridLayoutManager(requireContext(),2).apply {
                    spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
                        override fun getSpanSize(position: Int): Int {
                            return if (list.size % 2 == 0) {
                                1
                            } else {
                                if (position == list.size - 1) 2 else 1
                            }
                        }
                    }
                }
            }

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}