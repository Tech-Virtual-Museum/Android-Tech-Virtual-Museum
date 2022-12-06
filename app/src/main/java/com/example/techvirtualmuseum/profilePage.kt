package com.example.techvirtualmuseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class profilePage : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var database : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)

        auth = Firebase.auth
        database = FirebaseFirestore.getInstance()

        val userName = findViewById<TextView>(R.id.nameTextView)
        val userSurname = findViewById<TextView>(R.id.surnameTextView)
        val userEmail = findViewById<TextView>(R.id.emailTextView)

        //obtenemos el email del usuario actual, ya que en el firebase estan identificados por su correo y no por un uid aleatorio
        val idUser = auth.currentUser!!.email
        database.collection("Users").document(idUser!!).get().addOnSuccessListener {
            userName.setText(it.get("name") as String? )
            userSurname.setText(it.get("surname") as String? )
            userEmail.setText(it.get("email") as String? )
        }

        val backButton : ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent : Intent = Intent(this, homePage::class.java)
            startActivity(intent)
        }

        //listener para el boton que nos lleve a la pantalla de inicio
        val logOutBtn: ImageButton = findViewById(R.id.logOutBtn)
        logOutBtn.setOnClickListener {
            auth.signOut()
            Toast.makeText(baseContext, "Se ha cerrado la sesion correctamente",
                Toast.LENGTH_SHORT).show()
            val intent : Intent = Intent(this, login::class.java)
            startActivity(intent)
        }
    }
}