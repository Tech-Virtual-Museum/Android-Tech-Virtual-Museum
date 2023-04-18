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

class EventDetails : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseFirestore

    private lateinit var nameEvent: TextView
    private lateinit var dateEvent: TextView
    private lateinit var hourEvent: TextView
    private lateinit var priceEvent: TextView
    private lateinit var descripcionEvent: TextView
    private lateinit var imageEvent: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)

        auth = Firebase.auth
        database = FirebaseFirestore.getInstance()

        nameEvent = findViewById(R.id.nameEvent)
        dateEvent = findViewById(R.id.dateEvent)
        hourEvent = findViewById(R.id.hourEvent)
        priceEvent = findViewById(R.id.priceEvent)
        descripcionEvent = findViewById(R.id.descripcionEvent)
        imageEvent = findViewById(R.id.imageEvent)

        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, UpcomingEvents::class.java)
            startActivity(intent)
        }

        val bookNowBtn: Button = findViewById(R.id.bookNowBtn)
        bookNowBtn.setOnClickListener {
            val intent = Intent(this, BuyEventTicket::class.java)
            intent.putExtra("name", nameEvent.text.toString())
            intent.putExtra("date", dateEvent.text.toString())
            intent.putExtra("hour", hourEvent.text.toString())
            intent.putExtra("pricing", priceEvent.text.toString())
            intent.putExtra("description", descripcionEvent.text.toString())
            startActivity(intent)
        }

        val calendarioButton: ImageButton = findViewById(R.id.calendarioBtn)
        calendarioButton.setOnClickListener {
            startActivity(Intent(this, UpcomingEvents::class.java))
        }

        val homeButton: ImageButton = findViewById(R.id.homeBtn)
        homeButton.setOnClickListener {
            startActivity(Intent(this, HomePage::class.java))
        }

        val scanButton: ImageButton = findViewById(R.id.scanBtn)
        scanButton.setOnClickListener {
            startActivity(Intent(this, EscanerQR::class.java))
        }

        intent.getStringExtra("name")?.let { nameEvent.text = it }
        intent.getStringExtra("date")?.let { dateEvent.text = it }
        intent.getStringExtra("hour")?.let { hourEvent.text = it }
        intent.getStringExtra("pricing")?.let { priceEvent.text = it }
        intent.getStringExtra("description")?.let { descripcionEvent.text = it }
        intent.getStringExtra("imgUrl")?.let {
            Picasso.get().load(it).into(imageEvent)
        }
    }
}
