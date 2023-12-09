package com.edrhian.kotlinapp3tex

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class TutorialOne : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_tutorial_1)
    }

    fun toTutorialTwo(view: View) {
        val intent = Intent(this, TutorialTwo::class.java).apply {}
        startActivity(intent)
    }
}