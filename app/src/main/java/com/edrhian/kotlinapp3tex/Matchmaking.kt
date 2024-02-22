package com.edrhian.kotlinapp3tex

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Matchmaking : AppCompatActivity() {
    private lateinit var mp: MediaPlayer
    private var posicion = 0
    private lateinit var animacion: Animation
    private lateinit var iv_logo_mm: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.matchmaking)

        mp = MediaPlayer.create(this, R.raw.op_emparejamiento)
        mp.start()
        iv_logo_mm = findViewById(R.id.iv_logo_mm)
        animacion = AnimationUtils.loadAnimation(this, R.anim.matchmaking_anim)
        animacion.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                iv_logo_mm.startAnimation(animacion)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })

        iv_logo_mm.startAnimation(animacion)
    }

    override fun onPause() {
        super.onPause()
        posicion = mp.getCurrentPosition();
        mp.pause();
    }

    override fun onResume() {
        super.onResume()
        mp.seekTo(posicion)
        mp.start()
    }
    fun toBack(view: View) {
        super.onBackPressed()
    }
}