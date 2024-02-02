package com.edrhian.kotlinapp3tex

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUp : AppCompatActivity() {

    private lateinit var userNameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var repeatPasswordEditText: EditText
    private lateinit var signUpButton: Button
    private lateinit var backButton: Button
    private lateinit var accountButton: ImageButton
    private lateinit var menuButton: ImageButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_signup)


        userNameEditText = findViewById(R.id.editTextName)
        emailEditText = findViewById(R.id.editTextTextEmailAddress)
        passwordEditText = findViewById(R.id.editTextPassword)
        repeatPasswordEditText = findViewById(R.id.editTextTextPassword)
        signUpButton = findViewById(R.id.btn_online)


        //backButton = findViewById(R.id.btn_back)


        signUpButton.setOnClickListener {
            registerUser()
        }


        //backButton.setOnClickListener {
        //    super.onBackPressed()
        //}


        accountButton = findViewById(R.id.btn_account)
        accountButton.setOnClickListener {

        }


//        menuButton = findViewById(R.id.btn_menu)
//        menuButton.setOnClickListener {
//            val intent = Intent(this, Menu::class.java)
//            startActivity(intent)
//        }
    }

    private fun registerUser() {
        val userName = userNameEditText.text.toString()
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        val repeatPassword = repeatPasswordEditText.text.toString()


        if (userName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && password == repeatPassword) {


            val values = ContentValues().apply {
                put("NAME", userName)
                put("MAIL", email)
                put("PASSWORD", password)
            }


            Log.d("MiApp", "Valores a insertar: $values")

            val databaseHelper = BaseDatos(this, "myDatabase", null, 1)
            val database = databaseHelper.writableDatabase

            val result = database.insert("Users", null, values)

            if (result != -1L) {
                Log.d("MiApp", "Usuario registrado correctamente")

                Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, Menu::class.java)
                startActivity(intent)
                finish()
            } else {
                Log.e("MiApp", "Error al registrar el usuario")

                Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
            }


            Log.d("MiApp", "Resultado de la inserción: $result")

            database.close()
        } else {
            Toast.makeText(
                this,
                "Completa todos los campos y asegúrate de que las contraseñas coincidan",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}
