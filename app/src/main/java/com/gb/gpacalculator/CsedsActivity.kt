package com.gb.gpacalculator

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.gb.gpacalculator.databinding.ActivityCsedsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CsedsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCsedsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCsedsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSubmit.setOnClickListener { solve() }
    }

    private fun solve() {

        val g1 = binding.editTextDAA.text?.toString()
        val g2 = binding.editTextDBMS.text?.toString()
        val g3 = binding.editTextEDA.text?.toString()
        val g4 = binding.editTextDBMSL.text?.toString()
        val g5 = binding.editTextEDAL.text?.toString()
        val g6 = binding.editTextWTL.text?.toString()
        val g7 = binding.editTextMP.text?.toString()
        val g8 = binding.editTextPE.text?.toString()
        val g9 = binding.editTextOE.text?.toString()

        if(g1 == "" || g2 == "" || g3 == "" || g4 == "" || g5 == "" || g6 == "" || g7 == "" || g8 == "" || g9 == ""){
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_LONG).show()
        }
        else{
            val creditSum = 22.0
            val sum = (g1!!.toInt() * 4) + (g2!!.toInt() * 3) +(g3!!.toInt() * 3) +(g4!!.toInt() * 1) +(g5!!.toInt() * 1) +(g6!!.toInt() * 3) +(g7!!.toInt() * 1) +(g8!!.toInt() * 3) +(g9!!.toInt() * 3)
            val ans = sum / creditSum;

            val formattedAns = String.format("%.2f", ans)
            val roundedDrawable = ResourcesCompat.getDrawable(resources, R.drawable.rounded_bg, null)

            val titleText = SpannableString("Congratulations!")
            titleText.setSpan(StyleSpan(Typeface.BOLD), 0, titleText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            titleText.setSpan(ForegroundColorSpan(getColor(R.color.black)), 0, titleText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            val messageText = SpannableString("Your CGPA is 👉 $formattedAns")
            messageText.setSpan(StyleSpan(Typeface.ITALIC), 0, messageText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            messageText.setSpan(ForegroundColorSpan(getColor(R.color.black)), 0, messageText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            MaterialAlertDialogBuilder(this)
                .setTitle(titleText)
                .setBackground(roundedDrawable)
                .setMessage(messageText)
                .setPositiveButton("OK"){ dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}