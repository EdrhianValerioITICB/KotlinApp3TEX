package com.edrhian.kotlinapp3tex

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

class TutorialOne : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_tutorial_1)
    }

    fun toProfile(view: View) {
        val intent = Intent(this, Profile::class.java).apply {}
        startActivity(intent)
    }
    fun showMenuFragment(view: View) {
        val menuFragment = MenuFragment()

        // Agregar el fragmento al contenedor
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, menuFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()

        // Obtener referencia a la View transparente
        val transparentView = findViewById<View>(R.id.transparentView)

        // Configurar el OnClickListener para la View transparente
        transparentView.setOnClickListener {
            // Ocultar el fragmento del menú al hacer clic en el área fuera del menú
            supportFragmentManager.beginTransaction()
                .remove(menuFragment)
                .commit()

            transparentView.visibility = View.GONE
        }

        // Mostrar la View transparente al mostrar el fragmento del menú
        transparentView.visibility = View.VISIBLE
    }

    fun toTutorialTwo(view: View) {
        val intent = Intent(this, TutorialTwo::class.java).apply {}
        startActivity(intent)
    }
}