package com.example.techvirtualmuseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class BuyTicket3 : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_ticket3)

        auth = Firebase.auth
        database = FirebaseFirestore.getInstance()

        val userName = findViewById<TextView>(R.id.nameTextView)
        val userSurname = findViewById<TextView>(R.id.surnameTextView)
        val userEmail = findViewById<TextView>(R.id.emailTextView)

        //obtenemos el email del usuario actual, ya que en el firebase estan identificados por su correo y no por un uid aleatorio
        val idUser = auth.currentUser!!.email

        //mostramos la informacion del usuario
        database.collection("users").document(idUser!!).get().addOnSuccessListener {
            userName.text = it.get("name") as String?
            userSurname.text = it.get("surname") as String?
            userEmail.text = it.get("email") as String?
        }

        //boton que nos lleva a la actividad anterior
        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, BuyTicket2::class.java)
            startActivity(intent)
        }

        //boton que nos lleva a la actividad anterior
        val buyFinalButton: Button = findViewById(R.id.buyFinalButton)
        buyFinalButton.setOnClickListener {
            val intent = Intent(this, TicketBought::class.java)
            startActivity(intent)
        }

        //boton de la navigationBar - compra ticket 1
        val calendarioButton: ImageButton = findViewById(R.id.calendarioBtn)
        calendarioButton.setOnClickListener {
            val intent: Intent = Intent(this, UpcomingEvents::class.java)
            startActivity(intent)
        }


        //boton de la navigationBar - ir a la pagina inicio
        val homeButton: ImageButton = findViewById(R.id.homeBtn)
        homeButton.setOnClickListener {
            val intent: Intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

        //boton de la navigationBar - ir a la pagina de escanear QR
        val scanButton: ImageButton = findViewById(R.id.scanBtn)
        scanButton.setOnClickListener {
            val intent: Intent = Intent(this, EscanerQR::class.java)
            startActivity(intent)
        }

    }

    //funcion que nos mostrara un mensaje emergente dependiendo de que metodo de pagos elijamos
    fun buttonClick(view: View) {
        if (view is RadioButton) {
            // comprobamos si ha sido seleccionado
            val checked = view.isChecked
            // comprobamos cual de las tres opciones ha sido
            when (view.getId()) {
                R.id.radioCreditCard ->
                    if (checked) {
                        Toast.makeText(
                            this,
                            "Has seleccionado tarjeta de credito",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                R.id.radioPaypal ->
                    if (checked) {
                        Toast.makeText(this, "Has seleccionado paypal", Toast.LENGTH_SHORT).show()

                    }
                R.id.radioApplePay -> {
                    if (checked) {
                        Toast.makeText(this, "Has seleccionado applepay", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
