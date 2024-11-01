package com.softworkshub.a7minworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.softworkshub.a7minworkout.data.HistoryDao
import com.softworkshub.a7minworkout.data.WorkOutApp
import com.softworkshub.a7minworkout.databinding.ActivityHistoryScreenBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HistoryScreen : AppCompatActivity() {

    private var binding : ActivityHistoryScreenBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarHistoryScreen)

        if (supportActionBar != null){
            supportActionBar?.title = "HISTORY"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding?.toolbarHistoryScreen?.setNavigationOnClickListener {
            onBackPressed()
        }

        val historyDao = (application as WorkOutApp).db.historyDao()

        getAllCompleteDates(historyDao)

    }

    private fun getAllCompleteDates(historyDao: HistoryDao){
        lifecycleScope.launch {
            historyDao.fetchAllDate().collect{ allCompleteDates ->


                if (allCompleteDates.isNotEmpty()){
                    binding?.tvExrciseComp?.visibility = View.VISIBLE
                    binding?.rvHistoryScreen?.visibility = View.VISIBLE
                    binding?.tvHistoryNoDataAvailable?.visibility = View.GONE

                    binding?.rvHistoryScreen?.layoutManager = LinearLayoutManager(this@HistoryScreen)


                    val dates = ArrayList<String>()
                    for (date in allCompleteDates){
                        dates.add(date.date)
                    }

                    val historyAdapter = HistoryAdapter(ArrayList(dates))

                    binding?.rvHistoryScreen?.adapter = historyAdapter

                }else{
                    binding?.tvExrciseComp?.visibility = View.GONE
                    binding?.rvHistoryScreen?.visibility = View.GONE
                    binding?.tvHistoryNoDataAvailable?.visibility = View.VISIBLE
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}