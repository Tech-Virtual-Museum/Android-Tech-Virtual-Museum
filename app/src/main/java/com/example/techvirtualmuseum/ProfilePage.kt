package com.example.techvirtualmuseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class ProfilePage : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseFirestore

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
        database.collection("users").document(idUser!!).get().addOnSuccessListener {
            userName.text = it.get("name") as String?
            userSurname.text = it.get("surname") as String?
            userEmail.text = it.get("email") as String?
        }

        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent: Intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

        //boton para editar la informacion del perfil
        val editProfile: Button = findViewById(R.id.editInformation)
        editProfile.setOnClickListener {
            val intent = Intent(this, EditInformation::class.java)
            startActivity(intent)
        }

        //listener para el boton que nos lleve a la pantalla de inicio
        val logOutBtn: ImageButton = findViewById(R.id.logOutBtn)
        logOutBtn.setOnClickListener {
            auth.signOut()
            Toast.makeText(
                baseContext, "Se ha cerrado la sesion correctamente",
                Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }


        //boton de la navigationBar - compra ticket 1
        val calendarioButton: ImageButton = findViewById(R.id.calendarioBtn)
        calendarioButton.setOnClickListener {
            val intent = Intent(this, BuyTicket::class.java)
            startActivity(intent)
        }

        //boton de la navigationBar - ir a la pagina inicio
        val homeButton: ImageButton = findViewById(R.id.homeBtn)
        homeButton.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

        //boton de la navigationBar - ir a la pagina de escanear QR
        val scanButton: ImageButton = findViewById(R.id.scanBtn)
        scanButton.setOnClickListener {
            val intent = Intent(this, EscanerQR::class.java)
            startActivity(intent)
        }
    }
}
