package com.example.techvirtualmuseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import com.example.techvirtualmuseum.adapter.AdapterProducts
import com.example.techvirtualmuseum.modal.ProductModal
import com.google.firebase.firestore.FirebaseFirestore

class Products : AppCompatActivity() {

    private lateinit var productosLista: ListView
    private lateinit var productModalArrayList: ArrayList<ProductModal>
    private lateinit var database: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        productosLista = findViewById(R.id.listItems)
        productModalArrayList = ArrayList()
        database = FirebaseFirestore.getInstance()

        loadDataInListView()

        findViewById<ImageButton>(R.id.calendarioBtn).setOnClickListener {
            startActivity(Intent(this, UpcomingEvents::class.java))
        }

        findViewById<ImageButton>(R.id.homeBtn).setOnClickListener {
            startActivity(Intent(this, HomePage::class.java))
        }

        findViewById<ImageButton>(R.id.scanBtn).setOnClickListener {
            startActivity(Intent(this, EscanerQR::class.java))
        }
    }

    private fun loadDataInListView() {
        database.collection("products").get()
            .addOnSuccessListener { querySnapshot ->
                val list = querySnapshot.documents
                for (document in list) {
                    val productModal = document.toObject(ProductModal::class.java)
                    productModalArrayList.add(productModal!!)
                }

                val adapter = AdapterProducts(this, productModalArrayList)
                productosLista.adapter = adapter

                if (list.isEmpty()) {
                    Toast.makeText(
                        this,
                        "No se han encontrado datos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al cargar los datos", Toast.LENGTH_SHORT).show()
            }
    }
}
