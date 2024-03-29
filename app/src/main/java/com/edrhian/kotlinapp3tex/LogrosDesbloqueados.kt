package com.edrhian.kotlinapp3tex

import android.media.MediaPlayer
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edrhian.kotlinapp3tex.controller.AdapterLogros
import com.edrhian.kotlinapp3tex.data.Logro


class LogrosDesbloqueados : AppCompatActivity() {
    private lateinit var mp: MediaPlayer
    lateinit var context: Context
    private lateinit var adapterLogros: AdapterLogros
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logros_desbloqueados)
        mp = MediaPlayer.create(this, R.raw.button_click)
    }
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val rootView : View = inflater.inflate(R.layout.fragment_tareas,container,false)
//        return rootView
//    }

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
            mp.start()
        }
    }

    fun getAchievementsList() : ArrayList<Logro>{
        var achievementsList : ArrayList<Logro> = ArrayList()
        return achievementsList
    }

    fun toProfile(view: View) {
        mp.start()
        val intent = Intent(this, Perfil::class.java).apply {}
        startActivity(intent)
    }

    fun toLockedAchivements(view: View) {
        mp.start()
        val intent = Intent(this, LogrosBloqueados::class.java).apply {}
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

    fun toBack(view: View) {
        super.onBackPressed()
    }
}