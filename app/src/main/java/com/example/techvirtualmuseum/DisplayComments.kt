package com.example.techvirtualmuseum

import android.content.Intent
import android.os.Bundle
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

    private lateinit var auth: FirebaseAuth
    private var comentariosLista: ListView? = null
    private var commentModalArrayList = arrayListOf<CommentModal>()
    private var database: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_comments)

        comentariosLista = findViewById<ListView>(R.id.listItems)
        database = FirebaseFirestore.getInstance()
        auth = Firebase.auth

        val qrText = intent.getStringExtra("QR_CODE_TEXT")
        loadComments(qrText)

        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, ProductDetails::class.java)
            startActivity(intent)
        }

        val floatingButton: FloatingActionButton = findViewById(R.id.floatingButton)
        floatingButton.setOnClickListener {
            val intent = Intent(this, CrearComentario::class.java)
            intent.putExtra("QR_CODE_TEXT", qrText)
            startActivity(intent)
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

    private fun loadComments(qrText: String?) {
        database!!.collection("comments")
            .document(qrText!!)
            .collection("comments")
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.isEmpty) {
                    Toast.makeText(
                        this,
                        "No se han encontrado datos",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    for (document in querySnapshot.documents) {
                        val comment = document.toObject<CommentModal>(CommentModal::class.java)
                        commentModalArrayList.add(comment!!)
                    }
                    comentariosLista!!.adapter = AdapterComments(this, commentModalArrayList)
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al cargar los datos", Toast.LENGTH_SHORT).show()
            }
    }
}
