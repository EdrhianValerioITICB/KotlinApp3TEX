package com.edrhian.kotlinapp3tex

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_settings)
    }

    fun toLanguage(view: View) {
        val intent = Intent(this, Language::class.java).apply {}
        startActivity(intent)
    }

    fun toSound(view: View) {
        val intent = Intent(this, Sound::class.java).apply {}
        startActivity(intent)
    }

    fun toTheme(view: View) {
        val intent = Intent(this, Theme::class.java).apply {}
        startActivity(intent)
    }

    fun toPieceStyle(view: View) {
        val intent = Intent(this, PieceStyle::class.java).apply {}
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