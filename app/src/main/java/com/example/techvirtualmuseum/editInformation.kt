package com.example.techvirtualmuseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

        val name = findViewById<EditText>(R.id.nameEditText_register)
        val surname = findViewById<EditText>(R.id.surnameEditText_register)
        val email = findViewById<EditText>(R.id.emailEditText_register)

        val inputName = name.text.toString()
        val inputSurname = surname.text.toString()
        val inputEmail = email.text.toString()

        val idUser = auth.currentUser!!.email
        val dato = hashMapOf("name" to inputName,"surname" to inputSurname, "email" to inputEmail)
        if (idUser != null) {
            database.collection("prueba").document(idUser).set(dato)
        }

        val cambio : Button = findViewById(R.id.cambio)
        cambio.setOnClickListener {
            val intent : Intent = Intent(this, profilePage::class.java)
            startActivity(intent)
        }


    }
}