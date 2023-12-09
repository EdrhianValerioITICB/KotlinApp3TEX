package com.edrhian.kotlinapp3tex

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_login)
    }

    fun toSignUp(view: View) {
        val intent = Intent(this, SignUp::class.java).apply {}
        startActivity(intent)
    }

    fun toMenu(view: View) {
        val intent = Intent(this, Menu::class.java).apply {}
        startActivity(intent)
    }

    fun toBack(view: View) {
        super.onBackPressed()
    }
}