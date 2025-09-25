package com.example.myviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ContactUsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)

        // Configurar boton Back
        val btnBack: Button = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            finish() // Cierra esta actividad y vuelve a la anterior
        }
    }
}