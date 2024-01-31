package com.edrhian.kotlinapp3tex

import MenuViewModel
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

// MenuFragment.kt
class MenuFragment : Fragment() {

    private lateinit var viewModel: MenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del menú
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar el ViewModel
        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)

        // Configurar acciones para elementos del menú
        val btnOption1 = view.findViewById<Button>(R.id.btn_menu_play)
        btnOption1.setOnClickListener {
            val intent = Intent(requireContext(), Menu::class.java)
            startActivity(intent)
        }

        val btnOption2 = view.findViewById<Button>(R.id.btn_menu_config)
        btnOption2.setOnClickListener {
            val intent = Intent(requireContext(), Settings::class.java)
            startActivity(intent)
        }
        val btnOption3 = view.findViewById<Button>(R.id.btn_menu_tuto)
        btnOption3.setOnClickListener {
            val intent = Intent(requireContext(), TutorialMainViewPager::class.java)
            startActivity(intent)
        }
        val btnOption4 = view.findViewById<Button>(R.id.btn_menu_logout)
        btnOption4.setOnClickListener {
            val intent = Intent(requireContext(), FirstView::class.java)
            startActivity(intent)
        }
        val btnOption5 = view.findViewById<Button>(R.id.btn_menu_privacy)
        btnOption5.setOnClickListener {
            viewModel.selectOption("Opción 5")
        }

        // Observar cambios en el ViewModel
        viewModel.selectedOption.observe(viewLifecycleOwner, Observer {
            // Lógica para manejar la opción seleccionada
            Toast.makeText(requireContext(), "$it seleccionada", Toast.LENGTH_SHORT).show()
        })
    }
}
