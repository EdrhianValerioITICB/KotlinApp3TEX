package com.edrhian.kotlinapp3tex

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Online : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_online)
    }

    fun toMatchmaking(view: View) {
        val intent = Intent(this, Matchmaking::class.java).apply {}
        startActivity(intent)
    }

    fun toInvitation(view: View) {
        val intent = Intent(this, Invitation::class.java).apply {}
        startActivity(intent)
    }

    fun toProfile(view: View) {
        val intent = Intent(this, Profile::class.java).apply {}
        startActivity(intent)
    }

    fun toBack(view: View) {
        super.onBackPressed()
    }
}