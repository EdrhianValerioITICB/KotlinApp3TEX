package com.edrhian.kotlinapp3tex

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AchievementDesc : Activity(){
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val desc = intent.getStringExtra("descripcion")
        setContentView(R.layout.achievement_desc)
        textView = findViewById(R.id.TextView01)
        textView.setText(desc)
    }
}