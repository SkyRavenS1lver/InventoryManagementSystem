package revandra.projects.inventorymanagementsystem.ui.addPage.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import revandra.projects.inventorymanagementsystem.Database.Databases
import revandra.projects.inventorymanagementsystem.Entity.Category
import revandra.projects.inventorymanagementsystem.Entity.Variant
import revandra.projects.inventorymanagementsystem.R
import revandra.projects.inventorymanagementsystem.databinding.FragmentAddProductBinding
import java.util.concurrent.Executors

class AddProductFragment : Fragment() {

    private var _binding: FragmentAddProductBinding? = null
    private val binding get() = _binding!!

    private val db by lazy {
        Databases.getDatabase(requireContext())
    }
    private var selectedVarId:Int = -1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddProductBinding.inflate(inflater, container, false)

        with(binding){
            spinnerCat.adapter = ArrayAdapter(
                requireContext(), android.R.layout.simple_list_item_1, Category.categoryList
                )
            spinnerCat.onItemSelectedListener = object : OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    db!!.variantDao()!!.variantFromIdCategory((p2+1).toString()).observe(requireActivity()){
                        binding.variantContainer.visibility = View.VISIBLE
                        binding.spinnerVar.adapter = ArrayAdapter(
                            requireContext(), android.R.layout.simple_list_item_1, it
                        )
                        binding.spinnerVar.onItemSelectedListener = object : OnItemSelectedListener{
                            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                                selectedVarId = it[p2].idVariant
                            }

                            override fun onNothingSelected(p0: AdapterView<*>?) {
                            }
                        }
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
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