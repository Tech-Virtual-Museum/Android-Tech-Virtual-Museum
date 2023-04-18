package com.example.techvirtualmuseum

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.techvirtualmuseum.adapter.AdapterComments
import com.example.techvirtualmuseum.modal.CommentModal
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class DisplayComments : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    var comentariosLista: ListView? = null
    var commentModalArrayList: ArrayList<CommentModal>? = null
    var database: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_comments)

        comentariosLista = findViewById<View>(R.id.listItems) as ListView?
        commentModalArrayList = ArrayList()

        database = FirebaseFirestore.getInstance()
        auth = Firebase.auth

        //obtenemos el id del producto
        val qrText = intent.getStringExtra("QR_CODE_TEXT")

        //cargamos los comentarios con esta funcion
        loadComments(qrText)

        //boton para volver atras
        val backButton : ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener{
            val intent = Intent (this, ProductDetails::class.java)
            startActivity(intent)
        }

        //nos redirige a la actividad de crear los comentarios
        val floatingButton : FloatingActionButton = findViewById(R.id.floatingButton)
        floatingButton.setOnClickListener{
            val intent = Intent (this, CrearComentario::class.java)
            intent.putExtra("QR_CODE_TEXT", qrText)
            startActivity(intent)
        }


        //boton de la navigationBar - eventos
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

    private fun loadComments(qrText: String?) {
        // llamamos al metodo que nos cargaran los comentarios en una lista
        database!!.collection("comments").document(qrText!!).collection("comments").get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val list = querySnapshot.documents
                    for (document in list) {
                        // Procesamos el documento
                        val comment = document.toObject(CommentModal::class.java)
                        commentModalArrayList!!.add(comment!!)
                    }
                    val adapter = AdapterComments(this@DisplayComments, commentModalArrayList)
                    comentariosLista!!.adapter = adapter
                } else {
                    // si el snapshot esta vacio, mostramos un aviso
                    Toast.makeText(
                        this@DisplayComments,
                        "No se han encontrado datos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .addOnFailureListener {
                // Manejamos la excepción
                Toast.makeText(this@DisplayComments, "Error al cargar los datos", Toast.LENGTH_SHORT).show()
            }

    }
}