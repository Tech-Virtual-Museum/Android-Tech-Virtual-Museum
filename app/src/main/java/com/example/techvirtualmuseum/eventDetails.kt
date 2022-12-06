package com.example.techvirtualmuseum

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class eventDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)

        //inicializamos los campos para posteriormente añadir la informacion
        val nameEvent = findViewById<TextView>(R.id.nameEvent)
        val dateEvent = findViewById<TextView>(R.id.dateEvent)
        val hourEvent = findViewById<TextView>(R.id.hourEvent)
        val priceEvent = findViewById<TextView>(R.id.priceEvent)
        val descripcionEvent = findViewById<TextView>(R.id.descripcionEvent)

        //obtenemos la informacion del evento que ha sido clickado
        val nombreEvento = getIntent().getStringExtra("name");
        val fechaEvento = getIntent().getStringExtra("date");
        val horaEvento = getIntent().getStringExtra("hour");
        val precioEvento = getIntent().getStringExtra("pricing");
        val descripcionEvento = getIntent().getStringExtra("description");


        //mostramos la informacion
        nameEvent.setText(nombreEvento)
        dateEvent.setText(fechaEvento)
        hourEvent.setText(horaEvento)
        priceEvent.setText(precioEvento)
        descripcionEvent.setText(descripcionEvento)

        //listener para el boton de volver atras
        val backButton : ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent : Intent = Intent(this, upcomingEvents::class.java)
            startActivity(intent)
        }

        //listener para el boton de comprar ticket
        val bookNowBtn : Button = findViewById(R.id.bookNowBtn)
        bookNowBtn.setOnClickListener {
            val intent : Intent = Intent(this, buyEventTicket::class.java)
            intent.putExtra("name", nombreEvento)
            intent.putExtra("date", fechaEvento)
            intent.putExtra("hour", horaEvento)
            intent.putExtra("pricing", precioEvento)
            intent.putExtra("description", descripcionEvento)
            startActivity(intent)
        }
    }




}


