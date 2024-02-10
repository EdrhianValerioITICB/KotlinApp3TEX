package com.edrhian.kotlinapp3tex

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class LogroDescripcion : Activity(){
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val desc = intent.getStringExtra("descripcion")
        setContentView(R.layout.logro_descripcion)
        textView = findViewById(R.id.TextView01)
        textView.setText(desc)
    }
}