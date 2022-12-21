package com.example.techvirtualmuseum

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.techvirtualmuseum.adapter.AdapterComments
import com.example.techvirtualmuseum.modal.commentModal
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
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

        //obtenemos el id del producto
        val qrText = intent.getStringExtra("QR_CODE_TEXT")

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


        //boton para volver atras
        val backButton : ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener{
            val intent : Intent = Intent (this, productDetails::class.java)
            startActivity(intent)
        }

        //cuando hacemos click
        val floatingButton : FloatingActionButton = findViewById(R.id.floatingButton)
        floatingButton.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Escribe tu comentario a continuación:")

            val input = EditText(this)
            input.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(input)

            // Agregar botones "OK" y "Cancelar" al diálogo
            builder.setPositiveButton("OK") { dialog, which ->
                // Obtener el texto ingresado por el usuario y lo guardamos en la coleccion pertinente al producto en el que nos encontramos
                database!!.collection("comments").document(qrText).collection("comments").document().set("comment" to input.text.toString())
                Toast.makeText(this, "Comentario añadido correctamente", Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton("Cancelar") { dialog, which ->
                // Cerrar el diálogo sin hacer nada
            }

            // Mostrar el diálogo
            val dialog = builder.create()
            dialog.show()
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
}