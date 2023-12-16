package revandra.projects.inventorymanagementsystem.ui.profile

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import revandra.projects.inventorymanagementsystem.Login
import revandra.projects.inventorymanagementsystem.R
import revandra.projects.inventorymanagementsystem.Utility.CustomToastMaker
import revandra.projects.inventorymanagementsystem.Utility.SharedPrefManager
import revandra.projects.inventorymanagementsystem.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val prefManager by lazy {
        SharedPrefManager.getInstance(requireContext())
    }
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        with(binding){
            role.text = prefManager.isLogged()
            logout.setOnClickListener {
                Logout()
            }
            changePW.setOnClickListener {
                ChangePw()
            }
        }

        return root
    }

    private fun ChangePw() {
        findNavController().navigate(R.id.changePassword)
    }

    private fun Logout(){
        val alert  = AlertDialog.Builder(requireContext())
            alert.setTitle("Alert")
            .setMessage("Are you sure to log out from application?")
            .setPositiveButton("Yes"){ _, _ ->
                prefManager.logout()
                CustomToastMaker.makeCustomToast(requireContext(), "Successfully Logged Out")
                startActivity(Intent(context, Login::class.java))
            }.also {
                    it.setNegativeButton("No"){_,_->it.create().dismiss()}
                    it.show()
                }
            }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}