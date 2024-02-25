package com.edrhian.kotlinapp3tex

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {
    private lateinit var mp: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        mp = MediaPlayer.create(this, R.raw.button_click)
    }

    fun toSignUp(view: View) {
        mp.start()
        val intent = Intent(this, SignUp::class.java).apply {}
        startActivity(intent)
    }

    fun toMenu(view: View) {
        val etName: EditText = findViewById(R.id.editTextName)
        val etPassword: EditText = findViewById(R.id.editTextPassword)

        val admin = BaseDatos(this, "myDatabase", null, 1)
        val bd = admin.writableDatabase

        Log.d("MiApp", "Intento de inicio de sesión para el usuario: ${etName.text.toString()}")

        val fila = bd.rawQuery("SELECT NAME, PASSWORD FROM Users WHERE NAME='${etName.text.toString()}' AND PASSWORD='${etPassword.text.toString()}'", null)
        var name = ""
        var password = ""
        if (fila.moveToFirst()) {
            name = fila.getString(0)
            password = fila.getString(1)
        }


        Log.d("MiApp", "Nombre obtenido de la base de datos: $name")
        Log.d("MiApp", "Contraseña obtenida de la base de datos: $password")

        if (etName.text.toString() != "" && etPassword.text.toString() != "") {
            if (etName.text.toString() == name) {
                if (etPassword.text.toString() == password) {

                    val preferences: SharedPreferences = getSharedPreferences("isLogged", Context.MODE_PRIVATE)
                    val editor = preferences.edit()
                    editor.putBoolean("isLogged", true)
                    editor.apply()

                    val intent = Intent(this, Menu::class.java)
                    startActivity(intent)
                } else {
                    showToast("La contraseña es incorrecta.")
                }
            } else {
                showToast("El usuario no existe.")
            }
        } else {
            showToast("Debes ingresar un nombre y una contraseña.")
        }
        mp.start()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
