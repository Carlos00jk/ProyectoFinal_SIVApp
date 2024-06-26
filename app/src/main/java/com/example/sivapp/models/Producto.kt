package com.example.sivapp.models

data class Producto(
    val almacen: Int,
    val codProducto: String,
    val descripcion: String,
    val nomProducto: String,
    val nomProveedor: String,
    val precio: Double

)
data class ProductoVenta(
    val almacen: Int,
    val codProducto: String,
    val descripcion: String,
    val nomProducto: String,
    val nomProveedor: String,
    val precio: Double,
    var cantidad: Int
)
