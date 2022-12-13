package com.example.techvirtualmuseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.VideoView

class videoPlayer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        val videoPlayer : VideoView = findViewById(R.id.videoPlayer)
        val verVideo = intent.getStringExtra("video")

        videoPlayer.setVideoPath(verVideo)

    }
}