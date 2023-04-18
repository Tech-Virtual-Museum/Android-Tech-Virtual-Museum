package com.example.techvirtualmuseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import com.example.techvirtualmuseum.adapter.AdapterProducts
import com.example.techvirtualmuseum.modal.ProductModal
import com.google.firebase.firestore.FirebaseFirestore

class Products : AppCompatActivity() {

    var productosLista: ListView? = null
    var productModalArrayList: ArrayList<ProductModal>? = null
    var database: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        productosLista = findViewById<View>(R.id.listItems) as ListView?
        productModalArrayList = ArrayList()
        database = FirebaseFirestore.getInstance()

        // llamamos al metodo que nos cargaran los datos en la vista
        loadDatainListview()

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

    private fun loadDatainListview() {
        database!!.collection("products").get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    val list = queryDocumentSnapshots.documents
                    for (d in list) {
                        // despues de obtener la lista, la pasamos para nuestra clase objeto
                        val productModal: ProductModal? = d.toObject(ProductModal::class.java)

                        // despues de obtener los datos de firebase, la guardamos en un arrayList
                        productModalArrayList!!.add(productModal!!)

                    }
                    // pasamos el arrayList a la clase adapter que tenemos
                    val adapter =
                        AdapterProducts(
                            this@Products,
                            productModalArrayList
                        )
                    productosLista!!.adapter = adapter
                } else {
                    // si el snapshot esta vacio, mostramos un aviso
                    Toast.makeText(
                        this@Products,
                        "No se han encontrado datos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                //si tenemos un error, mostramos un mensaje
            }.addOnFailureListener {
                Toast.makeText(this@Products, "Error al cargar los datos", Toast.LENGTH_SHORT)
                    .show()
            }
    }
}