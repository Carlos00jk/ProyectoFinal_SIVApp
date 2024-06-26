package com.example.sivapp.network.response

import com.example.sivapp.models.Producto

data class ProductoResponse(
    val codigo: String,
    val mensaje: String,
    val resultado: MutableList<Producto>
)
