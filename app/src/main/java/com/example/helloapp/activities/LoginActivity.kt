package com.example.helloapp.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.helloapp.R

class LoginActivity : AppCompatActivity() {

    private lateinit var buttonRegisterLogin: Button
    private lateinit var buttonRestorePass: Button
    private lateinit var editTextCorreo: EditText
    private lateinit var editTextContrasena: EditText
    private lateinit var buttonLogeo: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLogeo = findViewById(R.id.btEntrar)
        buttonLogeo.setOnClickListener {
            //validar campos
            if(validarCampos()){

                //redireccionar al login
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                finish()

                limpiarCampos()
            }

        }

        buttonRegisterLogin = findViewById(R.id.btRegistroLogin)
        buttonRegisterLogin.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        buttonRestorePass = findViewById(R.id.btRecuperar)
        buttonRestorePass.setOnClickListener {
            val intent = Intent(this, RestoreActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validarCampos(): Boolean {
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        editTextCorreo = findViewById(R.id.textUserLogin)
        editTextContrasena = findViewById(R.id.textPassLogin)
        val correo = editTextCorreo.text.toString().trim()
        val contrasena = editTextContrasena.text.toString().trim()

        if (correo.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa tu correo", Toast.LENGTH_SHORT).show()
            return false
        }
        if (contrasena.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa tu contraseña", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Toast.makeText(this, "Por favor, ingresa un correo electrónico válido", Toast.LENGTH_SHORT).show()
            return false
        }
        // Obtener los datos almacenados en SharedPreferences
        val correoGuardado = sharedPreferences.getString("correo", "")
        val contrasenaGuardada = sharedPreferences.getString("contrasena", "")

        // Verificar si el correo y la contraseña coinciden con los datos guardados
        if (correo == correoGuardado && contrasena == contrasenaGuardada) {
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
            return true
        } else {
            Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            return false
        }
        return true

    }

    private fun limpiarCampos() {
        editTextCorreo.text.clear()
        editTextContrasena.text.clear()
    }
}