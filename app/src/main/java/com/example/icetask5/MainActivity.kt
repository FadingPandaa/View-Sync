package com.example.icetask5

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // Declares UI
    private lateinit var etFirstNumber: EditText
    private lateinit var etSecondNumber: EditText
    private lateinit var tvResultText: TextView
    private lateinit var btnAdd: Button
    private lateinit var btnSub: Button
    private lateinit var btnMultiply: Button
    private lateinit var btnDivision: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // links the code to UI file

        // Initialize the UI components by finding them in the layout
        etFirstNumber = findViewById(R.id.etFirstNumber)
        etSecondNumber = findViewById(R.id.etSecondNumber)
        tvResultText = findViewById(R.id.tvResultText)
        btnAdd = findViewById(R.id.btn_add)
        btnSub = findViewById(R.id.btn_sub)
        btnMultiply = findViewById(R.id.btn_mutiply)
        btnDivision = findViewById(R.id.btn_division)

        // Set click listeners for each button
        btnAdd.setOnClickListener(this::onClick)
        btnSub.setOnClickListener(this::onClick)
        btnMultiply.setOnClickListener(this::onClick)
        btnDivision.setOnClickListener(this::onClick)
    }

    // fixed onClick function
    private fun onClick(v: View) {
        // Gets text from both input fields as strings
        val firstInputStr = etFirstNumber.text.toString()
        val secondInputStr = etSecondNumber.text.toString()

        // Variables to hold
        var firstNumber: Double? = null
        var secondNumber: Double? = null
        var result: Double = 0.0


        try {
            // Handle empty or blank input by treating it as invalid
            if (firstInputStr.isNotBlank()) {
                firstNumber = firstInputStr.toDouble()
            } else {
                throw NumberFormatException("First number is empty")
            }
            if (secondInputStr.isNotBlank()) {
                secondNumber = secondInputStr.toDouble()
            } else {
                throw NumberFormatException("Second number is empty")
            }
        } catch (e: NumberFormatException) {
            // Display a user-friendly error message and exit the function
            tvResultText.text = "Error: Please enter valid numbers"
            return
        }

        // Perform calculation based on which button was clicked
        when (v.id) {
            R.id.btn_add -> {
                // Addition
                result = firstNumber + secondNumber
            }
            R.id.btn_sub -> {
                // Subtraction
                result = firstNumber - secondNumber
            }
            R.id.btn_division -> {
                // Division
                if (secondNumber == 0.0) {
                    tvResultText.text = "Error: Cannot divide by zero"
                    return
                }
                result = firstNumber / secondNumber
            }
            R.id.btn_mutiply -> {
                // Multiplication
                result = firstNumber * secondNumber
            }
            else -> {
                // Unknown button
                return
            }
        }

        // Display the result. Remove trailing ".0" if the result is a whole number.
        val resultText = if (result == result.toLong().toDouble()) {
            result.toLong().toString()
        } else {
            result.toString()
        }
        tvResultText.text = resultText
    }
}