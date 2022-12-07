package com.example.techvirtualmuseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import java.lang.Integer.*


class buyTicket2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_ticket2)

        val cantidad : EditText = findViewById(R.id.cantidadJunior)
        var texto = parseInt(cantidad.text.toString())

        //boton con el que aumentamos el numero de entradas
        val aumentarBtnJunior : Button = findViewById(R.id.sumaJunior)
        aumentarBtnJunior.setOnClickListener {
            texto += 1
            cantidad.setText(texto)
        }

        //boton con el que aumentamos el numero de entradas
        val disminuirBtnJunior : Button = findViewById(R.id.restaJunior)
        disminuirBtnJunior.setOnClickListener {
            texto += (-1)
            cantidad.setText(texto)
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





