package com.example.helloapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.helloapp.R

class PerfilFragment : Fragment(R.layout.fragment_perfil) {

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var perfilNombre: TextView
    private lateinit var perfilEdad: TextView
    private lateinit var perfilCorreo: TextView
    private lateinit var perfilPrograma: TextView
    private lateinit var perfilSemestre: TextView

    private lateinit var editNombre: EditText
    private lateinit var editEdad: EditText
    private lateinit var editCorreo: EditText
    private lateinit var editPrograma: EditText
    private lateinit var editSemestre: EditText

    private lateinit var botonEditar: Button

    private var editando = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE)

        // TextViews
        perfilNombre = view.findViewById(R.id.viewPerfilNombre)
        perfilEdad = view.findViewById(R.id.viewPerfilEdad)
        perfilCorreo = view.findViewById(R.id.viewPerfilCorreo)
        perfilPrograma = view.findViewById(R.id.viewPerfilPrograma)
        perfilSemestre = view.findViewById(R.id.viewPerfilSemestre)

        // EditTexts
        editNombre = view.findViewById(R.id.editPerfilNombre)
        editEdad = view.findViewById(R.id.editPerfilEdad)
        editCorreo = view.findViewById(R.id.editPerfilCorreo)
        editPrograma = view.findViewById(R.id.editPerfilPrograma)
        editSemestre = view.findViewById(R.id.editPerfilSemestre)

        botonEditar = view.findViewById(R.id.perfilBtnEditar)

        // Cargar datos actuales
        val nombre = sharedPreferences.getString("nombre", "")
        val edad = sharedPreferences.getString("edad", "")
        val correo = sharedPreferences.getString("correo", "")
        val programa = sharedPreferences.getString("programa", "")
        val semestre = sharedPreferences.getString("semestre", "")

        perfilNombre.text = nombre
        perfilEdad.text = edad
        perfilCorreo.text = correo
        perfilPrograma.text = programa
        perfilSemestre.text = semestre

        botonEditar.setOnClickListener {
            if (!editando) {
                toggleCampos(true)
                editNombre.setText(nombre)
                editEdad.setText(edad)
                editCorreo.setText(correo)
                editPrograma.setText(programa)
                editSemestre.setText(semestre)
                botonEditar.text = "Guardar"
                editando = true
            } else {
                val editor = sharedPreferences.edit()
                editor.putString("nombre", editNombre.text.toString())
                editor.putString("edad", editEdad.text.toString())
                editor.putString("correo", editCorreo.text.toString())
                editor.putString("programa", editPrograma.text.toString())
                editor.putString("semestre", editSemestre.text.toString())
                editor.apply()

                perfilNombre.text = editNombre.text
                perfilEdad.text = editEdad.text
                perfilCorreo.text = editCorreo.text
                perfilPrograma.text = editPrograma.text
                perfilSemestre.text = editSemestre.text

                toggleCampos(false)
                botonEditar.text = "Editar"
                editando = false
            }
        }
    }

    private fun toggleCampos(visible: Boolean) {
        val vis = if (visible) View.GONE else View.VISIBLE
        val editVis = if (visible) View.VISIBLE else View.GONE

        perfilNombre.visibility = vis
        perfilEdad.visibility = vis
        perfilCorreo.visibility = vis
        perfilPrograma.visibility = vis
        perfilSemestre.visibility = vis

        editNombre.visibility = editVis
        editEdad.visibility = editVis
        editCorreo.visibility = editVis
        editPrograma.visibility = editVis
        editSemestre.visibility = editVis
    }
}
