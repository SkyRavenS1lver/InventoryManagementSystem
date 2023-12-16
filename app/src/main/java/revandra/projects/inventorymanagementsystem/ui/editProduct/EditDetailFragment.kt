package revandra.projects.inventorymanagementsystem.ui.editProduct

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
import android.widget.ArrayAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import revandra.projects.inventorymanagementsystem.Dashboard
import revandra.projects.inventorymanagementsystem.Database.Databases
import revandra.projects.inventorymanagementsystem.Entity.Category
import revandra.projects.inventorymanagementsystem.Entity.Product
import revandra.projects.inventorymanagementsystem.Entity.Variant
import revandra.projects.inventorymanagementsystem.R
import revandra.projects.inventorymanagementsystem.Utility.CustomToastMaker
import revandra.projects.inventorymanagementsystem.databinding.FragmentEditDetailBinding
import revandra.projects.inventorymanagementsystem.databinding.FragmentEditProductBinding
import revandra.projects.inventorymanagementsystem.ui.variant.VariantAdapter
import java.util.concurrent.Executors

class EditDetailFragment : Fragment() {
    private val SELECT_IMAGE_REQUEST = 1
    private var selectedImagePath: String = ""
    private val currentProduct = MutableLiveData(Product())
    private val tempData = MutableLiveData(listOf(Variant()))


    private var _binding: FragmentEditDetailBinding? = null
    private val binding get() = _binding!!

    private val db by lazy {
        Databases.getDatabase(requireContext())
    }
    private var selectedVarId:Int = -1
    private var selectedCatId:Int = -1
    private var isSelected = false
    private val isSuccess by lazy {
        MutableLiveData<Boolean?>(null)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args:EditDetailFragmentArgs by navArgs()
        _binding = FragmentEditDetailBinding.inflate(LayoutInflater.from(requireContext()))
        with(binding){
            spinnerCat.adapter = ArrayAdapter(
                requireContext(), android.R.layout.simple_list_item_1, Category.categoryList
            )
            spinnerCat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    selectedCatId = p2+1
                    db!!.variantDao()!!.variantFromIdCategory((p2+1).toString()).observe(requireActivity()){
                        binding.spinnerVar.adapter = ArrayAdapter(
                            requireContext(), android.R.layout.simple_list_item_1, it
                        )
                        binding.spinnerVar.onItemSelectedListener = object :
                            AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                                selectedVarId = it[p2].idVariant
                            }

                            override fun onNothingSelected(p0: AdapterView<*>?) {
                            }
                        }
                        if (!isSelected){
                            tempData.postValue(it)
                        }
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
                db!!.productDao()!!.productFromId(args.idProduct.toString()).observe(requireActivity()) {
                    currentProduct.postValue(it)
                }



            currentProduct.observe(requireActivity()){
                nameTv.setText(it.name)
                selectedImagePath = it.imagePath
                try {
                    Glide.with(requireContext()).load(Uri.parse(it.imagePath)).into(image)
                }
                catch (e:Exception){
                    image.setImageResource(R.drawable.resource_default)
                }
                spinnerCat.setSelection(it.idCategory-1)
                descriptionTv.setText(it.description)
            }
            tempData.observe(requireActivity()){
                if (!isSelected) {
                    it.forEachIndexed { id, variant ->
                        if (variant.idVariant == (currentProduct.value?.idVariant ?: -1) &&(variant.name != "")) {
                            isSelected = true
                            spinnerVar.setSelection(id)
                            return@forEachIndexed
                        }
                    }
                }
            }
            image.setOnClickListener {
                startActivityForResult(Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    ,SELECT_IMAGE_REQUEST)
            }
            saveBtn.setOnClickListener {
                Executors.newSingleThreadExecutor().execute {
                    db!!.productDao()!!.update(
                        Product(
                            nameTv.text.toString(),
                            selectedCatId, selectedVarId,
                            currentProduct.value?.stock?:0,
                            descriptionTv.text.toString(),
                            selectedImagePath,
                            args.idProduct,
                        )
                    )
                    isSuccess.postValue(true)
                }
            }
            isSuccess.observe(requireActivity()){
                if (it == true){
                    CustomToastMaker.makeCustomToast(requireContext(), "Product Detail Successfully Changed")
                    findNavController().popBackStack()
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