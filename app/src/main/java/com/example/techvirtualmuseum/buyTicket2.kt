package com.example.techvirtualmuseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView


class buyTicket2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_ticket2)

        //constante que nos servira para sumar o restar de uno en uno en los botones
        val valor : Int = 1

        val cantidad : TextView = findViewById(R.id.cantidadJunior)

        val aumentarBtnJunior : Button = findViewById(R.id.sumaJunior)
        aumentarBtnJunior.setOnClickListener {
            var numero = cantidad.text.toString()
            numero += valor
            cantidad.text = numero

        }

        val disminuirBtnJunior : Button = findViewById(R.id.restaJunior)
        disminuirBtnJunior.setOnClickListener {



        }


        //boton que nos llevara a la actvidad anterior
        val backButton : ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent : Intent = Intent(this, buyTicket::class.java)
            startActivity(intent)
        }

        //listener para el boton que nos dirigira a la tercera actividad de compra de tickets
        val buyTicket3 : Button = findViewById(R.id.buyFinalTicket)
        buyTicket3.setOnClickListener {
            val intent : Intent = Intent(this, buyTicket3::class.java)
            startActivity(intent)
        }
    }

}





