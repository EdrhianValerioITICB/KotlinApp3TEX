package com.edrhian.kotlinapp3tex

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.TooltipCompat
import androidx.core.widget.addTextChangedListener

class SignUp : AppCompatActivity() {

    private lateinit var userNameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var repeatPasswordEditText: EditText
    private lateinit var signUpButton: Button
    private lateinit var backButton: Button
    private lateinit var accountButton: ImageButton
    private lateinit var menuButton: ImageButton
    private lateinit var passwordTooltip: TextView
    private lateinit var userNameTooltip: TextView
    private lateinit var emailTooltip: TextView
    private lateinit var repeatPasswordTooltip: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_signup)

        userNameEditText = findViewById(R.id.editTextName)
        emailEditText = findViewById(R.id.editTextTextEmailAddress)
        passwordEditText = findViewById(R.id.editTextPassword)
        repeatPasswordEditText = findViewById(R.id.editTextTextPassword)
        signUpButton = findViewById(R.id.btn_online)
        passwordTooltip = findViewById<TextView>(R.id.passwordTooltip)
        userNameTooltip = findViewById<TextView>(R.id.userNameTooltip)
        emailTooltip = findViewById<TextView>(R.id.emailTooltip)
        repeatPasswordTooltip = findViewById<TextView>(R.id.repeatPasswordTooltip)

        // Set tooltips for EditTexts
        TooltipCompat.setTooltipText(userNameEditText, "El nombre ya existe")
        TooltipCompat.setTooltipText(emailEditText, "El correo electrónico no es válido")
        TooltipCompat.setTooltipText(repeatPasswordEditText, "Las contraseñas no coinciden")
        TooltipCompat.setTooltipText(passwordEditText, "Requisitos de contraseña: al menos 8 caracteres, una mayúscula, una minúscula, y 6 dígitos.")

        signUpButton.setOnClickListener {
            registerUser()
        }

        accountButton = findViewById(R.id.btn_account)
        accountButton.setOnClickListener {

        }

        // Hide the tooltips initially
        userNameTooltip.visibility = View.INVISIBLE
        emailTooltip.visibility = View.INVISIBLE
        repeatPasswordTooltip.visibility = View.INVISIBLE
        passwordTooltip.visibility = View.INVISIBLE
        // Set visibility of tooltip based on focus change of EditTexts
        passwordEditText.addTextChangedListener {
            if (!isValidPassword(passwordEditText.text.toString())) {
                passwordTooltip.visibility = View.VISIBLE
            } else {
                passwordTooltip.visibility = View.INVISIBLE
            }
        }

        userNameEditText.addTextChangedListener {
            if ( userNameExists(userNameEditText.text.toString())) {
                userNameTooltip.visibility = View.VISIBLE
            } else {
                userNameTooltip.visibility = View.INVISIBLE
            }
        }

        emailEditText.addTextChangedListener {
            if ( !isValidMail(emailEditText.text.toString())) {
                emailTooltip.visibility = View.VISIBLE
            } else {
                emailTooltip.visibility = View.INVISIBLE
            }
        }

        repeatPasswordEditText.addTextChangedListener {
            if ( passwordEditText.text.toString() != repeatPasswordEditText.text.toString()) {
                repeatPasswordTooltip.visibility = View.VISIBLE
            } else {
                repeatPasswordTooltip.visibility = View.INVISIBLE
            }
        }
    }

    private fun registerUser() {
        val userName = userNameEditText.text.toString()
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        val repeatPassword = repeatPasswordEditText.text.toString()

        if (userName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && repeatPassword.isNotEmpty()) {
            if (isValidMail(email)) {
                if (isValidPassword(password)) {
                    if (password == repeatPassword) {
                        val databaseHelper = BaseDatos(this, "myDatabase", null, 1)
                        val database = databaseHelper.writableDatabase

                        // Check for duplicate username
                        val cursor = database.rawQuery("SELECT * FROM Users WHERE NAME=?", arrayOf(userName))
                        if (cursor.count > 0) {
                            Toast.makeText(this, "El nombre de usuario ya está en uso", Toast.LENGTH_SHORT).show()
                            cursor.close()
                            return
                        }
                        cursor.close()

                        val values = ContentValues().apply {
                            put("NAME", userName)
                            put("MAIL", email)
                            put("PASSWORD", password)
                        }

                        val result = database.insert("Users", null, values)

                        if (result != -1L) {
                            Log.d("MiApp", "Usuario registrado correctamente")

                            Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT)
                                .show()

                            val intent = Intent(this, Menu::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Log.e("MiApp", "Error al registrar el usuario")

                            Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
                        }

                        database.close()
                    } else {
                        Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(
                        this,
                        "La contraseña no cumple con los requisitos mínimos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(this, "El correo electrónico no es válido", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordPattern =
            "(?=.*[A-Z])(?=.*[a-z])(?=.*\\d.*\\d.*\\d.*\\d.*\\d.*\\d).{8,}|(?=.*[A-Z])(?=.*[a-z])(?=.*\\d.*\\d.*[a-zA-Z].*\\d.*\\d).{8,}".toRegex()
        return passwordPattern.matches(password)
    }

    private fun isValidMail(mail: String): Boolean {
        val mailPattern =
            ".+\\@.+\\..+".toRegex()
        return mailPattern.matches(mail)
    }

    private fun userNameExists(userName: String): Boolean {
        val databaseHelper = BaseDatos(this, "myDatabase", null, 1)
        val database = databaseHelper.readableDatabase

        val cursor = database.rawQuery("SELECT * FROM Users WHERE NAME=?", arrayOf(userName))
        val exists = cursor.count > 0

        cursor.close()
        database.close()

        return exists
    }

}
