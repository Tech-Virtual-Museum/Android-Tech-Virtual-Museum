package com.example.techvirtualmuseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ticketBought : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_bought)

        //listener para el boton que nos lleve a la pantalla de inicio
        val goBackButton: Button = findViewById(R.id.goback)
        goBackButton.setOnClickListener {
            val intent : Intent = Intent(this, login::class.java)
            startActivity(intent)
        }
    }
}