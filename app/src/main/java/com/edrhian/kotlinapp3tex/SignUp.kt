package com.edrhian.kotlinapp3tex

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_signup)
    }

    fun toMenu(view: View) {
        val intent = Intent(this, Menu::class.java).apply {}
        startActivity(intent)
    }

    fun toBack(view: View) {
        super.onBackPressed()
    }
}