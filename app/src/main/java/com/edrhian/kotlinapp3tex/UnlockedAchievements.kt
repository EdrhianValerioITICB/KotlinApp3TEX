package com.edrhian.kotlinapp3tex

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edrhian.kotlinapp3tex.controller.AdapterAchievements
import com.edrhian.kotlinapp3tex.data.Achievement

class UnlockedAchievements : AppCompatActivity() {

    lateinit var context: Context
    private lateinit var adapterAchievements: AdapterAchievements
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_unlocked_achievements)

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
        adapterAchievements = AdapterAchievements(getAchievementsList())
        recyclerView.adapter = adapterAchievements

        adapterAchievements.onItemClick  = {
            //Toast.makeText(this, "ID: " + it.id, Toast.LENGTH_SHORT).show()
            val i = Intent(this, AchievementDesc::class.java)
            i.putExtra("descripcion", it.desc)
            startActivity(i)
        }
    }

    fun getAchievementsList() : ArrayList<Achievement>{
        var achievementsList : ArrayList<Achievement> = ArrayList()

        achievementsList.add(Achievement(1, "La primera de muchas", "Gana una partida", R.drawable.baseline_lock_open_24, 1))
        achievementsList.add(Achievement(2, "La primera de muchas (desgraciadamente)", "Pierde una partida", R.drawable.baseline_lock_open_24, 1))
        achievementsList.add(Achievement(3, "¿Y ahora qué?", "Empata una partida", R.drawable.baseline_lock_open_24, 1))
        achievementsList.add(Achievement(4, "Tic Tac", "Gana una partida por falta de tiempo del oponente", R.drawable.baseline_lock_open_24, 1))
        achievementsList.add(Achievement(5, "Piensa rápido", "Pierde una partida por falta de tiempo", R.drawable.baseline_lock_open_24, 1))
        achievementsList.add(Achievement(6, "Principiante", "Gana 10 partidas", R.drawable.baseline_lock_open_24, 1))
        achievementsList.add(Achievement(7, "Avanzado", "Gana 100 partidas", R.drawable.baseline_lock_open_24, 1))
        achievementsList.add(Achievement(8, "Maestro de las fichas", "Gana 1000 partidas", R.drawable.baseline_lock_open_24, 1))
        achievementsList.add(Achievement(9, "Juego mental", "Gana una partida por abandono de oponente", R.drawable.baseline_lock_open_24, 1))
        achievementsList.add(Achievement(10, "ALT+F4", "Pierde una partida por abandono", R.drawable.baseline_lock_open_24, 1))


        return achievementsList
    }

    fun toProfile(view: View) {
        val intent = Intent(this, Profile::class.java).apply {}
        startActivity(intent)
    }

    fun toLockedAchivements(view: View) {
        val intent = Intent(this, LockedAchievements::class.java).apply {}
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