package com.example.techvirtualmuseum

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView

class BuyTicket : AppCompatActivity() {

    private lateinit var backButton: ImageButton
    private lateinit var continueTicket: Button
    private lateinit var calendarioButton: ImageButton
    private lateinit var homeButton: ImageButton
    private lateinit var scanButton: ImageButton
    private lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_ticket)

        setupViews()

        // mostramos el día actual por defecto
        val calendarView: MaterialCalendarView = findViewById(R.id.calendarView)
        calendarView.selectedDate = CalendarDay.today()

        backButton.setOnClickListener {
            startActivity(Intent(this, HomePage::class.java))
        }

        continueTicket.setOnClickListener {
            startActivity(Intent(this, BuyTicket2::class.java))
        }

        calendarioButton.setOnClickListener {
            startActivity(Intent(this, UpcomingEvents::class.java))
        }

        homeButton.setOnClickListener {
            startActivity(Intent(this, HomePage::class.java))
        }

        scanButton.setOnClickListener {
            startActivity(Intent(this, EscanerQR::class.java))
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val radioButton = findViewById<RadioButton>(checkedId)
            val selectedHour = radioButton.text
            Toast.makeText(this, "Has seleccionado $selectedHour", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupViews() {
        backButton = findViewById(R.id.backButton)
        continueTicket = findViewById(R.id.continueTicket)
        calendarioButton = findViewById(R.id.calendarioBtn)
        homeButton = findViewById(R.id.homeBtn)
        scanButton = findViewById(R.id.scanBtn)
        radioGroup = findViewById(R.id.buttonsGroup)
    }
}
