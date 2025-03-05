package com.example.helloapp

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var perfilApellido: TextView
    private lateinit var perfilTelefono: TextView
    private lateinit var perfilCorreo: TextView
    private lateinit var perfilNombre: TextView
    private lateinit var perfilBoton: Button
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        // Recuperar el correo del Intent
        val correoUsuario = sharedPreferences.getString("correo", "")
        val nombreUsuario = sharedPreferences.getString("nombres", "")
        val apellidoUsuario = sharedPreferences.getString("apellidos", "")
        val telefonoUsuario = sharedPreferences.getString("telefono", "")


        // Buscar el TextView y asignarle el correo
        val perfilCorreo = findViewById<TextView>(R.id.perfilCorreo)
        val perfilNombre = findViewById<TextView>(R.id.perfilNombre)
        val perfilApellido = findViewById<TextView>(R.id.perfilApellidos)
        val perfilTelefono = findViewById<TextView>(R.id.perfilTelefono)

        perfilCorreo.text = correoUsuario
        perfilNombre.text = nombreUsuario
        perfilApellido.text = apellidoUsuario
        perfilTelefono.text = telefonoUsuario
    }
}