package com.example.techvirtualmuseum

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel

class homePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        //inicio del slider haciendo uso de las imagenes de firebase pero sin traerlas directamente de la base de datos
        val imageSlider = findViewById<ImageSlider>(R.id.imageSlider)
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel("https://firebasestorage.googleapis.com/v0/b/virtualtechmuseum-53a42.appspot.com/o/slider%2FgameboySinFondoBien.png?alt=media&token=a9c2261f-61ab-4baf-973b-cbd20abe3307"))
        imageList.add(SlideModel("https://firebasestorage.googleapis.com/v0/b/virtualtechmuseum-53a42.appspot.com/o/slider%2FgameboySinFOndo.png?alt=media&token=5f71760e-0646-4cf5-87fb-f6e7890d358b"))
        imageList.add(SlideModel("https://firebasestorage.googleapis.com/v0/b/virtualtechmuseum-53a42.appspot.com/o/slider%2FtecladoMecanicoSinFondo.png?alt=media&token=fa4e10bd-b8a9-4b15-9a42-00714359441b"))

        imageSlider.setImageList(imageList, ScaleTypes.CENTER_INSIDE)

        //listener para el boton que nos lleve a la pantalla del perfil del usuario
        val profilePage: ImageButton = findViewById(R.id.profilePage)
        profilePage.setOnClickListener {
            val intent: Intent = Intent(this, profilePage::class.java)
            startActivity(intent)
        }

        //listener para el boton que nos lleve a la pantalla de compra del ticket
        val buyTicketButton: Button = findViewById(R.id.buyTicket)
        buyTicketButton.setOnClickListener {
            val intent: Intent = Intent(this, buyTicket::class.java)
            startActivity(intent)
        }

        val mapaButton : ImageButton = findViewById(R.id.imgMapa)
        mapaButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.es/maps/place/Edificio+de+Inform%C3%A1tica+y+Matem%C3%A1ticas+-+ULPGC/@28.0731364,-15.4536018,17z/data=!3m2!4b1!5s0xc4095d48ff9735f:0xe7a7b5620ba98e3a!4m5!3m4!1s0xc4095d48fc41b6b:0x64299e4d4078258c!8m2!3d28.0731317!4d-15.4514078?hl=es"))
            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
            startActivity(browserIntent)

        }


    }


}
