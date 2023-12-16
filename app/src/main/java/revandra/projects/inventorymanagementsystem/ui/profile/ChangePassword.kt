package revandra.projects.inventorymanagementsystem.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import revandra.projects.inventorymanagementsystem.Database.Databases
import revandra.projects.inventorymanagementsystem.Entity.User
import revandra.projects.inventorymanagementsystem.R
import revandra.projects.inventorymanagementsystem.Utility.CustomToastMaker
import revandra.projects.inventorymanagementsystem.Utility.SharedPrefManager
import revandra.projects.inventorymanagementsystem.databinding.FragmentChangePasswordBinding
import revandra.projects.inventorymanagementsystem.databinding.FragmentProfileBinding
import java.util.concurrent.Executors


class ChangePassword : Fragment() {
    private var _binding: FragmentChangePasswordBinding? = null
    private val binding get() = _binding!!
    private val db by lazy {
        Databases.getDatabase(requireContext())
    }
    private val prefManager by lazy {
        SharedPrefManager.getInstance(requireContext())
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangePasswordBinding.inflate(inflater, container, false)
        val root: View = binding.root
        with(binding){
            etConfPw.addTextChangedListener {
                if (it?.isEmpty() == true){
                    confirmPw.error = "Please fill out this form"
                }
                else{
                    confirmPw.error = null
                }
            }
            etNewPw.addTextChangedListener {
                if (it?.isEmpty() == true){
                    newPW.error = "Please fill out this form"
                }
                else{
                    newPW.error = null
                }
            }
            resetPW.setOnClickListener {
                var success = true
                if (etConfPw.text.toString() == ""){
                    confirmPw.error = "Please fill out this form"
                    success = false
                }
                else{ confirmPw.error = null }
                if (etNewPw.text.toString() == ""){
                    newPW.error = "Please fill out this form"
                    success = false
                }
                else{ newPW.error = null }

                if (success && etConfPw.text.toString() == etNewPw.text.toString()){
                    ResetPassword()
                }
                else if (etConfPw.text.toString() != etNewPw.text.toString()){
                    confirmPw.error = "Password don't match"
                    newPW.error = "Password don't match"

                }
            }
        }

        return root
    }

    private fun ResetPassword() {
        Executors.newSingleThreadExecutor().execute {
            db!!.loginDao()!!.update(
                User(
                    prefManager.currentUname(),
                    binding.etConfPw.text.toString().trim(),
                    prefManager.isLogged()
                )
            )
        }
        CustomToastMaker.makeCustomToast(requireContext(), "Password Changed Successfully!")
        findNavController().navigate(R.id.profileFragment)
    }

}