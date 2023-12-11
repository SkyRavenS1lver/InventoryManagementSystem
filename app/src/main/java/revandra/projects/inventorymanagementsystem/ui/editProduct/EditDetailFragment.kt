package revandra.projects.inventorymanagementsystem.ui.editProduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import revandra.projects.inventorymanagementsystem.Database.Databases
import revandra.projects.inventorymanagementsystem.R
import revandra.projects.inventorymanagementsystem.databinding.FragmentAddProductBinding
import revandra.projects.inventorymanagementsystem.databinding.FragmentEditDetailBinding

class EditDetailFragment : Fragment() {
    private val SELECT_IMAGE_REQUEST = 1
    private var selectedImagePath: String = ""

    private var _binding: FragmentEditDetailBinding? = null
    private val binding get() = _binding!!

    private val db by lazy {
        Databases.getDatabase(requireContext())
    }
    private var selectedVarId:Int = -1
    private var selectedCatId:Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_detail, container, false)
    }

}