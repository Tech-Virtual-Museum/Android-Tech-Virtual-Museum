package com.example.techvirtualmuseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class BuyEventTicket : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var database : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_event_ticket)

        //inicializamos firebase authentication y obtenemos una instancia de la base de datos
        auth = Firebase.auth
        database = FirebaseFirestore.getInstance()

        //inicializamos los campos
        val cantidadEntradas = findViewById<EditText>(R.id.cantidadEntradas)
        val n1 : String = cantidadEntradas.text.toString()
        var valorEntradas : Int = Integer.parseInt(n1)

        //inicializamos los campos para la infomacion personal
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

        //inicializamos los campos
        val muestraPrecio = findViewById<TextView>(R.id.precioEvent)
        val muestraNombre = findViewById<TextView>(R.id.nombreEvent)
        val muestraNombre2 = findViewById<TextView>(R.id.nombreEventito)
        val muestraCantidad2 = findViewById<EditText>(R.id.cantidadEntraditas)
        val muestraPrecio2 = findViewById<TextView>(R.id.precioEventito)
        val precioFinal = findViewById<EditText>(R.id.precioTotal)

        var total : Double

        //obtenemos la informacion del evento que ha sido clickado
        val nombreEvento = intent.getStringExtra("name")
        val precioEvento = intent.getStringExtra("pricing")

        //mostramos la informacion
        muestraPrecio.text = precioEvento
        muestraNombre.text = nombreEvento
        muestraNombre2.text = nombreEvento
        muestraPrecio2.text = precioEvento

        //convertimos el valor a un entero para trabajar con el
        val n : Double = precioEvento!!.toDouble()

        //dismminuimos en 1  las entradas
        val menosEntradasBtn :  Button = findViewById(R.id.menosEntradas)
        menosEntradasBtn.setOnClickListener {
            if ( valorEntradas == 0){
                cantidadEntradas.setText("0")
                muestraCantidad2.setText("$valorEntradas")
                total = (valorEntradas * n)
                precioFinal.setText("$total")

            }else{
                valorEntradas += (-1)
                cantidadEntradas.setText("$valorEntradas")
                muestraCantidad2.setText("$valorEntradas")
                total = (valorEntradas * n)
                precioFinal.setText("$total")
            }
        }

        //aumentamos en 1 el numero de entradas
        val masEntradasBtn : Button = findViewById(R.id.masEntradas)
        masEntradasBtn.setOnClickListener {
            valorEntradas +=1
            cantidadEntradas.setText("$valorEntradas")
            muestraCantidad2.setText("$valorEntradas")
            total = (valorEntradas * n)
            precioFinal.setText("$total")

        }

        //listener para el boton de volver atras
        val backButton : ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent : Intent = Intent(this, EventDetails::class.java)
            startActivity(intent)
        }

        //listener que nos dirige a la pantalla final informativa
        val buyFinalButton : Button = findViewById(R.id.buyFinalButton)
        buyFinalButton.setOnClickListener{
            val intent : Intent = Intent(this, TicketBought::class.java)
            //guardamos la informacion del usuario, metodo de pago y la informacion del evento a firebase
            val dato = hashMapOf("email" to idUser, "nameEvent" to nombreEvento,  "priceEvent" to precioEvento)
            database.collection("eventoComprado").document(idUser).set(dato)
            startActivity(intent)
        }

        //boton de la navigationBar - actividad de eventos
        val calendarioButton : ImageButton = findViewById(R.id.calendarioBtn)
        calendarioButton.setOnClickListener {
            val intent : Intent = Intent(this, UpcomingEvents::class.java)
            startActivity(intent)
        }


        //boton de la navigationBar - ir a la pagina inicio
        val homeButton : ImageButton = findViewById(R.id.homeBtn)
        homeButton.setOnClickListener {
            val intent : Intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

        //boton de la navigationBar - ir a la pagina de escanear QR
        val scanButton : ImageButton = findViewById(R.id.scanBtn)
        scanButton.setOnClickListener {
            val intent: Intent = Intent(this, EscanerQR::class.java)
            startActivity(intent)
        }
    }
}