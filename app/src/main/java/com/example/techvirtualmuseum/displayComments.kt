package com.example.techvirtualmuseum

import android.content.Intent
import android.os.Bundle
import android.text.InputType
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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
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

        // llamamos al metodo que nos cargaran los datos en la vista
        loadDatainListview()

        //boton para volver atras
        val backButton : ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener{
            val intent : Intent = Intent (this, productDetails::class.java)
            startActivity(intent)
        }

        //obtenemos el usuario actual
        val idUser = auth.currentUser!!.email

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
                // Obtener el texto ingresado por el usuario y guardarlo en Firestore
                database!!.collection("comments").document(idUser!!).set("comentario" to input.text.toString())
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

    private fun loadDatainListview() {
        database!!.collection("comments").get()
            .addOnSuccessListener(OnSuccessListener<QuerySnapshot> { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    val list = queryDocumentSnapshots.documents
                    for (d in list) {
                        // despues de obtener la lista, la pasamos para nuestra clase objeto
                        val commentModal: commentModal? = d.toObject(commentModal::class.java)

                        // despues de obtener los datos de firebase, la guardamos en un arrayList
                        commentModalArrayList!!.add(commentModal!!)
                    }
                    // pasamos el arrayList a la clase adapter que tenemos
                    val adapter =
                        AdapterComments(
                            this@displayComments,
                            commentModalArrayList
                        )
                    comentariosLista!!.setAdapter(adapter)
                } else {
                    // si el snapshot esta vacio, mostramos un aviso
                    Toast.makeText(
                        this@displayComments,
                        "No existen comentarios",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                //si tenemos un error, mostramos un mensaje
            }).addOnFailureListener(OnFailureListener {
                Toast.makeText(this@displayComments, "Error al cargar los comentarios", Toast.LENGTH_SHORT).show()
            })
    }
}