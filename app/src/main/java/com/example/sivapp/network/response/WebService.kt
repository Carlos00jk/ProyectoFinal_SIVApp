package com.example.sivapp.network.response

import com.example.sivapp.models.Producto
import com.example.sivapp.models.Proveedor
import com.example.sivapp.models.VentasSend
import com.example.sivapp.network.response.ProductoResponse
import com.example.sivapp.network.response.ProveedorResponse
import com.example.sivapp.network.response.VentaResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {
    //PROVEEDORES
    @GET("/proveedores")
    suspend fun getProveedores(): Response<ProveedorResponse>

    @POST("/proveedores/add")
    suspend fun addProveedor(
        @Body prov: Proveedor
    ): Response<ProveedorResponse>

    @PUT("/proveedores/update/{nomProveedor}")
    suspend fun updateProveedor(
        @Path("nomProveedor") nomProveedor: String,
        @Body prov: Proveedor
    ): Response<ProveedorResponse>
    //END PROVEEDORES

    //PRODUCTOS
    @GET("/productos")
    suspend fun getProductos(): Response<ProductoResponse>

    @GET("/productos/{codProducto}")
    suspend fun addProductos(
        @Path("codProducto") codProducto: String
    ): Response<ProductoResponse>

    @POST("/productos/add")
    suspend fun addProducto(
        @Body prod: Producto
    ): Response<ProductoResponse>

    @PUT("/productos/update/{codProducto}")
    suspend fun updateProducto(
        @Path("codProducto") codProducto: String,
        @Body prod: Producto
    ): Response<ProductoResponse>
    //END PRODUCTOS
    //VENTAS
    @GET("/ventas/periodo")
    suspend fun getVentasPeriodo(
        @Query("fechaInicio") fechaInicio: String,
        @Query("fechaFinal") fechaFinal: String
    ): Response<VentaResponse>

    @POST("/ventas/add")
    suspend fun addVenta(
        @Body datosSend: VentasSend

    ):Response<VentaResponse>
    //END VENTAS
}