package com.example.techvirtualmuseum

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso


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

        val checkboxFav : CheckBox = findViewById(R.id.checkboxFav)
        checkboxFav.setOnCheckedChangeListener{ checkbox, isChecked ->
            if (isChecked){
                Toast.makeText(this, "Añadido a favoritos", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Eliminado de favoritos", Toast.LENGTH_SHORT).show()
            }
        }

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
            val intent : Intent = Intent(this, upcomingEvents::class.java)
            startActivity(intent)
        }


        //boton de la navigationBar - ir a la pagina inicio
        val homeButton : ImageButton = findViewById(R.id.homeBtn)
        homeButton.setOnClickListener {
            val intent : Intent = Intent(this, homePage::class.java)
            startActivity(intent)
        }

        //boton de la navigationBar - ir a la pagina de escanear QR
        val scanButton : ImageButton = findViewById(R.id.scanBtn)
        scanButton.setOnClickListener {
            val intent : Intent = Intent(this, escanerQR::class.java)
            startActivity(intent)
        }
    }




}


