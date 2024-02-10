package com.edrhian.kotlinapp3tex


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edrhian.kotlinapp3tex.controller.AdapterLogros
import com.edrhian.kotlinapp3tex.data.Logro

class LogrosBloqueados : AppCompatActivity() {

    private lateinit var adapterLogros: AdapterLogros
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logros_bloqueados)

    }

    fun toProfile(view: View) {
        val intent = Intent(this, Perfil::class.java).apply {}
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        val layoutManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.rv_list_achievements)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapterLogros = AdapterLogros(getAchievementsList())
        recyclerView.adapter = adapterLogros
        adapterLogros.onItemClick  = {
            //Toast.makeText(this, "ID: " + it.id, Toast.LENGTH_SHORT).show()
            val i = Intent(this, LogroDescripcion::class.java)
            i.putExtra("descripcion", it.desc)
            startActivity(i)
        }
    }

    fun getAchievementsList() : ArrayList<Logro>{
        var achievementsList : ArrayList<Logro> = ArrayList()
        achievementsList.add(Logro(11, "Pasando el tiempo", "Juega un total de 1 hora", R.drawable.baseline_lock_24, 1))
        achievementsList.add(Logro(12, "Aflojale al 3T-EX", "Juega un total de 1 dia", R.drawable.baseline_lock_24, 1))
        achievementsList.add(Logro(13, "Jugador inseguro", "Tarda más de 5 minutos en poner una pieza", R.drawable.baseline_lock_24, 1))
        achievementsList.add(Logro(14, "He perdido la cuenta", "Coloca 1000 piezas en el tablero", R.drawable.baseline_lock_24, 1))
        achievementsList.add(Logro(15, "Dedicación", "Haz login en el juego durante 7 dias seguidos", R.drawable.baseline_lock_24, 1))
        achievementsList.add(Logro(16, "Cara a cara (literalmente)", "Juega una partida local", R.drawable.baseline_lock_24, 1))
        achievementsList.add(Logro(17, "En contra de ChatgpTEX", "Juega una partida contra la máquina", R.drawable.baseline_lock_24, 1))
        return achievementsList
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
    fun toBack(view: View) {
        super.onBackPressed()
    }
}