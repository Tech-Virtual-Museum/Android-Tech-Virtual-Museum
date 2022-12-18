package com.example.techvirtualmuseum

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class editInformation : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var database : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_information)

        auth = Firebase.auth
        database = FirebaseFirestore.getInstance()

        //editText
        val newName = findViewById<EditText>(R.id.newName)
        val newSurname = findViewById<EditText>(R.id.newSurname)

        //obtenemos los textos introducidos por el usuario
        val inputName = newName.text.toString()
        val inputSurname = newSurname.text.toString()

        //obtenemos el email del usuario actual, ya que en el firebase estan identificados por su correo y no por un uid aleatorio
        val idUser = auth.currentUser!!.email

        val cambio : Button = findViewById(R.id.cambio)
        cambio.setOnClickListener {
            val intent : Intent = Intent(this, profilePage::class.java)

            startActivity(intent)
        }


    }
}