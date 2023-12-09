package com.edrhian.kotlinapp3tex

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_menu)
    }

    fun toProfile(view: View) {
        val intent = Intent(this, Profile::class.java).apply {}
        startActivity(intent)
    }

    fun toOnline(view: View) {
        val intent = Intent(this, Online::class.java).apply {}
        startActivity(intent)
    }

    fun toOffline(view: View) {
        val intent = Intent(this, Offline::class.java).apply {}
        startActivity(intent)
    }
}