package com.example.techvirtualmuseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
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
            userName.text = it.get("name") as String?
            userSurname.text = it.get("surname") as String?
            userEmail.text = it.get("email") as String?
        }

        //inicializamos los campos para posteriormente añadir la informacion
        val nameEvent = findViewById<TextView>(R.id.nameEvent)
        val dateEvent = findViewById<TextView>(R.id.dateEvent)
        val hourEvent = findViewById<TextView>(R.id.hourEvent)
        val priceEvent = findViewById<TextView>(R.id.priceEvent)

        //obtenemos la informacion del evento que ha sido clickado
        val nombreEvento = intent.getStringExtra("name");
        val fechaEvento = intent.getStringExtra("date");
        val horaEvento = intent.getStringExtra("hour");
        val precioEvento = intent.getStringExtra("pricing");

        //mostramos la informacion
        nameEvent.text = nombreEvento
        dateEvent.text = fechaEvento
        hourEvent.text = horaEvento
        priceEvent.text = precioEvento

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
            //guardamos la informacion del usuario, metodo de pago y la informacion del evento a firebase
            val dato = hashMapOf("nameEvent" to nombreEvento, "dateEvent" to fechaEvento, "hourEvent" to horaEvento,
                "priceEvent" to precioEvento)
            database.collection("eventoComprado").document(idUser).set(dato)
            startActivity(intent)
        }

        //boton de la navigationBar - actividad de eventos
        val calendarioButton : ImageButton = findViewById(R.id.calendarioBtn)
        calendarioButton.setOnClickListener {
            val intent : Intent = Intent(this, upcomingEvents::class.java)
            startActivity(intent)
        }


        //boton de la navigationBar - ir a la pagina inicio
        val homeButton : ImageButton = findViewById(R.id.homeBtn)
        homeButton.setOnClickListener {
            val intent : Intent = Intent(this, homePage::class.java)
            startActivity(intent)
        }

        //boton de la navigationBar - ir a la pagina de escanear QR
        val scanButton : ImageButton = findViewById(R.id.scanBtn)
        scanButton.setOnClickListener {
            val intent: Intent = Intent(this, escanerQR::class.java)
            startActivity(intent)
        }
    }
}