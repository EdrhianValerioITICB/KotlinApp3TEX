package com.edrhian.kotlinapp3tex

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Sound : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_sound)
    }

    fun toBack(view: View) {
        super.onBackPressed()
    }
}