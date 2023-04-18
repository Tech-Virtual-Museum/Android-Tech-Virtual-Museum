package com.example.techvirtualmuseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ResetPassword : AppCompatActivity() {

    private lateinit var emailInput : EditText
    private lateinit var resetBtn : Button

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        emailInput = findViewById(R.id.emailInput)
        resetBtn = findViewById(R.id.resetBtn)

        auth = FirebaseAuth.getInstance()

        resetBtn.setOnClickListener {
            val recoveryPassword = emailInput.text.toString()
            auth.sendPasswordResetEmail(recoveryPassword)
                .addOnSuccessListener {
                    Toast.makeText(this, "Por favor, revise su correo", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                }
        }

        //listener que nos mandara a la pantalla de inicio para salir de esta actividad
        val backHome : TextView = findViewById(R.id.backHome)
        backHome.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
    }
}