package com.example.techvirtualmuseum

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso


class EventDetails : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var database : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)

        //inicializamos firebase authentication y obtenemos una instancia de la base de datos
        auth = Firebase.auth
        database = FirebaseFirestore.getInstance()


        //inicializamos los campos para posteriormente añadir la informacion
        val nameEvent = findViewById<TextView>(R.id.nameEvent)
        val dateEvent = findViewById<TextView>(R.id.dateEvent)
        val hourEvent = findViewById<TextView>(R.id.hourEvent)
        val priceEvent = findViewById<TextView>(R.id.priceEvent)
        val descripcionEvent = findViewById<TextView>(R.id.descripcionEvent)
        val imageEvent = findViewById<ImageView>(R.id.imageEvent)

        //obtenemos la informacion del evento que ha sido clickado
        val nombreEvento = intent.getStringExtra("name");
        val fechaEvento = intent.getStringExtra("date");
        val horaEvento = intent.getStringExtra("hour");
        val precioEvento = intent.getStringExtra("pricing");
        val descripcionEvento = intent.getStringExtra("description");
        val imagenEvento = intent.getStringExtra("imgUrl")

        //mostramos la informacion
        nameEvent.text = nombreEvento
        dateEvent.text = fechaEvento
        hourEvent.text = horaEvento
        priceEvent.text = precioEvento
        descripcionEvent.text = descripcionEvento

        //obtenemos las imagenes guardadas en firebase
        Picasso.get().load(imagenEvento).into(imageEvent)

        //listener para el boton de volver atras
        val backButton : ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, UpcomingEvents::class.java)
            startActivity(intent)
        }

        //listener para el boton de comprar ticket
        val bookNowBtn : Button = findViewById(R.id.bookNowBtn)
        bookNowBtn.setOnClickListener {
            val intent = Intent(this, BuyEventTicket::class.java)
            Log.d("error", "$intent")
            intent.putExtra("name", nombreEvento)
            intent.putExtra("date", fechaEvento)
            intent.putExtra("hour", horaEvento)
            intent.putExtra("pricing", precioEvento)
            intent.putExtra("description", descripcionEvento)
            startActivity(intent)
        }


        //boton de la navigationBar - compra ticket 1
        val calendarioButton : ImageButton = findViewById(R.id.calendarioBtn)
        calendarioButton.setOnClickListener {
            val intent = Intent(this, UpcomingEvents::class.java)
            startActivity(intent)
        }


        //boton de la navigationBar - ir a la pagina inicio
        val homeButton : ImageButton = findViewById(R.id.homeBtn)
        homeButton.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

        //boton de la navigationBar - ir a la pagina de escanear QR
        val scanButton : ImageButton = findViewById(R.id.scanBtn)
        scanButton.setOnClickListener {
            val intent = Intent(this, EscanerQR::class.java)
            startActivity(intent)
        }
    }




}


