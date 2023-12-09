package com.edrhian.kotlinapp3tex

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class LockedAchievements : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_locked_achievements)
    }

    fun toProfile(view: View) {
        val intent = Intent(this, Profile::class.java).apply {}
        startActivity(intent)
    }

    fun toUnlockedAchivements(view: View) {
        val intent = Intent(this, UnlockedAchievements::class.java).apply {}
        startActivity(intent)
    }

    fun toBack(view: View) {
        super.onBackPressed()
    }
}