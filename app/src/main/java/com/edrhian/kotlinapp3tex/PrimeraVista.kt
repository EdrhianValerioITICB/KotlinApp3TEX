package com.edrhian.kotlinapp3tex

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

class PrimeraVista : AppCompatActivity() {
    private lateinit var mp: MediaPlayer
    private lateinit var logo : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.primera_vista)
        logo = findViewById(R.id.iv_logo)
        val animacion = AnimationUtils.loadAnimation(this, R.anim.logo_anim)
        logo.startAnimation(animacion)
        mp = MediaPlayer.create(this, R.raw.button_click)

        showPopup()
    }

    fun toLogin(view: View) {
        mp.start()
        val intent = Intent(this, Login::class.java).apply {}
        startActivity(intent)
    }
    fun toOnline(view: View) {
        mp.start()
        val intent = Intent(this, Online::class.java).apply {}
        startActivity(intent)
    }

    fun toOffline(view: View) {
        mp.start()
        val intent = Intent(this, Offline::class.java).apply {}
        startActivity(intent)
    }
    fun toProfile(view: View) {
        mp.start()
        val intent = Intent(this, Perfil::class.java).apply {}
        startActivity(intent)
    }




    private fun showPopup() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Bienvenido a 3TEX")
        builder.setMessage("Parece que es tu primera vez en 3TEX. Para continuar, por favor regístrate. Al hacer clic en 'IR', serás dirigido a la página de registro.")
        builder.setPositiveButton("IR") { dialog, _ ->
            dialog.dismiss()
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
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
}