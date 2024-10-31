package com.example.b2

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.b2.databinding.ActivityMainBinding

import android.text.Editable
import android.text.TextWatcher

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentList: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchBar: EditText = findViewById(R.id.searchBar)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)


        studentList = listOf(
            Student("a", "123456"),
            Student("b", "234567"),
            Student("c", "345678"),

        )

        studentAdapter = StudentAdapter(studentList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter


        searchBar.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().trim()
                if (query.length > 2) {
                    val filteredList = studentList.filter {
                        it.name.contains(query, ignoreCase = true) ||
                                it.mssv.contains(query, ignoreCase = true)
                    }
                    studentAdapter.filterList(filteredList)
                } else {
                    studentAdapter.filterList(studentList)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })
    }
}

