package com.example.techvirtualmuseum

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class productDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        //inicializamos los campos para posteriormente añadir la informacion
        val nameProduct = findViewById<TextView>(R.id.nameProduct)
        val descripcionProduct = findViewById<TextView>(R.id.descripcionProduct)
        val imageProduct = findViewById<ImageView>(R.id.imageProduct)

        //obtenemos la informacion del evento que ha sido clickado
        val nombreProducto = intent.getStringExtra("name");
        val descripcionProducto = intent.getStringExtra("descripcion")
        val imagenProducto = intent.getStringExtra("img")

        //mostramos la informacion
        nameProduct.text = nombreProducto
        descripcionProduct.text = descripcionProducto


        //obtenemos las imagenes guardadas en firebase
        Picasso.get().load(imagenProducto).into(imageProduct)


        //listener para el boton de volver atras
        val backButton : ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent : Intent = Intent(this, products::class.java)
            startActivity(intent)
        }

        //boton que nos lleva a la pantalla de ver video o escuchar un audio
        val playAudioVideo : ImageButton = findViewById(R.id.playAudioVideo)
        playAudioVideo.setOnClickListener {
            val intent : Intent = Intent(this, homePage::class.java)
            startActivity(intent)
        }

        //boton de la navigationBar - compra ticket 1
        val calendarioButton : ImageButton = findViewById(R.id.calendarioBtn)
        calendarioButton.setOnClickListener {
            val intent : Intent = Intent(this, buyTicket::class.java)
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