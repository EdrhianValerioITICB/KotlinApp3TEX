package com.edrhian.kotlinapp3tex

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class TutorialMainViewPager : AppCompatActivity() {
    private lateinit var pager: ViewPager
    private lateinit var pagerAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_tutorial_main_viewpager)
        //TODO(String)
        Toast.makeText(this, "Desliza la pantalla hacia la derecha y la izquierda para avanzar o retroceder", Toast.LENGTH_LONG).show()
        Toast.makeText(this, "Para salir pulsa el boton de retroceder de tu móvil", Toast.LENGTH_LONG).show()
        val list: MutableList<Fragment> = ArrayList()
        list.add(TutorialA())
        list.add(TutorialB())
        list.add(TutorialC())
        list.add(TutorialD())
        list.add(TutorialE())
        list.add(TutorialF())

        pager = findViewById(R.id.pager)
        pagerAdapter = SlidePagerAdapter(supportFragmentManager, list)
        pager.adapter = pagerAdapter
    }
}
