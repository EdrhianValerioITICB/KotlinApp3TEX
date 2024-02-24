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

        val admin = BaseDatos(this, "bd", null, 1)
        val bd = admin.writableDatabase

        val fila = bd.rawQuery("SELECT ID, NOMBRE, DESCRIPCION, IMAGEN, BLOQUEADO, USUARIO FROM Logros", null)

        var id = 0
        var name = ""
        var desc = ""
        var imagen = 0
        var bloqueado = 0
        var user = 0

        if (fila.moveToFirst()){
            do {
                id = fila.getString(0).toInt()
                name = fila.getString(1)
                desc = fila.getString(2)
                imagen = fila.getString(3).toInt()
                bloqueado = fila.getString(4).toInt()
                user = fila.getString(5).toInt()
                achievementsList.add(Logro(id, name, desc, imagen, bloqueado, user))
            } while (fila.moveToNext())
        }

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