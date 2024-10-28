package com.softworkshub.a7minworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.softworkshub.a7minworkout.databinding.ActivityHistoryScreenBinding

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

    }
}