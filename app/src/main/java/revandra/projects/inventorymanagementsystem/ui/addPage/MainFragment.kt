package revandra.projects.inventorymanagementsystem.ui.addPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import revandra.projects.inventorymanagementsystem.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        with(binding){
            viewPager.adapter = PageAdapter(requireActivity().supportFragmentManager, lifecycle)
            TabLayoutMediator(tabLayout, viewPager){ tab: TabLayout.Tab, position: Int ->
                val titles = arrayListOf("Product", "Variant")
                tab.text = titles[position]
            }.attach()
        }
        return binding.root
    }
}