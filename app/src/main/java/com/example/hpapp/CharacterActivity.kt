package com.example.hpapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterActivity : AppCompatActivity() {
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_character)

        val etCharacterId = findViewById<EditText>(R.id.et_character_id)
        val btnSearch = findViewById<Button>(R.id.btn_search_character)
        tvResult = findViewById(R.id.tv_character_result)

        btnSearch.setOnClickListener {
            val id = etCharacterId.text.toString().trim()
            if (id.isNotEmpty()) {
                searchCharacterById(id)
            } else {
                tvResult.text = "Por favor insira um ID válido."
            }
        }

        val btnBack = findViewById<Button>(R.id.btn_back_character)
        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun searchCharacterById(id: String) {
        lifecycleScope.launch {
            try {
                val character = withContext(Dispatchers.IO) {
                    RetrofitInstance.api.getCharacterById(id)
                }
                if (character.isNotEmpty()) {
                    val char = character[0] // aparentemente volta uma lista de tamanho 1
                    tvResult.text =
                        "Nome: ${char.name}\nCasa: ${char.house ?: ""}"
                } else {
                    tvResult.text = "Nenhum personagem encontrado."
                }
            } catch (e: Exception) {
                tvResult.text = "Erro: ${e.message}"
            }
        }
    }
}