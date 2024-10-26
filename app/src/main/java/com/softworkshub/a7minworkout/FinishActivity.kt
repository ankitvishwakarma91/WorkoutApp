package com.softworkshub.a7minworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.softworkshub.a7minworkout.databinding.ActivityExerciseBinding
import com.softworkshub.a7minworkout.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {

    private var binding : ActivityFinishBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)


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
}