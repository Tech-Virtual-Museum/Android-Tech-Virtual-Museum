package com.example.techvirtualmuseum

import android.content.Intent
import android.os.Bundle
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

        val idUser = auth.currentUser!!.email


        //cuando hacemos click
        val floatingButton : FloatingActionButton = findViewById(R.id.floatingButton)
        floatingButton.setOnClickListener{
            val inflater = layoutInflater
            val view = inflater.inflate(R.layout.popup_layout, null)
            val builder = AlertDialog.Builder(this@displayComments)
            val nameComment = view.findViewById<EditText>(R.id.nameComment)
            val commentComment = view.findViewById<EditText>(R.id.commentComment)
            val inputName = nameComment.text.toString()
            val inputComment = commentComment.text.toString()
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()

            with(builder){
                val saveComments : Button? = dialog.findViewById(R.id.saveComment)
                saveComments?.setOnClickListener {
                    val dato = hashMapOf("name" to inputName, "comment" to inputComment)
                    database!!.collection("pruebaComentarios").document(idUser!!).set(dato)
                    val myIntent = Intent(this@displayComments, displayComments::class.java)
                    startActivity(myIntent)
                    dialog.cancel() //Cierra dialogo.
                }
            }
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
        database!!.collection("pruebaComentarios").get()
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
                        "No data found in Database",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                //si tenemos un error, mostramos un mensaje
            }).addOnFailureListener(OnFailureListener {
                Toast.makeText(this@displayComments, "Fail to load data..", Toast.LENGTH_SHORT).show()
            })
    }
}