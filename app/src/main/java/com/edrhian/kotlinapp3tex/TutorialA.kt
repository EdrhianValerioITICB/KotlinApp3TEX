package com.edrhian.kotlinapp3tex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class TutorialA : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedBInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tutorial_a, container, false) as ViewGroup
    }
}