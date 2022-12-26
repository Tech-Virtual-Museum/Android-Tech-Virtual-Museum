package com.example.techvirtualmuseum

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.techvirtualmuseum.adapter.AdapterComments
import com.example.techvirtualmuseum.modal.commentModal
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class displayComments : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    var comentariosLista: ListView? = null
    var commentModalArrayList: ArrayList<commentModal>? = null
    var database: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_comments)

        comentariosLista = findViewById<View>(R.id.listItems) as ListView?
        commentModalArrayList = ArrayList()

        database = FirebaseFirestore.getInstance()
        auth = Firebase.auth
        val usuarioActual = auth.currentUser

        //obtenemos el id del producto
        val qrText = intent.getStringExtra("QR_CODE_TEXT")

        //cargamos los comentarios con esta funcion
        loadComments(qrText)

        //boton para volver atras
        val backButton : ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener{
            val intent : Intent = Intent (this, productDetails::class.java)
            startActivity(intent)
        }

        //nos redirige a la actividad de crear los comentarios
        val floatingButton : FloatingActionButton = findViewById(R.id.floatingButton)
        floatingButton.setOnClickListener{
            val intent : Intent = Intent (this, crearComentario::class.java)
            intent.putExtra("QR_CODE_TEXT", qrText)
            startActivity(intent)
        }


        //boton de la navigationBar - eventos
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

    private fun loadComments(qrText: String?) {
        // llamamos al metodo que nos cargaran los comentarios en una lista
        database!!.collection("comments").document(qrText!!).collection("comments").get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val list = querySnapshot.documents
                    for (document in list) {
                        // Procesamos el documento
                        val comment = document.toObject(commentModal::class.java)
                        commentModalArrayList!!.add(comment!!)
                    }
                    val adapter = AdapterComments(this@displayComments, commentModalArrayList)
                    comentariosLista!!.adapter = adapter
                } else {
                    // si el snapshot esta vacio, mostramos un aviso
                    Toast.makeText(
                        this@displayComments,
                        "No se han encontrado datos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .addOnFailureListener { exception ->
                // Manejamos la excepción
                Toast.makeText(this@displayComments, "Error al cargar los datos", Toast.LENGTH_SHORT).show()
            }

    }
}