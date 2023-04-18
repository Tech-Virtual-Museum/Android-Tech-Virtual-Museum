package com.example.techvirtualmuseum

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.techvirtualmuseum.adapter.AdapterEventos
import com.example.techvirtualmuseum.modal.DataModal
import com.google.firebase.firestore.FirebaseFirestore

class UpcomingEvents : AppCompatActivity() {

    private lateinit var eventosLista: ListView
    private var dataModalArrayList = ArrayList<DataModal>()
    private lateinit var database: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upcoming_events)

        eventosLista = findViewById(R.id.listItems)
        database = FirebaseFirestore.getInstance()

        loadDatainListview()

        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            startActivity(Intent(this, HomePage::class.java))
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
    }

    private fun loadDatainListview() {
        database.collection("eventos").get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    val list = queryDocumentSnapshots.toObjects(DataModal::class.java)
                    dataModalArrayList.addAll(list)
                    val adapter = AdapterEventos(this@UpcomingEvents, dataModalArrayList)
                    eventosLista.adapter = adapter
                } else {
                    Toast.makeText(this@UpcomingEvents, "No data found in Database", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                Toast.makeText(this@UpcomingEvents, "Fail to load data..", Toast.LENGTH_SHORT).show()
            }
    }
}
