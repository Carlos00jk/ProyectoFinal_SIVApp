package com.example.sivapp.network.response

import com.example.sivapp.models.Proveedor


data class ProveedorResponse(
    val codigo: String,
    val mensaje: String,
    val resultado: MutableList<Proveedor>
)
