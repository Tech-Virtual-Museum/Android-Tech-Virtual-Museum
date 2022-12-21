package com.example.techvirtualmuseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class editInformation : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var database : FirebaseFirestore

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
            val newSurname = findViewById<EditText>(R.id.newSurname)
            val newEmail = findViewById<EditText>(R.id.newEmail)
            val newPassword = findViewById<EditText>(R.id.newPassword)

            //obtenemos los textos introducidos por el usuario
            val inputName = newName.text.toString()
            val inputSurname = newSurname.text.toString()
            val inputEmail = newEmail.text.toString()
            val inputPassword = newPassword.text.toString()

            //evaluamos el caso en que alguno de los campos este vacio
            if ( inputName.isEmpty() || inputSurname.isEmpty() || inputEmail.isEmpty() ||
                inputPassword.isEmpty()){
                Toast.makeText(baseContext, "Debe rellenar todos los campos",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //comprobamos que la contraseña introducida cumple con el patron estipulado
            if (!PASSWORD_PATTERN.matcher(inputPassword).matches()){
                Toast.makeText(baseContext, "La contraseña debe tener minimo 6 caracteres y un caracter especial",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //comprobamos que el email introducido cumple con el patron establecido
            if (!EMAIL_PATTERN.matcher(inputEmail).matches()){
                Toast.makeText(baseContext, "Debe introducir un correo valido",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //obtenemos el usuario actual
            val user = FirebaseAuth.getInstance().currentUser

            //actualizamos el email en firebase auth
            user?.updateEmail(inputEmail)

            //actualizamos la contraseña en firebase auth
            user?.updatePassword(inputPassword)

            //obtenemos el email del usuario actual, ya que en el firebase estan identificados por su correo y no por un uid aleatorio
            val idUser = auth.currentUser!!.email
            //actualizamos en firebase los datos de inicio de sesion del usuario
            database.collection("users").document(idUser!!).update(hashMapOf("name" to inputName, "surname" to inputSurname, "email" to inputEmail, "password" to inputPassword) as Map<String, Any>)

            startActivity(intent)

            //mostramos un mensaje si se han actualizado de forma correcta los datos
            Toast.makeText(this, "Se ha actualizado los datos de forma correcta", Toast.LENGTH_SHORT).show()

        }

        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent: Intent = Intent(this, profilePage::class.java)
            startActivity(intent)
        }
    }
}