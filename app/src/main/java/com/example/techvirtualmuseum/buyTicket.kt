package com.example.techvirtualmuseum

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView

class buyTicket : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_ticket)

        //mostramos el dia actual por defecto
        val calendarView : MaterialCalendarView = findViewById(R.id.calendarView)
        calendarView.selectedDate = CalendarDay.today()


        //boton que nos llevara a la actvidad anterior
        val backButton : ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent : Intent = Intent(this, homePage::class.java)
            startActivity(intent)
        }

        //boton para continuar con la compra
        val continueTicket  : Button = findViewById(R.id.continueTicket)
        continueTicket.setOnClickListener {
            val intent : Intent = Intent (this, buyTicket2::class.java)
            startActivity(intent)
        }

        //boton de la navigationBar - compra ticket 1
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

    //funcion que muestrara un mensaje dependiendo de que hora hayas seleccionado
    fun buttonClick(view: View) {
        if (view is RadioButton) {
            // comprobamos si ha sido seleccionado
            val checked = view.isChecked
            // comprobamos cual de las tres opciones ha sido
            when (view.getId()) {
                R.id.radioHour1 ->
                    if (checked) {
                        Toast.makeText(this, "Has seleccionado 10:00 AM", Toast.LENGTH_SHORT
                        ).show()
                    }

                R.id.radioHour2 ->
                    if (checked) {
                        Toast.makeText(this, "Has seleccionado 10:30 AM", Toast.LENGTH_SHORT
                        ).show()
                    }

                R.id.radioHour3 ->
                    if (checked) {
                        Toast.makeText(this, "Has seleccionado 11:00 AM", Toast.LENGTH_SHORT
                        ).show()
                    }

                R.id.radioHour4 ->
                    if (checked) {
                        Toast.makeText(this, "Has seleccionado 11:30 AM", Toast.LENGTH_SHORT).show()

                    }

                R.id.radioHour5 ->
                    if (checked) {
                        Toast.makeText(this, "Has seleccionado 12:00 AM", Toast.LENGTH_SHORT
                        ).show()
                    }

                R.id.radioHour6 ->
                    if (checked) {
                        Toast.makeText(this, "Has seleccionado 12:30 AM", Toast.LENGTH_SHORT
                        ).show()
                    }

                R.id.radioHour7 ->
                    if (checked) {
                        Toast.makeText(this, "Has seleccionado 1:00 PM", Toast.LENGTH_SHORT).show()
                    }

                R.id.radioHour8 ->
                    if (checked) {
                        Toast.makeText(this, "Has seleccionado 1:30 PM", Toast.LENGTH_SHORT
                        ).show()
                    }

                R.id.radioHour9 ->
                    if (checked) {
                        Toast.makeText(this, "Has seleccionado 2:00 PM", Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        }
    }


}