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

class HomePage : AppCompatActivity() {

    private lateinit var profilePageBtn: ImageButton
    private lateinit var buyTicketButton: Button
    private lateinit var mapaButton: ImageButton
    private lateinit var calendarioButton: ImageButton
    private lateinit var homeButton: ImageButton
    private lateinit var scanButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        initImageSlider()
        initButtons()
    }

    private fun initImageSlider() {
        val imageSlider = findViewById<ImageSlider>(R.id.imageSlider)
        val imageList = arrayListOf(
            SlideModel("https://firebasestorage.googleapis.com/v0/b/virtualtechmuseum-53a42.appspot.com/o/slider%2FgameboySinFondoBien.png?alt=media&token=aea8255d-dd2d-42b3-abbb-6d7b3e442801"),
            SlideModel("https://firebasestorage.googleapis.com/v0/b/virtualtechmuseum-53a42.appspot.com/o/slider%2FgameboySinFOndo.png?alt=media&token=2b92c4a9-4d37-448c-bd4c-a7e931fa60ac"),
            SlideModel("https://firebasestorage.googleapis.com/v0/b/virtualtechmuseum-53a42.appspot.com/o/slider%2FtecladoMecanicoSinFondo.png?alt=media&token=231533d2-0896-4464-acc4-2ceff20df84d")
        )

        imageSlider.setImageList(imageList, ScaleTypes.CENTER_INSIDE)
    }

    private fun initButtons() {
        profilePageBtn = findViewById(R.id.profileImage)
        profilePageBtn.setOnClickListener { startActivity(Intent(this, ProfilePage::class.java)) }

        buyTicketButton = findViewById(R.id.buyTicket)
        buyTicketButton.setOnClickListener { startActivity(Intent(this, BuyTicket::class.java)) }

        mapaButton = findViewById(R.id.imgMapa)
        mapaButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.es/maps/place/Edificio+de+Inform%C3%A1tica+y+Matem%C3%A1ticas+-+ULPGC/@28.0731364,-15.4536018,17z/data=!3m2!4b1!5s0xc4095d48ff9735f:0xe7a7b5620ba98e3a!4m5!3m4!1s0xc4095d48fc41b6b:0x64299e4d4078258c!8m2!3d28.0731317!4d-15.4514078?hl=es"))
            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity")
            startActivity(browserIntent)
        }

        calendarioButton = findViewById(R.id.calendarioBtn)
        calendarioButton.setOnClickListener { startActivity(Intent(this, UpcomingEvents::class.java)) }

        homeButton = findViewById(R.id.homeBtn)
        homeButton.setOnClickListener { startActivity(Intent(this, HomePage::class.java)) }

        scanButton =findViewById(R.id.scanBtn)
        scanButton.setOnClickListener { startActivity(Intent(this, EscanerQR::class.java)) }
    }
}