package com.gb.gpacalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gb.gpacalculator.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.lang.NullPointerException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCSE.setOnClickListener { startActivity(Intent(this, CseActivity::class.java)) }
        binding.buttonCSEAIML.setOnClickListener { startActivity(Intent(this, CseaimlActivity::class.java)) }
        binding.buttonCSEDS.setOnClickListener { startActivity(Intent(this, CsedsActivity::class.java)) }

    }
}