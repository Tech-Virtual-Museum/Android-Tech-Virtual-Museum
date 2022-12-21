package com.example.techvirtualmuseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
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

        val cambio : Button = findViewById(R.id.cambio)
        cambio.setOnClickListener {
            val intent : Intent = Intent(this, profilePage::class.java)
            //inicializamos los campos
            val newName = findViewById<EditText>(R.id.newName)
            Log.d("nombre", "$newName")
            val newSurname = findViewById<EditText>(R.id.newSurname)
            val newEmail = findViewById<EditText>(R.id.newEmail)
            val newPassword = findViewById<EditText>(R.id.newPassword)

            //obtenemos los textos introducidos por el usuario
            val inputName = newName.text.toString()
            val inputSurname = newSurname.text.toString()
            val inputEmail = newEmail.text.toString()
            val inputPassword = newPassword.text.toString()

            //obtenemos el email del usuario actual, ya que en el firebase estan identificados por su correo y no por un uid aleatorio
            val idUser = auth.currentUser!!.email
            database.collection("nuevaInfo").document(idUser!!).set(hashMapOf("name" to inputName, "surname" to inputSurname, "email" to inputEmail, "password" to inputPassword))
            startActivity(intent)
        }

        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent: Intent = Intent(this, profilePage::class.java)
            startActivity(intent)
        }
    }
}