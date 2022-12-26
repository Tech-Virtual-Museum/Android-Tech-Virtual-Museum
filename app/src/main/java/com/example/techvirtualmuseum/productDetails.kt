package com.example.techvirtualmuseum

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class productDetails : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var database : FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        auth = Firebase.auth
        database = FirebaseFirestore.getInstance()

        //inicializamos los campos para posteriormente añadir la informacion
        val nameProduct = findViewById<TextView>(R.id.nameProduct)
        val descripcionProduct = findViewById<TextView>(R.id.descripcionProduct)
        val imageProduct = findViewById<ImageView>(R.id.imageProduct)

        //obtenemos la informacion del evento que ha sido clickado
        val nombreProducto = intent.getStringExtra("name");
        val descripcionProducto = intent.getStringExtra("descripcion")
        var imagenProducto = intent.getStringExtra("img")
        var videoProducto = intent.getStringExtra("video")
        var idVideoProducto = intent.getStringExtra("videoId")

        //mostramos la informacion
        nameProduct.text = nombreProducto
        descripcionProduct.text = descripcionProducto

        //obtenemos las imagenes guardadas en firebase
        Picasso.get().load(imagenProducto).into(imageProduct)

        val qrText = intent.getStringExtra("QR_CODE_TEXT")

        database.collection("products").document(qrText!!).get().addOnSuccessListener {
            //mostramos la informacion
            nameProduct.text = it.get("name") as String?
            descripcionProduct.text = it.get("descripcion") as String?
            imagenProducto = it.get("img") as String?
            videoProducto = it.get("video") as String?
            idVideoProducto = it.get("videoId") as String?

            //obtenemos las imagenes guardadas en firebase
            Picasso.get().load(imagenProducto).into(imageProduct)
        }

        //listener para el boton de volver atras
        val backButton : ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent : Intent = Intent(this, products::class.java)
            startActivity(intent)
        }

        //boton que nos lleva a la pantalla de ver video o escuchar un audio
        val playAudioVideo : ImageButton = findViewById(R.id.playAudioVideo)
        playAudioVideo.setOnClickListener {
            val intent : Intent = Intent(this, videoPlayer::class.java)
            intent.putExtra("video", videoProducto)
            intent.putExtra("videoId", idVideoProducto)
            startActivity(intent)
        }

        //nos lleva a una actividad donde podremos ver todos los comentarios
        val comentarioBtn : ImageButton = findViewById(R.id.comentarioBtn)
        comentarioBtn.setOnClickListener {
            val intent : Intent = Intent(this, displayComments::class.java)

            //le pasamos el id del producto para en la otra actividad mostrar solo sus comentarios
            intent.putExtra("QR_CODE_TEXT", qrText)
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