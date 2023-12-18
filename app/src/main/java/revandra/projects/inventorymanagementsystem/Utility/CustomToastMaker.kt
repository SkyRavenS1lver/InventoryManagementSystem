package revandra.projects.inventorymanagementsystem.Utility

import android.R.id.message
import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import revandra.projects.inventorymanagementsystem.R
import revandra.projects.inventorymanagementsystem.databinding.ToastViewBinding


class CustomToastMaker {
    companion object{
        fun makeCustomToast(context: Context, string: String){
            val toast = Toast.makeText(context, string, Toast.LENGTH_SHORT)
            val binding:ToastViewBinding = ToastViewBinding.inflate(LayoutInflater.from(context))
            binding.toastText.text = string
            toast.setView(binding.root)
            toast.show()
        }
    }
}