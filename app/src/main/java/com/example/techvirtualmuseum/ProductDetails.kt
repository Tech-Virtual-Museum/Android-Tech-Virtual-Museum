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

class ProductDetails : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        auth = Firebase.auth
        database = FirebaseFirestore.getInstance()

        val nameProduct: TextView = findViewById(R.id.nameProduct)
        val descripcionProduct: TextView = findViewById(R.id.descripcionProduct)
        val imageProduct: ImageView = findViewById(R.id.imageProduct)

        val qrText = intent.getStringExtra("QR_CODE_TEXT")
        val docRef = database.collection("products").document(qrText!!)

        docRef.get().addOnSuccessListener { documentSnapshot ->
            val data = documentSnapshot.data
            if (data != null) {
                val nombreProducto = data["name"] as String?
                val descripcionProducto = data["descripcion"] as String?
                val imagenProducto = data["img"] as String?
                val videoProducto = data["video"] as String?
                val idVideoProducto = data["videoId"] as String?

                nameProduct.text = nombreProducto
                descripcionProduct.text = descripcionProducto
                Picasso.get().load(imagenProducto).into(imageProduct)

                val playAudioVideo: ImageButton = findViewById(R.id.playAudioVideo)
                playAudioVideo.setOnClickListener {
                    val intent = Intent(this, VideoPlayer::class.java).apply {
                        putExtra("video", videoProducto)
                        putExtra("videoId", idVideoProducto)
                    }
                    startActivity(intent)
                }

                val comentarioBtn: ImageButton = findViewById(R.id.comentarioBtn)
                comentarioBtn.setOnClickListener {
                    val intent = Intent(this, DisplayComments::class.java).apply {
                        putExtra("QR_CODE_TEXT", qrText)
                    }
                    startActivity(intent)
                }
            }
        }

        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, Products::class.java)
            startActivity(intent)
        }

        val calendarioButton: ImageButton = findViewById(R.id.calendarioBtn)
        calendarioButton.setOnClickListener {
            val intent = Intent(this, UpcomingEvents::class.java)
            startActivity(intent)
        }

        val homeButton: ImageButton = findViewById(R.id.homeBtn)
        homeButton.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

        val scanButton: ImageButton = findViewById(R.id.scanBtn)
        scanButton.setOnClickListener {
            val intent = Intent(this, EscanerQR::class.java)
            startActivity(intent)
        }
    }
}
