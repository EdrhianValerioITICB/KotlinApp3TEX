package com.edrhian.kotlinapp3tex

import android.os.Bundle
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

                val list: MutableList<Fragment> = ArrayList()
                list.add(TutorialOne())
                list.add(TutorialTwo())
                list.add(TutorialThree())
                list.add(TutorialFour())
                list.add(TutorialFive())
                list.add(TutorialSix())

                pager = findViewById(R.id.pager)
                pagerAdapter = SlidePagerAdapter(supportFragmentManager, list)
                pager.adapter = pagerAdapter
        }
}
