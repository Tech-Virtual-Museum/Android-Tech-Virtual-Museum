package com.example.techvirtualmuseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class products : AppCompatActivity() {

    var productosLista: ListView? = null
    var productModalArrayList: ArrayList<productModal>? = null
    var database: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        productosLista = findViewById<View>(R.id.listItems) as ListView?
        productModalArrayList = ArrayList()
        database = FirebaseFirestore.getInstance()

        // llamamos al metodo que nos cargaran los datos en la vista
        loadDatainListview()
    }

    private fun loadDatainListview() {
        database!!.collection("products").get()
            .addOnSuccessListener(OnSuccessListener<QuerySnapshot> { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    val list = queryDocumentSnapshots.documents
                    for (d in list) {
                        // despues de obtener la lista, la pasamos para nuestra clase objeto
                        val productModal: productModal? = d.toObject(productModal::class.java)

                        // despues de obtener los datos de firebase, la guardamos en un arrayList
                        productModalArrayList!!.add(productModal!!)
                    }
                    // pasamos el arrayList a la clase adapter que tenemos
                    val adapter = AdapterProducts(this@products, productModalArrayList)
                    productosLista!!.setAdapter(adapter)
                } else {
                    // si el snapshot esta vacio, mostramos un aviso
                    Toast.makeText(
                        this@products,
                        "No data found in Database",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                //si tenemos un error, mostramos un mensaje
            }).addOnFailureListener(OnFailureListener {
                Toast.makeText(this@products, "Fail to load data..", Toast.LENGTH_SHORT).show()
            })

    }
}