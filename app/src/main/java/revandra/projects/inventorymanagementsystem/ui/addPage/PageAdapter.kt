package revandra.projects.inventorymanagementsystem.ui.addPage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import revandra.projects.inventorymanagementsystem.ui.addPage.category.AddVariantFragment
import revandra.projects.inventorymanagementsystem.ui.addPage.product.AddProductFragment

class PageAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
    :FragmentStateAdapter(fragmentManager,lifecycle)
{
    private val tabs = arrayListOf(
        AddProductFragment(),
        AddVariantFragment(),
    )
    override fun getItemCount(): Int {
        return tabs.size
    }

    override fun createFragment(position: Int): Fragment {
        return tabs[position]
    }
}