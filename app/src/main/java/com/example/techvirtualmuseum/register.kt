package com.example.techvirtualmuseum

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class register : AppCompatActivity() {

    //creamos el patron que debe seguir la contraseña para ser considerada valida
    private val PASSWORD_PATTERN: Pattern = Pattern.compile("^" +
                "(?=.*[@#$%^&+=])" + // minimo un caracter especial
                "(?=\\S+$)" +  // sin espacios en blanco
                ".{6,}" +  // minimo 6 caracteres
                "$"
    )

    //creamos el patron que debe seguir el email
    private val EMAIL_PATTERN : Pattern = Pattern.compile(
        "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    )


    private lateinit var auth : FirebaseAuth
    private lateinit var database : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth
        database = FirebaseFirestore.getInstance()

        //al hace click en el boton de registro, se procede a ejecutar la funcion
        val signupButton: Button = findViewById(R.id.signupButton)
        signupButton.setOnClickListener {
            performSignUp()
        }
    }

    //Funcion con la que se crea en el Authentication el usuario
    private fun performSignUp() {
        val name = findViewById<EditText>(R.id.nameEditText_register)
        val surname = findViewById<EditText>(R.id.surnameEditText_register)
        val email = findViewById<EditText>(R.id.emailEditText_register)
        val password = findViewById<EditText>(R.id.passwordEditText_register)

        val inputName = name.text.toString()
        val inputSurname = surname.text.toString()
        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()

        //evaluamos el caso en que alguno de los campos este vacio
        if ( inputName.isEmpty() || inputSurname.isEmpty() || inputEmail.isEmpty() ||
            inputPassword.isEmpty()){
                Toast.makeText(baseContext, "Debe rellenar todos los campos",
                    Toast.LENGTH_SHORT).show()
                return
        }

        //comprobamos que la contraseña introducida cumple con el patron estipulado
        if (!PASSWORD_PATTERN.matcher(inputPassword).matches()){
            Toast.makeText(baseContext, "La contraseña debe tener minimo 6 caracteres y un caracter especial",
                Toast.LENGTH_SHORT).show()
            return
        }

        //comprobamos que el email introducido cumple con el patron establecido
        if (!EMAIL_PATTERN.matcher(inputEmail).matches()){
            Toast.makeText(baseContext, "Debe introducir un correo valido",
                Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                val dato = hashMapOf("name" to inputName,
                    "surname" to inputSurname, "email" to inputEmail, "password" to inputPassword)
                database.collection("users").document(inputEmail).set(dato)

                    Toast.makeText(baseContext, "Se ha registrado correctamente",
                        Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, homePage::class.java)
                    startActivity(intent)
                    finish()

                } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(baseContext, "Ha ocurrido un error, intentelo de nuevo",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}

