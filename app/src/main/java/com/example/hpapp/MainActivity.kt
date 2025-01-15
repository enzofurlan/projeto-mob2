package com.example.hpapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_character).setOnClickListener {
            startActivity(Intent(this, CharacterActivity::class.java))
        }

        findViewById<Button>(R.id.btn_staff).setOnClickListener {
            startActivity(Intent(this, StaffActivity::class.java))
        }

        findViewById<Button>(R.id.btn_house).setOnClickListener {
            startActivity(Intent(this, HouseStudentsActivity::class.java))
        }

        findViewById<Button>(R.id.btn_exit).setOnClickListener {
            finishAffinity()
        }
    }
}