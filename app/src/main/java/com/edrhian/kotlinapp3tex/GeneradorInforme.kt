package com.edrhian.kotlinapp3tex

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class GeneradorInforme : AppCompatActivity(){
    private lateinit var mp: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.generador_informe)
        mp = MediaPlayer.create(this, R.raw.button_click)
    }
}