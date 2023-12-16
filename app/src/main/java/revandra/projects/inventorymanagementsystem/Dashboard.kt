package revandra.projects.inventorymanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import revandra.projects.inventorymanagementsystem.Database.Databases
import revandra.projects.inventorymanagementsystem.Utility.CustomToastMaker
import revandra.projects.inventorymanagementsystem.Utility.SharedPrefManager
import revandra.projects.inventorymanagementsystem.databinding.ActivityDashboardBinding
import java.util.concurrent.Executors

class Dashboard : AppCompatActivity() {
    var mLastClickTime:Long = 0
    private val binding by lazy {
        ActivityDashboardBinding.inflate(layoutInflater)
    }
    private val db by lazy {
        Databases.getDatabase(this)
    }
    private val prefManager by lazy {
        SharedPrefManager.getInstance(this)
    }
    lateinit var data1: LiveData<Int>
    lateinit var data2: LiveData<Int>
    lateinit var data3: LiveData<Int>
    lateinit var data4: LiveData<Int>
    lateinit var data5: LiveData<Int>
    lateinit var data6: LiveData<Int>
    lateinit var data7: LiveData<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (prefManager.isLogged() == "Super Admin") {
            binding.bgAdmin.visibility = View.GONE
            binding.containerChart.visibility = View.VISIBLE
            binding.btnAdd.visibility = View.VISIBLE
            val chartList = arrayListOf<PieEntry>()
            chartList.add(PieEntry(0f, "Electronics"))
            chartList.add(PieEntry(0f, "Office"))
            chartList.add(PieEntry(0f, "Kitchen"))
            chartList.add(PieEntry(0f, "Medical"))
            chartList.add(PieEntry(0f, "Tools"))
            chartList.add(PieEntry(0f, "Clothes"))
            chartList.add(PieEntry(0f, "Sport"))


            Executors.newSingleThreadExecutor().execute {
                data1 = db!!.productDao()!!.productFromCategory("1")
                data2 = db!!.productDao()!!.productFromCategory("2")
                data3 = db!!.productDao()!!.productFromCategory("3")
                data4 = db!!.productDao()!!.productFromCategory("4")
                data5 = db!!.productDao()!!.productFromCategory("5")
                data6 = db!!.productDao()!!.productFromCategory("6")
                data7 = db!!.productDao()!!.productFromCategory("7")
            }
            Thread.sleep(100)

            var dataset = PieDataSet(chartList, "")
            val colored = mutableListOf(
                getColor(R.color.electronics),
                getColor(R.color.office),
                getColor(R.color.kitchen),
                getColor(R.color.medical),
                getColor(R.color.tools),
                getColor(R.color.clothing),
                getColor(R.color.sport),
            )
            dataset.colors = colored
            with(binding.chart) {
                data = PieData(dataset)
                data.setValueTextColor(getColor(R.color.white))
                description.isEnabled = false
                legend.isEnabled = false
                setUsePercentValues(true)
                setDrawEntryLabels(false)
                setDrawSliceText(false)
                setEntryLabelColor(getColor(R.color.white))
                invalidate()
                data1.observe(this@Dashboard) {
                    if (it != null) {
                        chartList[0] = PieEntry(it.toFloat(), "Electronics")
                        dataset = PieDataSet(chartList, "")
                        dataset.colors = colored
                        data = PieData(dataset)
                        data.setValueTextColor(getColor(R.color.white))
                        invalidate()
                    }
                }
                data2.observe(this@Dashboard) {
                    if (it != null) {
                        chartList[1] = PieEntry(it.toFloat(), "Office")
                        dataset = PieDataSet(chartList, "")
                        dataset.colors = colored
                        data = PieData(dataset)
                        data.setValueTextColor(getColor(R.color.white))
                        invalidate()
                    }
                }
                data3.observe(this@Dashboard) {
                    if (it != null) {
                        chartList[2] = PieEntry(it.toFloat(), "Kitchen")
                        dataset = PieDataSet(chartList, "")
                        dataset.colors = colored
                        data = PieData(dataset)
                        data.setValueTextColor(getColor(R.color.white))
                        invalidate()
                    }
                }
                data4.observe(this@Dashboard) {
                    if (it != null) {
                        chartList[3] = PieEntry(it.toFloat(), "Medical")
                        dataset = PieDataSet(chartList, "")
                        dataset.colors = colored
                        data = PieData(dataset)
                        data.setValueTextColor(getColor(R.color.white))
                        invalidate()
                    }
                }
                data5.observe(this@Dashboard) {
                    if (it != null) {
                        chartList[4] = PieEntry(it.toFloat(), "Tools")
                        dataset = PieDataSet(chartList, "")
                        dataset.colors = colored
                        data = PieData(dataset)
                        data.setValueTextColor(getColor(R.color.white))
                        invalidate()
                    }
                }
                data6.observe(this@Dashboard) {
                    if (it != null) {
                        chartList[5] = PieEntry(it.toFloat(), "Clothes")
                        dataset = PieDataSet(chartList, "")
                        dataset.colors = colored
                        data = PieData(dataset)
                        data.setValueTextColor(getColor(R.color.white))
                        invalidate()
                    }
                }
                data7.observe(this@Dashboard) {
                    if (it != null) {
                        chartList[6] = PieEntry(it.toFloat(), "Sport")
                        dataset = PieDataSet(chartList, "")
                        dataset.colors = colored
                        data = PieData(dataset)
                        data.setValueTextColor(getColor(R.color.white))
                        invalidate()
                    }
                }
            }
        }
        else{
            binding.bgAdmin.visibility = View.VISIBLE
            binding.containerChart.visibility = View.GONE
            binding.btnAdd.visibility = View.GONE
        }



    }

    override fun onBackPressed() {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 3000){
            finishAffinity()
            finish()
        }
        else if (SystemClock.elapsedRealtime() - mLastClickTime > 3000){
            mLastClickTime = SystemClock.elapsedRealtime()
            CustomToastMaker.makeCustomToast(this, getString(R.string.toast_back))
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