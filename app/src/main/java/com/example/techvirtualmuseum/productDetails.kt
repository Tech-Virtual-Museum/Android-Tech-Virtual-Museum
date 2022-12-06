package com.example.techvirtualmuseum

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class productDetails : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        //inicializamos los campos para posteriormente añadir la informacion
        val nameProduct = findViewById<TextView>(R.id.nameProduct)
        val descripcionProduct = findViewById<TextView>(R.id.descripcionProduct)

        //obtenemos la informacion del evento que ha sido clickado
        val nombreProducto = getIntent().getStringExtra("name");
        val descripcionProducto = getIntent().getStringExtra("descripcion");


        //mostramos la informacion
        nameProduct.setText(nombreProducto)
        descripcionProduct.setText(descripcionProducto)

        //listener para el boton de volver atras
        val backButton : ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent : Intent = Intent(this, homePage::class.java)
            startActivity(intent)
        }

    }
}