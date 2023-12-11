package revandra.projects.inventorymanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import revandra.projects.inventorymanagementsystem.databinding.ActivityDashboardBinding

class Dashboard : AppCompatActivity() {
    var mLastClickTime:Long = 0
    private val binding by lazy {
        ActivityDashboardBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val chartList = arrayListOf<PieEntry>()
        chartList.add(PieEntry(80f, "Electronics"))
        chartList.add(PieEntry(20f, "Office"))
        chartList.add(PieEntry(50f, "Tools"))
        chartList.add(PieEntry(10f, "Kitchen"))
        val dataset = PieDataSet(chartList, "")
        dataset.colors = intArrayOf(
            getColor(R.color.electronics),
            getColor(R.color.office),
            getColor(R.color.tools),
            getColor(R.color.kitchen),
            getColor(R.color.medical),
            getColor(R.color.sport),
            getColor(R.color.clothing),
        ).toMutableList()
        with(binding.chart){
            data = PieData(dataset)
            data.setValueTextColor(getColor(R.color.white))
            description.isEnabled = false
            legend.isEnabled = false
            setDrawEntryLabels(false)
            setDrawSliceText(false)
            setEntryLabelColor(getColor(R.color.white))
            animateY(1000)
            invalidate()
        }

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

    fun toProfile(view: View) {
        Intent(this, MainActivity::class.java).putExtra("destination", "profile")
            .also {
                startActivity(it)
            }
    }

    fun toCategories(view: View) {
        Intent(this, MainActivity::class.java).putExtra("destination", "category")
            .also {
                startActivity(it)
            }
    }
    fun toSearch(view: View) {
        Intent(this, MainActivity::class.java).putExtra("destination", "search")
            .also {
                startActivity(it)
            }
    }
    fun toAdd(view: View) {
        Intent(this, MainActivity::class.java).putExtra("destination", "add")
            .also {
                startActivity(it)
            }
    }
}