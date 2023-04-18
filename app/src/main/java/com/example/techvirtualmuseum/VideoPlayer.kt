package com.example.techvirtualmuseum

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView


class VideoPlayer: YouTubeBaseActivity() {

    private lateinit var youtubePlayerInit : YouTubePlayer.OnInitializedListener
    private lateinit var calendarioButton: ImageButton
    private lateinit var homeButton: ImageButton
    private lateinit var scanButton: ImageButton
    private lateinit var backButton: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        val youTubePlayer: YouTubePlayerView = findViewById(R.id.youtubePlayer)
        val btnPlay: Button = findViewById(R.id.btnPlay)

        initButtons()

        val idVideoProducto = intent.getStringExtra("videoId")

        youtubePlayerInit = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean) {
                if (player == null) return
                if (wasRestored) {
                    player.play()
                } else {
                    player.loadVideo(idVideoProducto)
                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
                }
            }

            override fun onInitializationFailure(provider: YouTubePlayer.Provider?, result: YouTubeInitializationResult?) {
                // Handle initialization failure
            }
        }

        btnPlay.setOnClickListener {
            val apiKey = getString(R.string.youtube_api_key)
            youTubePlayer.initialize(apiKey, youtubePlayerInit)
        }
    }


    private fun initButtons() {
        backButton = findViewById(R.id.backButton)
        backButton.setOnClickListener{startActivity(Intent(this, ProductDetails::class.java))}

        calendarioButton = findViewById(R.id.calendarioBtn)
        calendarioButton.setOnClickListener { startActivity(Intent(this, UpcomingEvents::class.java)) }

        homeButton = findViewById(R.id.homeBtn)
        homeButton.setOnClickListener { startActivity(Intent(this, HomePage::class.java)) }

        scanButton =findViewById(R.id.scanBtn)
        scanButton.setOnClickListener { startActivity(Intent(this, EscanerQR::class.java)) }
    }
}