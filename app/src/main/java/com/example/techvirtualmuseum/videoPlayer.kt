package com.example.techvirtualmuseum

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView


class videoPlayer: YouTubeBaseActivity() {

    private val YOUTUBE_API_KEY ="AIzaSyA280whFAGngDw3523jqqvXlQ5sKyQjdrE"

    lateinit var youtubePlayerInit : YouTubePlayer.OnInitializedListener

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        val youTubePlayer : YouTubePlayerView = findViewById(R.id.youtubePlayer)
        val btnPlay : Button = findViewById(R.id.btnPlay)

        //obtenemos los datos
        val idVideoProducto = intent.getStringExtra("videoId")
        val urlVideo = intent.getStringExtra("video")

        youtubePlayerInit = object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean){
                if(player == null) return
                if (wasRestored)
                    player.play()
                else{
                    player.loadVideo(idVideoProducto)
                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
                }

            }
            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {

            }
        }

        btnPlay.setOnClickListener {
            youTubePlayer.initialize(YOUTUBE_API_KEY, youtubePlayerInit)
        }

        //listener para el boton de volver atras
        val backButton : ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent : Intent = Intent(this, productDetails::class.java)
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
            val intent : Intent = Intent(this, escanerQR::class.java)
            startActivity(intent)
        }

    }
}