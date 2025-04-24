package com.example.helloapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.helloapp.R

class PerfilFragment : Fragment(R.layout.fragment_perfil) {

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var perfilNombre: TextView
    private lateinit var perfilApellido: TextView
    private lateinit var perfilCorreo: TextView
    private lateinit var perfilTelefono: TextView

    private lateinit var editNombre: EditText
    private lateinit var editApellido: EditText
    private lateinit var editCorreo: EditText
    private lateinit var editTelefono: EditText

    private lateinit var botonEditar: Button

    private var editando = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE)

        perfilNombre = view.findViewById(R.id.perfilNombre)
        perfilApellido = view.findViewById(R.id.perfilApellidos)
        perfilCorreo = view.findViewById(R.id.perfilCorreo)
        perfilTelefono = view.findViewById(R.id.perfilTelefono)

        editNombre = view.findViewById(R.id.editPerfilNombre)
        editApellido = view.findViewById(R.id.editPerfilApellidos)
        editCorreo = view.findViewById(R.id.editPerfilCorreo)
        editTelefono = view.findViewById(R.id.editPerfilTelefono)

        botonEditar = view.findViewById(R.id.perfilBtnEditar)

        // Cargar datos actuales
        val nombre = sharedPreferences.getString("nombres", "")
        val apellido = sharedPreferences.getString("apellidos", "")
        val correo = sharedPreferences.getString("correo", "")
        val telefono = sharedPreferences.getString("telefono", "")

        perfilNombre.text = nombre
        perfilApellido.text = apellido
        perfilCorreo.text = correo
        perfilTelefono.text = telefono

        // Botón para alternar edición
        botonEditar.setOnClickListener {
            if (!editando) {
                // Mostrar campos para editar
                toggleCampos(visible = true)

                // Llenar los campos con el texto actual
                editNombre.setText(nombre)
                editApellido.setText(apellido)
                editCorreo.setText(correo)
                editTelefono.setText(telefono)

                botonEditar.text = "Guardar"
                editando = true
            } else {
                // Guardar cambios
                val editor = sharedPreferences.edit()
                editor.putString("nombres", editNombre.text.toString())
                editor.putString("apellidos", editApellido.text.toString())
                editor.putString("correo", editCorreo.text.toString())
                editor.putString("telefono", editTelefono.text.toString())
                editor.apply()

                // Actualizar los TextView
                perfilNombre.text = editNombre.text
                perfilApellido.text = editApellido.text
                perfilCorreo.text = editCorreo.text
                perfilTelefono.text = editTelefono.text

                // Volver a vista normal
                toggleCampos(visible = false)
                botonEditar.text = "Editar"
                editando = false
            }
        }
    }

    private fun toggleCampos(visible: Boolean) {
        val vis = if (visible) View.GONE else View.VISIBLE
        val editVis = if (visible) View.VISIBLE else View.GONE

        perfilNombre.visibility = vis
        perfilApellido.visibility = vis
        perfilCorreo.visibility = vis
        perfilTelefono.visibility = vis

        editNombre.visibility = editVis
        editApellido.visibility = editVis
        editCorreo.visibility = editVis
        editTelefono.visibility = editVis
    }
}

