package com.example.sivapp.network.response

import com.example.sivapp.models.DatosVentas

data class VentaResponse(
    val codigo: String,
    val mensaje: String,
    val resultado: List<DatosVentas>

)
