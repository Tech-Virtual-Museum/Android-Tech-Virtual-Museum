package com.example.techvirtualmuseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton


class buyTicket2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_ticket2)

        //PRECIOS ENTRADAS
        val ticketJR : Double = 2.50
        val ticketST : Double = 4.50
        val ticketAD : Double = 7.00
        val ticketSN : Double = 4.50

        //Variables JUNIOR
        val cantidadJR : EditText = findViewById(R.id.cantidadJunior)
        val n1 : String = cantidadJR.text.toString()
        var valorJR : Int = Integer.parseInt(n1)

        //Inicialiazacion de los campos para mostrar el precio
        val numTicketJr : EditText = findViewById(R.id.numTicketsJR)
        val numTicketSt : EditText = findViewById(R.id.numTicketsST)
        val numTicketAd : EditText = findViewById(R.id.numTicketsAD)
        val numTicketSn : EditText = findViewById(R.id.numTicketsSN)

        //boton con el que aumentamos el numero de entradas
        val aumentarBtnJunior : Button = findViewById(R.id.sumaJunior)
        aumentarBtnJunior.setOnClickListener {
            valorJR += 1
            cantidadJR.setText("$valorJR")
            //aumentacmos precio en el Summary
            if (valorJR >= 1) {
                val precioJR = valorJR * ticketJR
                numTicketJr.setText("$precioJR")
            }
        }

        //boton con el que aumentamos el numero de entradas
        val disminuirBtnJunior : Button = findViewById(R.id.restaJunior)
        disminuirBtnJunior.setOnClickListener {
            if ( valorJR >= 1 ){
                valorJR += (-1)
                cantidadJR.setText("$valorJR")
                val precioJR = valorJR * ticketJR
                numTicketJr.setText("$precioJR")
            }
        }


        //Variables STUDENT
        val cantidadST : EditText = findViewById(R.id.cantidadStudent)
        val n2 : String = cantidadST.text.toString()
        var valorST : Int = Integer.parseInt(n2)

        //boton con el que aumentamos el numero de entradas
        val aumentarBtnStudent : Button = findViewById(R.id.sumaStudent)
        aumentarBtnStudent.setOnClickListener {
            valorST += 1
            cantidadST.setText("$valorST")

            //aumentacmos precio en el Summary
            if (valorST >= 1) {
                val precioST = valorST * ticketST
                numTicketSt.setText("$precioST")
            }
        }

        //boton con el que aumentamos el numero de entradas
        val disminuirBtnStudent : Button = findViewById(R.id.restaStudent)
        disminuirBtnStudent.setOnClickListener {
            if ( valorST > 0 ){
                valorST += (-1)
                cantidadST.setText("$valorST")
                val precioST = valorST * ticketST
                numTicketSt.setText("$precioST")
            }
        }


        //Variables ADULT
        val cantidadAD : EditText = findViewById(R.id.cantidadAdult)
        val n3 : String = cantidadAD.text.toString()
        var valorAD : Int = Integer.parseInt(n3)

        val aumentarBtnAdult : Button = findViewById(R.id.sumaAdult)
        aumentarBtnAdult.setOnClickListener {
            valorAD += 1
            cantidadAD.setText("$valorAD")

            //aumentacmos precio en el Summary
            if (valorAD >= 1) {
                val precioAD = valorAD * ticketAD
                numTicketAd.setText("$precioAD")
            }
        }

        val disminuirBtnAdult : Button = findViewById(R.id.restaAdult)
        disminuirBtnAdult.setOnClickListener {
            if (valorAD > 0){
                valorAD += (-1)
                cantidadAD.setText("$valorAD")

                val precioAD = valorAD * ticketAD
                numTicketAd.setText("$precioAD")
            }
        }


        //Variables SENIOR
        val cantidadSN : EditText = findViewById(R.id.cantidadSenior)
        val n4 : String = cantidadSN.text.toString()
        var valorSN : Int = Integer.parseInt(n4)

        val aumentarBtnSenior : Button = findViewById(R.id.sumaSenior)
        aumentarBtnSenior.setOnClickListener {
            valorSN += 1
            cantidadSN.setText("$valorSN")

            //aumentacmos precio en el Summary
            if (valorSN >= 1) {
                val precioSN = valorSN * ticketSN
                numTicketSn.setText("$precioSN")
            }
        }

        val disminuirBtnSenior : Button = findViewById(R.id.restaSenior)
        disminuirBtnSenior.setOnClickListener {
            if (valorSN > 0){
                valorSN += (-1)
                cantidadSN.setText("$valorSN")

                val precioSN = valorSN * ticketSN
                numTicketSn.setText("$precioSN")
            }
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





