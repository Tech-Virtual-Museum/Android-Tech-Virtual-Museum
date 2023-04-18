package com.example.techvirtualmuseum

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView


class BuyTicket2 : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_ticket2)

        //PRECIOS ENTRADAS
        val ticketJR : Double = 2.50
        val ticketST : Double = 4.50
        val ticketAD : Double = 7.00
        val ticketSN : Double = 4.50

        //Inicialiazacion de los campos para mostrar el precio
        val numTicketJr : EditText = findViewById(R.id.numTicketsJR)
        val numTicketSt : EditText = findViewById(R.id.numTicketsST)
        val numTicketAd : EditText = findViewById(R.id.numTicketsAD)
        val numTicketSn : EditText = findViewById(R.id.numTicketsSN)

        val precioTotalEntradas : TextView = findViewById(R.id.precioTotalEntradas)

        //VARIABLES DE LOS PRECIOS
        var precioJR : Double = 0.0
        var precioST : Double = 0.0
        var precioAD : Double = 0.0
        var precioSN : Double = 0.0

        //Variables JUNIOR
        val cantidadJR : EditText = findViewById(R.id.cantidadJunior)
        val n1 : String = cantidadJR.text.toString()
        var valorJR : Int = Integer.parseInt(n1)

        //boton con el que aumentamos el numero de entradas
        val aumentarBtnJunior : Button = findViewById(R.id.sumaJunior)
        aumentarBtnJunior.setOnClickListener {
            valorJR += 1
            cantidadJR.setText("$valorJR")

            //aumentacmos precio en el Summary
            if (valorJR >= 1) {
                //precio por las entradas junior
                precioJR = valorJR * ticketJR
                numTicketJr.setText("$precioJR")
            }

            // actualizamos el precio total
            val precioTotal = precioJR + precioST + precioAD + precioSN
            precioTotalEntradas.setText("$precioTotal")

        }

        //boton con el que disminuimos el numero de entradas
        val disminuirBtnJunior : Button = findViewById(R.id.restaJunior)
        disminuirBtnJunior.setOnClickListener {
            if ( valorJR >= 1 ){
                valorJR += (-1)
                cantidadJR.setText("$valorJR")

                precioJR = valorJR * ticketJR
                numTicketJr.setText("$precioJR")
            }

            // actualizamos el precio total
            val precioTotal = precioJR + precioST + precioAD + precioSN
            precioTotalEntradas.setText("$precioTotal")
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
                precioST = valorST * ticketST
                numTicketSt.setText("$precioST")

            }

            // actualizamos el precio total
            val precioTotal = precioJR + precioST + precioAD + precioSN
            precioTotalEntradas.setText("$precioTotal")
        }

        //boton con el que aumentamos el numero de entradas
        val disminuirBtnStudent : Button = findViewById(R.id.restaStudent)
        disminuirBtnStudent.setOnClickListener {
            if ( valorST > 0 ){
                valorST += (-1)
                cantidadST.setText("$valorST")

                precioST = valorST * ticketST
                numTicketSt.setText("$precioST")
            }

            // actualizamos el precio total
            val precioTotal = precioJR + precioST + precioAD + precioSN
            precioTotalEntradas.setText("$precioTotal")
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
                precioAD = valorAD * ticketAD
                numTicketAd.setText("$precioAD")

            }

            // actualizamos el precio total
            val precioTotal = precioJR + precioST + precioAD + precioSN
            precioTotalEntradas.setText("$precioTotal")
        }

        val disminuirBtnAdult : Button = findViewById(R.id.restaAdult)
        disminuirBtnAdult.setOnClickListener {
            if (valorAD > 0){
                valorAD += (-1)
                cantidadAD.setText("$valorAD")

                precioAD = valorAD * ticketAD
                numTicketAd.setText("$precioAD")

            }

            // actualizamos el precio total
            val precioTotal = precioJR + precioST + precioAD + precioSN
            precioTotalEntradas.setText("$precioTotal")
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
                precioSN = valorSN * ticketSN
                numTicketSn.setText("$precioSN")

            }

            // actualizamos el precio total
            val precioTotal = precioJR + precioST + precioAD + precioSN
            precioTotalEntradas.setText("$precioTotal")
        }

        val disminuirBtnSenior : Button = findViewById(R.id.restaSenior)
        disminuirBtnSenior.setOnClickListener {
            if (valorSN > 0){
                valorSN += (-1)
                cantidadSN.setText("$valorSN")

                precioSN = valorSN * ticketSN
                numTicketSn.setText("$precioSN")
            }

            // actualizamos el precio total
            val precioTotal = precioJR + precioST + precioAD + precioSN
            precioTotalEntradas.setText("$precioTotal")
        }



        //boton que nos llevara a la actvidad anterior
        val backButton : ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent : Intent = Intent(this, BuyTicket::class.java)
            startActivity(intent)
        }

        //listener para el boton que nos dirigira a la tercera actividad de compra de tickets
        val buyTicket3 : Button = findViewById(R.id.buyFinalTicket)
        buyTicket3.setOnClickListener {
            val intent: Intent = Intent(this, com.example.techvirtualmuseum.BuyTicket3::class.java)
            startActivity(intent)
        }

        //boton de la navigationBar - compra ticket 1
        val calendarioButton : ImageButton = findViewById(R.id.calendarioBtn)
        calendarioButton.setOnClickListener {
            val intent : Intent = Intent(this, UpcomingEvents::class.java)
            startActivity(intent)
        }


        //boton de la navigationBar - ir a la pagina inicio
        val homeButton : ImageButton = findViewById(R.id.homeBtn)
        homeButton.setOnClickListener {
            val intent : Intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

        //boton de la navigationBar - ir a la pagina de escanear QR
        val scanButton : ImageButton = findViewById(R.id.scanBtn)
        scanButton.setOnClickListener {
            val intent: Intent = Intent(this, EscanerQR::class.java)
            startActivity(intent)
        }
    }

}





