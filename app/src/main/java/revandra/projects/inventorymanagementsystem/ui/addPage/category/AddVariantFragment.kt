package revandra.projects.inventorymanagementsystem.ui.addPage.category

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import revandra.projects.inventorymanagementsystem.Dashboard
import revandra.projects.inventorymanagementsystem.Database.Databases
import revandra.projects.inventorymanagementsystem.Entity.Category
import revandra.projects.inventorymanagementsystem.Entity.Variant
import revandra.projects.inventorymanagementsystem.databinding.FragmentAddVariantBinding
import java.util.concurrent.Executors


class AddVariantFragment : Fragment() {
    private var _binding: FragmentAddVariantBinding? = null
    private val SELECT_IMAGE_REQUEST = 1
    private var selectedImagePath: String = ""
    private var catId = -1

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
            spinnerCat.onItemSelectedListener = object :OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    catId = p2+1
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
            image.setOnClickListener {
                startActivityForResult(
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    ,SELECT_IMAGE_REQUEST)
            }
            insertBtn.setOnClickListener {
                Executors.newSingleThreadExecutor().execute {
                    db!!.variantDao()!!.insert(
                        Variant(
                            etVariant.text.toString(),
                            catId,
                            selectedImagePath
                        )
                    )
                    startActivity(Intent(requireContext(), Dashboard::class.java))
                    requireActivity().finish()
                }

            }
        }
        return binding.root
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SELECT_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri: Uri = data.data!!
            selectedImagePath = selectedImageUri.toString()
            println(selectedImageUri)
            Glide.with(this)
                .load(selectedImageUri)
                .into(binding.image)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}