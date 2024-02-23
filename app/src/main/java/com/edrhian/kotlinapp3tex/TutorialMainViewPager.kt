package com.edrhian.kotlinapp3tex

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener

class TutorialMainViewPager : AppCompatActivity(), OnPageChangeListener {
    private lateinit var pager: ViewPager
    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var mp: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tutorial_main_viewpager)
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
        pager.addOnPageChangeListener(this)
        mp = MediaPlayer.create(this,R.raw.tutorial_next)
    }

    override fun onPageScrollStateChanged(state: Int) {
        if (state == ViewPager.SCROLL_STATE_DRAGGING) {
            mp.start()
            val thread = Thread {
                mp.start()
                mp.seekTo(0)
            }
            thread.start()
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {

    }
}
