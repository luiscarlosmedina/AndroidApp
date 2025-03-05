package com.example.helloapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var editTextNombres: EditText
    private lateinit var editTextApellidos: EditText
    private lateinit var editTextCorreo: EditText
    private lateinit var editTextTelefono: EditText
    private lateinit var editTextContrasena: EditText
    private lateinit var editTextConfirmarContrasena: EditText
    private lateinit var buttonRegistro: Button

    private lateinit var sharedPreferences: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        Log.d("RegisterActivity", "onCreate: inicializando el activity de registro")

        editTextNombres = findViewById(R.id.etNombres)
        editTextApellidos = findViewById(R.id.etApellidos)
        editTextCorreo = findViewById(R.id.etCorreo)
        editTextTelefono = findViewById(R.id.etTelefono)
        editTextContrasena = findViewById(R.id.etContrasena)
        editTextConfirmarContrasena = findViewById(R.id.etRepetirContrasena)
        buttonRegistro = findViewById(R.id.btnRegistro)

        //archivo de almacenamiento local
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        buttonRegistro.setOnClickListener {
            //validar campos
            if(validarCampos()){
                //guardar datos
                guardarDatos()

                //redireccionar al login
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()

                limpiarCampos()
            }

        }


    }
    private fun validarCampos(): Boolean {
        val nombres = editTextNombres.text.toString().trim()
        val apellidos = editTextApellidos.text.toString().trim()
        val correo = editTextCorreo.text.toString().trim()
        val telefono = editTextTelefono.text.toString().trim()
        val contrasena = editTextContrasena.text.toString().trim()
        val confirmarContrasena = editTextConfirmarContrasena.text.toString().trim()
        if (nombres.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa tu nombre", Toast.LENGTH_SHORT).show()
            return false
        }
        if( apellidos.isEmpty()){
            Toast.makeText(this, "Por favor, ingresa tu apellido", Toast.LENGTH_SHORT).show()
            return false
        }
        if(correo.isEmpty()){
            Toast.makeText(this, "Por favor, ingresa tu correo", Toast.LENGTH_SHORT).show()
            return false
        }
        if(telefono.isEmpty()){
            Toast.makeText(this, "Por favor, ingresa tu telefono", Toast.LENGTH_SHORT).show()
            return false
        }
        if(contrasena.isEmpty()){
            Toast.makeText(this, "Por favor, ingresa tu contraseña", Toast.LENGTH_SHORT).show()
            return false
        }
        if(confirmarContrasena.isEmpty()) {
            Toast.makeText(this, "Por favor, confirma tu contraseña", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!android.util.Patterns.PHONE.matcher(telefono).matches()) {
            Toast.makeText(this, "Por favor, ingresa un número de teléfono válido", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Toast.makeText(this, "Por favor, ingresa un correo electrónico válido", Toast.LENGTH_SHORT).show()
            return false
        }
        if (contrasena != confirmarContrasena) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return false
        }
        return true

    }

    private fun guardarDatos() {
        val editor = sharedPreferences.edit()
        editor.putString("nombres", editTextNombres.text.toString().trim())
        editor.putString("apellidos", editTextApellidos.text.toString().trim())
        editor.putString("correo", editTextCorreo.text.toString().trim())
        editor.putString("telefono", editTextTelefono.text.toString().trim())
        editor.putString("contrasena", editTextContrasena.text.toString().trim())
        editor.apply()
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
    }

    private fun limpiarCampos() {
        editTextNombres.text.clear()
        editTextApellidos.text.clear()
        editTextCorreo.text.clear()
        editTextTelefono.text.clear()
        editTextContrasena.text.clear()
        editTextConfirmarContrasena.text.clear()
    }

}
