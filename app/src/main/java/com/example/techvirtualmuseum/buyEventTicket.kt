package com.example.techvirtualmuseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class buyEventTicket : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var database : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_event_ticket)

        //inicializamos firebase authentication y obtenemos una instancia de la base de datos
        auth = Firebase.auth
        database = FirebaseFirestore.getInstance()

        //inicializamos los campos para la infomacion personal
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

        //inicializamos los campos para posteriormente añadir la informacion
        val nameEvent = findViewById<TextView>(R.id.nameEvent)
        val dateEvent = findViewById<TextView>(R.id.dateEvent)
        val hourEvent = findViewById<TextView>(R.id.hourEvent)
        val priceEvent = findViewById<TextView>(R.id.priceEvent)

        //obtenemos la informacion del evento que ha sido clickado
        val nombreEvento = getIntent().getStringExtra("name");
        val fechaEvento = getIntent().getStringExtra("date");
        val horaEvento = getIntent().getStringExtra("hour");
        val precioEvento = getIntent().getStringExtra("pricing");

        //mostramos la informacion
        nameEvent.setText(nombreEvento)
        dateEvent.setText(fechaEvento)
        hourEvent.setText(horaEvento)
        priceEvent.setText(precioEvento)

        //listener para el boton de volver atras
        val backButton : ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent : Intent = Intent(this, eventDetails::class.java)
            startActivity(intent)
        }

        //listener que nos dirige a la pantalla final informativa
        val buyFinalButton : Button = findViewById(R.id.buyFinalButton)
        buyFinalButton.setOnClickListener{
            val intent : Intent = Intent(this, ticketBought::class.java)
            startActivity(intent)
        }
    }

    fun buttonClick(view: View) {
        if ( view is RadioButton){
            if (view.id == R.id.radioCreditCard){
                Toast.makeText(this, "Has seleccionado tarjeta de credito", Toast.LENGTH_SHORT).show()
            }

            if (view.id == R.id.radioPaypal){
                Toast.makeText(this, "Has seleccionado paypal", Toast.LENGTH_SHORT).show()
            }

            if (view.id == R.id.radioApplePay){
                Toast.makeText(this, "Has seleccionado apple pay", Toast.LENGTH_SHORT).show()
            }
        }
    }

}