package revandra.projects.inventorymanagementsystem.Utility

import android.R.id.message
import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import revandra.projects.inventorymanagementsystem.R


class CustomToastMaker {
    companion object{
        fun makeCustomToast(context: Context, string: String){
            val toast = Toast.makeText(context, string, Toast.LENGTH_SHORT)
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.toast_view, null)
            val text: TextView = view.findViewById(R.id.toastText)
            text.text = string
            toast.setView(view)
            toast.show()
        }
    }
}