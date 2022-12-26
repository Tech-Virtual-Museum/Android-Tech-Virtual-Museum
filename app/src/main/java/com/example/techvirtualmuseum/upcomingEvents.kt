package com.example.techvirtualmuseum

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.techvirtualmuseum.adapter.AdapterEventos
import com.example.techvirtualmuseum.modal.dataModal
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import androidx.appcompat.widget.SearchView


class upcomingEvents : AppCompatActivity() {

    var eventosLista: ListView? = null
    var dataModalArrayList: ArrayList<dataModal>? = null
    var database: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upcoming_events)

        eventosLista = findViewById<View>(R.id.listItems) as ListView?
        dataModalArrayList = ArrayList()
        database = FirebaseFirestore.getInstance()

        // llamamos al metodo que nos cargaran los datos en la vista
        loadDatainListview()

        //filtrar para buscar
        val searchView = findViewById<SearchView>(R.id.search_view)
        val names = arrayOf("Robotics Expo Winter 2022", "Augmented Reality Exhibition", "Smartphone Evolution Expo", "3D Printing Beginner Class", "Augmented Reality Games", "Evolution Of Games Expo")
        val namesAdapter : ArrayAdapter <String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, names)

        val collection = database!!.collection("eventos")

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // Cuando el usuario envía la búsqueda, realizamos una consulta de Firestore para buscar documentos que contengan la palabra especificada
                collection
                    .whereArrayContains("names", query)
                    .get()
                    .addOnSuccessListener { documents ->
                        // Aquí procesamos los documentos que se han obtenido de la consulta
                        for (document in documents) {
                            val dato = document.get("dato") as String
                            // Mostramos el dato en pantalla o en cualquier otro lugar que deseemos
                            Log.d("dato", dato)
                        }
                    }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                TODO("Not yet implemented")
            }
        })


        //boton para volver atras
        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent: Intent = Intent(this, homePage::class.java)
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

    private fun loadDatainListview() {
        database!!.collection("eventos").get()
            .addOnSuccessListener(OnSuccessListener<QuerySnapshot> { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    val list = queryDocumentSnapshots.documents
                    for (d in list) {
                        // despues de obtener la lista, la pasamos para nuestra clase objeto
                        val dataModal: dataModal? = d.toObject(dataModal::class.java)

                        // despues de obtener los datos de firebase, la guardamos en un arrayList
                        dataModalArrayList!!.add(dataModal!!)
                    }
                    // pasamos el arrayList a la clase adapter que tenemos
                    val adapter =
                        AdapterEventos(
                            this@upcomingEvents,
                            dataModalArrayList
                        )
                    eventosLista!!.setAdapter(adapter)
                } else {
                    // si el snapshot esta vacio, mostramos un aviso
                    Toast.makeText(
                        this@upcomingEvents,
                        "No data found in Database",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                //si tenemos un error, mostramos un mensaje
            }).addOnFailureListener(OnFailureListener {
                Toast.makeText(this@upcomingEvents, "Fail to load data..", Toast.LENGTH_SHORT).show()
            })
    }


}
