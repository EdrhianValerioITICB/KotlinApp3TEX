package com.edrhian.kotlinapp3tex

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Matchmaking : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_matchmaking)
    }
    fun toBack(view: View) {
        super.onBackPressed()
    }
}