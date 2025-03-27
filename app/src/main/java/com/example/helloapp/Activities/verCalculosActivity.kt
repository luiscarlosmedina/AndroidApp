package com.example.helloapp.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.helloapp.R

class verCalculosActivity : AppCompatActivity() {
    private lateinit var buttonRegisterLogin: Button
    private lateinit var buttonRestorePass: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vercalculos)

        buttonRegisterLogin = findViewById(R.id.btnCalculadora)
        buttonRegisterLogin.setOnClickListener {
            val intent = Intent(this, CalculadoraActivity::class.java)
            startActivity(intent)
        }
        buttonRestorePass = findViewById(R.id.btncargaPerfil)
        buttonRestorePass.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

    }
}