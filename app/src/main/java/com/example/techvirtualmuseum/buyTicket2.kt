package com.example.techvirtualmuseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class buyTicket2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_ticket2)

        //listener para el boton que nos dirigira a la tercera actividad de compra de tickets
        val buyTicket3 : Button = findViewById(R.id.buyFinalTicket)
        buyTicket3.setOnClickListener {
            val intent : Intent = Intent(this, buyTicket3::class.java)
            startActivity(intent)
        }
    }
}