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
import com.gb.gpacalculator.databinding.ActivityCseBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.MutableData
import com.google.firebase.database.Transaction


class CseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCseBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseReference = FirebaseDatabase.getInstance().getReference("cnt")

        binding.buttonSubmit.setOnClickListener { solve() }
    }

    private fun solve() {

        val g1 = binding.editTextDBE.text?.toString()
        val g2 = binding.editTextML.text?.toString()
        val g3 = binding.editTextOS.text?.toString()
        val g4 = binding.editTextDBEL.text?.toString()
        val g5 = binding.editTextMLL.text?.toString()
        val g6 = binding.editTextJava.text?.toString()
        val g7 = binding.editTextMP.text?.toString()
        val g8 = binding.editTextOSL.text?.toString()
        val g9 = binding.editTextPE.text?.toString()
        val g10 = binding.editTextOE.text?.toString()

        if(g1 == "" || g2 == "" || g3 == "" || g4 == "" || g5 == "" || g6 == "" || g7 == "" || g8 == "" || g9 == "" || g10 == ""){
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_LONG).show()
        }

        else{
            val creditSum = 22.0
            val sum = (g1!!.toInt() * 3) + (g2!!.toInt() * 3) +(g3!!.toInt() * 3) +(g4!!.toInt() * 1) +(g5!!.toInt() * 1) +(g6!!.toInt() * 3) +(g7!!.toInt() * 1) +(g8!!.toInt() * 1) +(g9!!.toInt() * 3) + (g10!!.toInt() * 3)
            val ans = sum / creditSum;

            val formattedAns = String.format("%.2f", ans)
            val roundedDrawable = ResourcesCompat.getDrawable(resources, R.drawable.rounded_bg, null)

            val titleText = SpannableString("Congratulations!")
            titleText.setSpan(StyleSpan(Typeface.BOLD), 0, titleText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            titleText.setSpan(ForegroundColorSpan(getColor(R.color.black)), 0, titleText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            val messageText = SpannableString("Your CGPA is 👉 $formattedAns")
            messageText.setSpan(StyleSpan(Typeface.ITALIC), 0, messageText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            messageText.setSpan(ForegroundColorSpan(getColor(R.color.black)), 0, messageText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            incrementFirebaseCount()

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

    private fun incrementFirebaseCount() {
        // Use a Firebase transaction to increment "cnt" atomically
        databaseReference.runTransaction(object : Transaction.Handler {
            override fun doTransaction(mutableData: MutableData): Transaction.Result {
                // Get the current count from Firebase
                var currentCount = mutableData.getValue(Int::class.java) ?: 0

                // Increment the count
                currentCount++

                // Update the count in Firebase
                mutableData.value = currentCount

                // Return the updated data
                return Transaction.success(mutableData)
            }

            override fun onComplete(
                databaseError: DatabaseError?,
                committed: Boolean,
                dataSnapshot: DataSnapshot?
            ) {
                // Handle the completion of the transaction if needed
                if (committed) {
                    // The transaction was successful
                    // You can update the UI or perform additional actions here
                } else {
                    // The transaction was not successful
                    // Handle the error if needed
                }
            }
        })
    }

}

