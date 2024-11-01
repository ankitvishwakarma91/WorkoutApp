package com.softworkshub.a7minworkout


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.softworkshub.a7minworkout.data.HistoryDao
import com.softworkshub.a7minworkout.data.HistoryEntity
import com.softworkshub.a7minworkout.data.WorkOutApp
import com.softworkshub.a7minworkout.databinding.ActivityExerciseBinding
import com.softworkshub.a7minworkout.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FinishActivity : AppCompatActivity() {

    private var binding : ActivityFinishBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        val dao = (application as WorkOutApp).db.historyDao()
        addDateToDatabase(dao)


        setSupportActionBar(binding?.toolbarFinishExercise)

        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarFinishExercise?.setNavigationOnClickListener{
            onBackPressed()
        }

        binding?.btnFinished?.setOnClickListener {
            finish()
        }
    }

    private fun addDateToDatabase(historyDao: HistoryDao){
        val c = Calendar.getInstance()
        val dateTime = c.time
        Log.e("Date: ","" + dateTime)

        val sdf = SimpleDateFormat("dd MM yyyy HH:mm:ss",Locale.getDefault())
        val date = sdf.format(dateTime)
        Log.e("Formatted Date",""+date)

        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(date = date))
            Log.e("Date : ",
                "Added...")
        }
    }
}