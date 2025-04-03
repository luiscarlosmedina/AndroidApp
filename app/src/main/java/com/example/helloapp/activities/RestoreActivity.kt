package com.example.helloapp.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.helloapp.R

class RestoreActivity : AppCompatActivity() {
    private lateinit var editTextCorreo: EditText
    private lateinit var buttonEnviar: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restore_pass)

        buttonEnviar = findViewById(R.id.recuperarBtnEnviar)
        buttonEnviar.setOnClickListener {
            if(validarCorreo()){
                //redireccionar al login
                Toast.makeText(this, "Se envió un correo de recuperación", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()

                limpiarCampos()
            }
        }

    }
    private fun validarCorreo(): Boolean {
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)
        editTextCorreo = findViewById(R.id.recuperarCorreo)
        val correo = editTextCorreo.text.toString().trim()
        if (correo.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa tu correo", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Toast.makeText(this, "Por favor, ingresa un correo electrónico válido", Toast.LENGTH_SHORT).show()
            return false
        }
        val correoGuardado = sharedPreferences.getString("correo", "")
        if (correo != correoGuardado) {
            Toast.makeText(this, "Correo electrónico no registrado", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
    private fun limpiarCampos() {
        editTextCorreo.text.clear()
    }
}