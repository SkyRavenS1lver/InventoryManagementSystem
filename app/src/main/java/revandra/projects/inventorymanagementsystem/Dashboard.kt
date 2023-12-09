package revandra.projects.inventorymanagementsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast

class Dashboard : AppCompatActivity() {
    var mLastClickTime:Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

    }

    override fun onBackPressed() {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 3000){
            finishAffinity()
            finish()
        }
        else if (SystemClock.elapsedRealtime() - mLastClickTime > 3000){
            mLastClickTime = SystemClock.elapsedRealtime()
            Toast.makeText(this, getString(R.string.toast_back), Toast.LENGTH_SHORT).show()
        }
        else{
            super.onBackPressed()
        }
    }
}