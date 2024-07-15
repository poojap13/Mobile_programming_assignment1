package com.example.assignment1_b

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var editTextAmount: EditText
    private lateinit var buttonConvert: Button
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.spinner)
        editTextAmount = findViewById(R.id.editTextAmount)
        buttonConvert = findViewById(R.id.buttonConvert)
        textViewResult = findViewById(R.id.textViewResult)

        // Set up Spinner with currency options
        val currencies = arrayOf("USD", "EUR", "GBP", "INR")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Set OnItemSelectedListener for Spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View, position: Int, id: Long) {
                // Perform action when an item is selected
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle case when nothing is selected
            }
        }

        // Set OnClickListener for Button
        buttonConvert.setOnClickListener {
            val amount = editTextAmount.text.toString().toDoubleOrNull()
            if (amount != null) {
                val selectedCurrency = spinner.selectedItem.toString()
                val convertedAmount = convertCurrency(amount, selectedCurrency)
                textViewResult.text = "Converted Amount: $convertedAmount $selectedCurrency"
            } else {
                Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun convertCurrency(amount: Double, currency: String): Double {
        return when (currency) {
            "USD" -> amount * 0.75
            "EUR" -> amount * 0.68
            "GBP" -> amount * 0.58
            "INR" -> amount * 56.27
            else -> amount
        }
    }
}
