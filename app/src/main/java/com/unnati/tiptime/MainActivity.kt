package com.unnati.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.unnati.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculate.setOnClickListener{ calculateTip() }
    }

    fun calculateTip(){
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            binding.tipAmountShow.text = ""
            return
        }
        val selectId = binding.tipOptions.checkedRadioButtonId
        val percentId = when(selectId){
            R.id.amazing -> 0.20
            R.id.good -> 0.18
            else -> 0.15
        }
        var tip = percentId * cost
        val roundUp = binding.switchforroundtip.isChecked
        if(roundUp){
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipAmountShow.text = getString(R.string.tip_amount, formattedTip)

    }
}

