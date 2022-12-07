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


        //JUNIOR - Aumentamos o disminuimos en 1 el numero de entradas
        val cantidadJunior : EditText = findViewById(R.id.cantidadJunior)
        var texto = parseInt(cantidadJunior.text.toString())

        //boton con el que aumentamos el numero de entradas
        val aumentarBtnJunior : Button = findViewById(R.id.sumaJunior)
        aumentarBtnJunior.setOnClickListener {
            texto += 1
            cantidadJunior.setText(texto)
        }
        //boton con el que aumentamos el numero de entradas
        val disminuirBtnJunior : Button = findViewById(R.id.restaJunior)
        disminuirBtnJunior.setOnClickListener {
            texto += (-1)
            cantidadJunior.setText(texto)
        }

        //STUDENT
        val cantidadStudent : EditText = findViewById(R.id.cantidadStudent)
        var textoStudent = parseInt(cantidadStudent.text.toString())

        //boton con el que aumentamos el numero de entradas
        val aumentarBtnStudent : Button = findViewById(R.id.sumaStudent)
        aumentarBtnStudent.setOnClickListener {
            textoStudent += 1
            cantidadStudent.setText(textoStudent)
        }
        //boton con el que aumentamos el numero de entradas
        val disminuirBtnStudent : Button = findViewById(R.id.restaStudent)
        disminuirBtnStudent.setOnClickListener {
            textoStudent += (-1)
            cantidadStudent.setText(textoStudent)
        }

        //ADULT
        val cantidadAdult : EditText = findViewById(R.id.cantidadAdult)
        var textoAdult = parseInt(cantidadAdult.text.toString())

        //boton con el que aumentamos el numero de entradas
        val aumentarBtnAdult : Button = findViewById(R.id.sumaAdult)
        aumentarBtnAdult.setOnClickListener {
            textoAdult += 1
            cantidadAdult.setText(textoAdult)
        }
        //boton con el que aumentamos el numero de entradas
        val disminuirBtnAdult : Button = findViewById(R.id.restaAdult)
        disminuirBtnAdult.setOnClickListener {
            textoAdult += (-1)
            cantidadAdult.setText(textoAdult)
        }

        //SENIOR
        val cantidadSenior : EditText = findViewById(R.id.cantidadSenior)
        var textoSenior = parseInt(cantidadSenior.text.toString())

        //boton con el que aumentamos el numero de entradas
        val aumentarBtnSenior: Button = findViewById(R.id.sumaSenior)
        aumentarBtnSenior.setOnClickListener {
            textoSenior += 1
            cantidadSenior.setText(textoSenior)
        }
        //boton con el que aumentamos el numero de entradas
        val disminuirBtnSenior : Button = findViewById(R.id.restaSenior)
        disminuirBtnSenior.setOnClickListener {
            textoSenior += (-1)
            cantidadSenior.setText(textoSenior)
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





