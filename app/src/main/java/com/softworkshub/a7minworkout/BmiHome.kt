package com.softworkshub.a7minworkout


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

import com.softworkshub.a7minworkout.databinding.ActivityBmiHomeBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BmiHome : AppCompatActivity() {

    companion object {
        private const val METRIC_UNITS_VIEW = "METRIC_UNITS_VIEW"
        private const val US_UNITS_VIEW = "US_UNITS_VIEW"
    }

    private var binding: ActivityBmiHomeBinding? = null

    private var currentVisibleView: String = METRIC_UNITS_VIEW


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarCalculateBmi)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "Calculate Bmi"
        }

        binding?.toolbarCalculateBmi?.setNavigationOnClickListener {
            onBackPressed()
        }

        makeVisibleMetricUnitsView()

        binding?.rgUnits?.setOnCheckedChangeListener { _, checkdId: Int ->
            if (checkdId == R.id.rbMetricUnits) {
                makeVisibleMetricUnitsView()
            } else {
                makeVisibleUsUnitsView()
            }
        }

        binding?.btnCalculate?.setOnClickListener {
            calculateResult()
        }

    }

    private fun calculateResult() {
        if (currentVisibleView == METRIC_UNITS_VIEW) {
            if (validateMetricUnit()) {
                val weightValue: Float = binding?.tIeTextWeight?.text.toString().toFloat()

                val heightValue: Float = binding?.tIeTextHeight?.text.toString().toFloat() / 100

                val resultBmi: Float = weightValue / (heightValue * heightValue)

                displayBmiResult(resultBmi)
            } else {
                Toast.makeText(this, "Please Enter the Valid Details", Toast.LENGTH_SHORT).show()
            }
        } else {

            if (validateUsMetricUnit()) {
                val weightVal: Float = binding?.etUsMetricUnitWeight?.text.toString().toFloat()
                val heightInFeet: String = binding?.etUsMetricUnitHeightFeet?.text.toString()
                val heightInInch: String = binding?.etUsMetricUnitHeightInch?.text.toString()

                val heightval = heightInInch.toFloat() + heightInFeet.toFloat() * 12

                val resultUsMetric = 703 *  (weightVal / (heightval * heightval))

                displayBmiResult(resultUsMetric.toFloat())
            } else {
                Toast.makeText(
                    this,
                    "Please Enter valid feet and inch and weight ",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }


    private fun makeVisibleMetricUnitsView() {
        currentVisibleView = METRIC_UNITS_VIEW
        binding?.tILayoutWeight?.visibility = View.VISIBLE
        binding?.tILayoutHeight?.visibility = View.VISIBLE
        binding?.tilUsMetricUnitWeight?.visibility = View.GONE
        binding?.tilMetricUsUnitHeightFeet?.visibility = View.GONE
        binding?.tilMetricUsUnitHeightInch?.visibility = View.GONE

        binding?.tIeTextWeight?.text!!.clear()
        binding?.tIeTextHeight?.text!!.clear()

        binding?.llDiplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun makeVisibleUsUnitsView() {
        currentVisibleView = US_UNITS_VIEW
        binding?.tILayoutWeight?.visibility = View.INVISIBLE
        binding?.tILayoutHeight?.visibility = View.INVISIBLE
        binding?.tilUsMetricUnitWeight?.visibility = View.VISIBLE
        binding?.tilMetricUsUnitHeightFeet?.visibility = View.VISIBLE
        binding?.tilMetricUsUnitHeightInch?.visibility = View.VISIBLE

        binding?.etUsMetricUnitWeight?.text!!.clear() // weight value is cleared.
        binding?.etUsMetricUnitHeightFeet?.text!!.clear() // height feet value is cleared.
        binding?.etUsMetricUnitHeightInch?.text!!.clear()  // height inch is cleared.

        binding?.llDiplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun displayBmiResult(result: Float) {


        val bmiLabel: String
        val bmiDescription: String

        if (result.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (result.compareTo(15f) > 0 && result.compareTo(16f) <= 0
        ) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
        } else if (result.compareTo(16f) > 0 && result.compareTo(18.5f) <= 0
        ) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (result.compareTo(18.5f) > 0 && result.compareTo(25f) <= 0
        ) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (result.compareTo(25f) > 0 && result.compareTo(30f) <= 0
        ) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (result.compareTo(30f) > 0 && result.compareTo(35f) <= 0
        ) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (result.compareTo(35f) > 0 && result.compareTo(40f) <= 0
        ) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        //Use to set the result layout visible
        binding?.llDiplayBMIResult?.visibility = View.VISIBLE

        val bmiValue = BigDecimal(result.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

//        binding?.tvWeightShow?.text = bmiValue // Value is set to TextView
//        binding?.tvMessageWeight?.text = bmiLabel // Label is set to TextView
//        binding?.tvmsg?.text = bmiDescription // Description is set to TextView

        binding?.apply {
            tvWeightShow.text = bmiValue
            tvMessageWeight.text = bmiLabel
            tvmsg.text = bmiDescription
        }
    }

    private fun validateMetricUnit(): Boolean {
        var isValid = true
        if (binding?.tIeTextWeight?.text.toString().isEmpty()) {
            isValid = false;
        } else if (binding?.tIeTextHeight?.text.toString().isEmpty()) {
            isValid = false;
        }
        return isValid
    }

    private fun validateUsMetricUnit(): Boolean {
        return when {
            binding?.etUsMetricUnitWeight?.text.toString().isEmpty() -> false
            binding?.etUsMetricUnitHeightFeet?.text.toString().isEmpty() -> false
            binding?.etUsMetricUnitHeightInch?.text.toString().isEmpty() -> false
            else -> true
        }
    }

}

