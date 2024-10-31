package com.example.myapplication

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.TextView
import com.example.myapplication.databinding.ActivityMainBinding
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputNumber: EditText = findViewById(R.id.inputNumber)
        val radioEven: RadioButton = findViewById(R.id.radioEven)
        val radioOdd: RadioButton = findViewById(R.id.radioOdd)
        val radioSquare: RadioButton = findViewById(R.id.radioSquare)
        val showButton = findViewById<Button>(R.id.showButton)
        val listView: ListView = findViewById(R.id.listView)
        val errorText: TextView = findViewById(R.id.errorText)

        showButton.setOnClickListener {
            val input = inputNumber.text.toString()
            if (input.isBlank()) {
                errorText.text = "Please enter a valid positive integer."
                errorText.visibility = TextView.VISIBLE
                return@setOnClickListener
            }

            val n = input.toIntOrNull()
            if (n == null || n < 1) {
                errorText.text = "Please enter a valid positive integer."
                errorText.visibility = TextView.VISIBLE
                return@setOnClickListener
            } else {
                errorText.visibility = TextView.GONE
            }

            val results = when {
                radioEven.isChecked -> (0..n).filter { it % 2 == 0 }
                radioOdd.isChecked -> (0..n).filter { it % 2 != 0 }
                radioSquare.isChecked -> (0..n).filter { kotlin.math.sqrt(it.toDouble()).toInt().toDouble() == kotlin.math.sqrt(it.toDouble()) }
                else -> emptyList()
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, results)
            listView.adapter = adapter
        }
    }
}
