package com.example.hpapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StaffActivity : AppCompatActivity() {
    private lateinit var tvStaffResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_staff)

        tvStaffResult = findViewById(R.id.tv_staff_result)
        fetchHogwartsStaff()

        val btnBack = findViewById<Button>(R.id.btn_back_staff)
        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun fetchHogwartsStaff() {
        lifecycleScope.launch {
            try {
                val staff = withContext(Dispatchers.IO) {
                    RetrofitInstance.api.getHogwartsStaff()
                }
                val staffNames = staff.joinToString(separator = "\n") { it.name }
                tvStaffResult.text = staffNames
            } catch (e: Exception) {
                tvStaffResult.text = "Erro: ${e.message}"
            }
        }
    }
}