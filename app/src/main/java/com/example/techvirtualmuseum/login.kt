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
import com.google.firebase.ktx.Firebase

class login : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        //listener para cuando se hace click en el boton de registrarse
        val signUpButton: Button = findViewById(R.id.signUpButton)
        signUpButton.setOnClickListener {
            val intent = Intent(this, register::class.java)
            startActivity(intent)
        }

        //listener para el boton que nos lleve a la pantalla de inicio
        val loginBtn: Button = findViewById(R.id.loginBtn)
        loginBtn.setOnClickListener {
            performLogin()
        }

        //listener que nos redirija si olvidamos la contraseña
        val forgotPassword : TextView = findViewById(R.id.forgotPassword)
        forgotPassword.setOnClickListener {
            val intent = Intent(this, resetPassword::class.java)
            startActivity(intent)
        }
    }



    private fun performLogin() {
        val email = findViewById<EditText>(R.id.emailEditText_login)
        val password = findViewById<EditText>(R.id.passwordEditText_login)

        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()

        //evaluamos el caso en que alguno de los campos este vacio
        if (inputEmail.isEmpty() || inputPassword.isEmpty()) {
            Toast.makeText(
                baseContext, "Debe rellenar todos los campos",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        auth.signInWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent: Intent = Intent(this, homePage::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        baseContext, "Error en el inicio de sesion",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}