package revandra.projects.inventorymanagementsystem.ui.addPage.product

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
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import revandra.projects.inventorymanagementsystem.Dashboard
import revandra.projects.inventorymanagementsystem.Database.Databases
import revandra.projects.inventorymanagementsystem.Entity.Category
import revandra.projects.inventorymanagementsystem.Entity.Product
import revandra.projects.inventorymanagementsystem.Utility.CustomToastMaker
import revandra.projects.inventorymanagementsystem.databinding.FragmentAddProductBinding
import java.util.concurrent.Executors

class AddProductFragment : Fragment() {
    private val SELECT_IMAGE_REQUEST = 1
    private var selectedImagePath: String = ""

    private var _binding: FragmentAddProductBinding? = null
    private val binding get() = _binding!!

    private val db by lazy {
        Databases.getDatabase(requireContext())
    }
    private val isSuccess by lazy {
        MutableLiveData<Boolean?>(null)
    }
    private var selectedVarId:Int = -1
    private var selectedCatId:Int = -1
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
                    selectedCatId = p2+1
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
            image.setOnClickListener {
                startActivityForResult(Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    ,SELECT_IMAGE_REQUEST)
            }
            insertBtn.setOnClickListener {
                if (etProduct.text.isNotBlank() && etDescription.text.isNotBlank() && etStock.text.isNotBlank()
                    && selectedImagePath.isNotEmpty()){
                    Executors.newSingleThreadExecutor().execute {
                        db!!.productDao()!!.insert(
                            Product(
                                etProduct.text.toString().trim(),
                                selectedCatId,selectedVarId,
                                etStock.text.toString().toInt(),
                                etDescription.text.toString().trim(),
                                selectedImagePath
                            )
                        )
                        isSuccess.postValue(true)
                    }

                }
                else{
                    CustomToastMaker.makeCustomToast(requireContext(), "Please fill out every form and picture")}
            }
            isSuccess.observe(requireActivity()){
                if (it == true){
                    CustomToastMaker.makeCustomToast(requireContext(), "Product Added Successfully")
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