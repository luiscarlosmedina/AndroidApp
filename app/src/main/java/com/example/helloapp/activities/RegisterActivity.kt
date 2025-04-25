package com.example.helloapp.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.helloapp.R

class RegisterActivity : AppCompatActivity() {

    private lateinit var editTextNombres: EditText
    private lateinit var editTextEdad: EditText
    private lateinit var editTextCorreo: EditText
    private lateinit var editTextPrograma: EditText
    private lateinit var editTextSemestre: EditText
    private lateinit var editTextContrasena: EditText
    private lateinit var editTextConfirmarContrasena: EditText
    private lateinit var buttonRegistro: Button

    private lateinit var sharedPreferences: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        Log.d("RegisterActivity", "onCreate: inicializando el activity de registro")

        editTextNombres = findViewById(R.id.etNombres)
        editTextEdad = findViewById(R.id.etEdad)
        editTextCorreo = findViewById(R.id.etCorreo)
        editTextPrograma = findViewById(R.id.etPrograma)
        editTextSemestre = findViewById(R.id.etSemestre)
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
            }

        }


    }
    private fun validarCampos(): Boolean {
        val nombres = editTextNombres.text.toString().trim()
        val edad = editTextEdad.text.toString().trim()
        val correo = editTextCorreo.text.toString().trim()
        val programa = editTextPrograma.text.toString().trim()
        val semestre = editTextSemestre.text.toString().trim()
        val contrasena = editTextContrasena.text.toString().trim()
        val confirmarContrasena = editTextConfirmarContrasena.text.toString().trim()
        if (nombres.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa tu nombre", Toast.LENGTH_SHORT).show()
            return false
        }
        if( edad.isEmpty()){
            Toast.makeText(this, "Por favor, ingresa tu edad", Toast.LENGTH_SHORT).show()
            return false
        }
        if(correo.isEmpty()){
            Toast.makeText(this, "Por favor, ingresa tu correo", Toast.LENGTH_SHORT).show()
            return false
        }
        if(programa.isEmpty()){
            Toast.makeText(this, "Por favor, ingresa tu programa", Toast.LENGTH_SHORT).show()
            return false
        }
        if(semestre.isEmpty()){
            Toast.makeText(this, "Por favor, ingresa tu semestre", Toast.LENGTH_SHORT).show()
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
        editor.putString("edad", editTextEdad.text.toString().trim())
        editor.putString("correo", editTextCorreo.text.toString().trim())
        editor.putString("semestre", editTextSemestre.text.toString().trim())
        editor.putString("programa", editTextPrograma.text.toString().trim())
        editor.putString("contrasena", editTextContrasena.text.toString().trim())
        editor.apply()
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
    }

}
