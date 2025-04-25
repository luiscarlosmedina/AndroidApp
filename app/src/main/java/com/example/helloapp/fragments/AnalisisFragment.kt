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

class AnalisisFragment : Fragment(R.layout.fragment_analisis) {

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var total: TextView
    private lateinit var costoInvitado: TextView
    private lateinit var costoCategoria: TextView
    private lateinit var costoVarios: TextView

    private lateinit var editEvento: EditText
    private lateinit var editInvitados: EditText
    private lateinit var editCosto: EditText
    private lateinit var editHoras: EditText
    private lateinit var editServicio: EditText
    private lateinit var editLocal: EditText
    private lateinit var editVarios: EditText

    private lateinit var botonCalcular: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE)

        // TextViews
        total = view.findViewById(R.id.viewtotal)
        costoInvitado = view.findViewById(R.id.viewCostoInvitado)
        costoCategoria = view.findViewById(R.id.viewCostoCategoria)
        costoVarios = view.findViewById(R.id.viewCostoVarios)

        // EditTexts
        editEvento = view.findViewById(R.id.etTpEvento)
        editInvitados = view.findViewById(R.id.etNumInvitados)
        editCosto = view.findViewById(R.id.etCostoUnitario)
        editHoras = view.findViewById(R.id.etHoras)
        editServicio = view.findViewById(R.id.etServicio)
        editLocal = view.findViewById(R.id.etCostoLocal)
        editVarios = view.findViewById(R.id.etCostoVarios)

        botonCalcular = view.findViewById(R.id.btnCalculo)

        botonCalcular.setOnClickListener {
            calcularPresupuesto()
        }
    }

    private fun calcularPresupuesto() {
        val invitados = editInvitados.text.toString().toIntOrNull() ?: 0
        val costoUnitario = editCosto.text.toString().toDoubleOrNull() ?: 0.0
        val horas = editHoras.text.toString().toIntOrNull() ?: 0
        val servicio = editServicio.text.toString().toDoubleOrNull() ?: 0.0
        val local = editLocal.text.toString().toDoubleOrNull() ?: 0.0
        val varios = editVarios.text.toString().toDoubleOrNull() ?: 0.0

        val factorDuracion = when {
            horas <= 2 -> 1.0
            horas <= 4 -> 1.5
            else -> 2.0
        }

        val presupuestoCatering = invitados * costoUnitario * factorDuracion
        val presupuestoAdicional = servicio + local + varios
        val costoTotal = presupuestoCatering + presupuestoAdicional

        costoInvitado.text = "Costo por invitado: $%.2f".format(costoUnitario)
        costoCategoria.text = "Presupuesto para categoria: $%.2f".format(presupuestoCatering)
        costoVarios.text = "Otros costos (servicio, local, varios): $%.2f".format(presupuestoAdicional)
        total.text = "Costo total estimado: $%.2f".format(costoTotal)

        // Hacer visibles los resultados
        costoInvitado.visibility = View.VISIBLE
        costoCategoria.visibility = View.VISIBLE
        costoVarios.visibility = View.VISIBLE
        total.visibility = View.VISIBLE
    }
}
