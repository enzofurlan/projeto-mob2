package com.example.hpapp

import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HouseStudentsActivity : AppCompatActivity() {

    private lateinit var tvStudentsResult: TextView
    private lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_house_students)

        tvStudentsResult = findViewById(R.id.tv_students_result)
        radioGroup = findViewById(R.id.rg_houses)
        val btnSearch = findViewById<Button>(R.id.btn_search_students)

        btnSearch.setOnClickListener {
            val selectedHouse = when (radioGroup.checkedRadioButtonId) {
                R.id.rb_gryffindor -> "gryffindor"
                R.id.rb_hufflepuff -> "hufflepuff"
                R.id.rb_ravenclaw -> "ravenclaw"
                R.id.rb_slytherin -> "slytherin"
                else -> null
            }

            if (selectedHouse != null) {
                fetchStudentsByHouse(selectedHouse)
            } else {
                Toast.makeText(this, "Por favor selecione uma casa!", Toast.LENGTH_SHORT).show()
            }
        }

        val btnBack = findViewById<Button>(R.id.btn_back_house_students)
        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun fetchStudentsByHouse(house: String) {
        lifecycleScope.launch {
            try {
                val students = withContext(Dispatchers.IO) {
                    RetrofitInstance.api.getCharactersByHouse(house)
                }
                val studentNames = students.joinToString(separator = "\n") { it.name }
                tvStudentsResult.text = studentNames
            } catch (e: Exception) {
                tvStudentsResult.text = "Erro: ${e.message}"
            }
        }
    }
}