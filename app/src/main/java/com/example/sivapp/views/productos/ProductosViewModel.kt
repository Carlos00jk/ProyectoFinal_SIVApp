package com.example.sivapp.views.productos

import android.app.Activity
import android.service.controls.actions.ControlAction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sivapp.models.Producto
import com.example.sivapp.network.RetrofitClient
import com.example.sivapp.network.response.ProductoResponse
import com.example.sivapp.network.response.ProveedorResponse
import com.example.sivapp.utils.Permisos
import com.example.sivapp.views.main.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class ProductosViewModel : ViewModel() {

    private var _listaProductos = MutableLiveData<MutableList<Producto>>()
    val listaProductos: LiveData<MutableList<Producto>> get() = _listaProductos

    private var _listaNomProovedores = MutableLiveData<MutableList<String>>()
    val listaNomProovedores: LiveData<MutableList<String>> get() = _listaNomProovedores

    private val _mensaje = MutableLiveData<String>()
    val mensaje: LiveData<String> get() = _mensaje

    private lateinit var response: Response<ProductoResponse>

    fun getProductos() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.getProductos()
            withContext(Dispatchers.Main) {
                if (response.body()!!.codigo == "200") {
                    _listaProductos.value = response.body()!!.resultado
                } else {
                    _mensaje.value = response.body()!!.mensaje
                }
            }
        }
    }
    fun getNomProveedores() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.getProveedores()
            withContext(Dispatchers.Main) {
                if (response.body()!!.codigo == "200") {
                    val aux = arrayListOf<String>()
                    response.body()!!.resultado.forEach {
                        aux.add(it.nombreProveedor)
                    }
                    _listaNomProovedores.value = aux
                } else {
                    _mensaje.value = response.body()!!.mensaje
                }
            }
        }
    }
    fun filtrarListaProductos (producto: String) {
        val listaFiltrada: MutableList<Producto> = mutableListOf()
        for (prod in listaProductos.value!!) {
            if (prod.codProducto.contains(producto) || prod.nomProducto.contains(producto)) {
                listaFiltrada.add(prod)
            }
        }
        _listaProductos.value = listaFiltrada
    }
    fun validarCampos(
        accion: String,
        codigo: String,
        nomProducto: String,
        descripcion: String,
        nomProveedor: String,
        precio: String,
        almacen: String
    ) {
        if (
            codigo.isNullOrEmpty()
            || nomProducto.isNullOrEmpty()
            || descripcion.isNullOrEmpty()
            || nomProveedor.isNullOrEmpty()
            || precio.isNullOrEmpty()
            || almacen.isNullOrEmpty()
        ) {
            _mensaje.value = "Todos los campos deben ser llenados"
        } else {
            val prod = Producto(
                almacen.toInt(),
                codigo,
                descripcion,
                nomProducto,
                nomProveedor,
                precio.toDouble()
            )

            productoAddUpdate(accion, prod)


        }
    }
     private fun productoAddUpdate(accion: String, prod: Producto){
         viewModelScope.launch(Dispatchers.IO) {
             if(accion == "add"){
                 response = RetrofitClient.webService.addProducto(prod)
             }else{
                 response = RetrofitClient.webService.updateProducto(prod.codProducto, prod)
             }
             withContext(Dispatchers.Main){
                 if (response.body()!!.codigo == "200") {
                     _mensaje.value = response.body()!!.mensaje
                     getProductos()
                 }else{
                     _mensaje.value = response.body()!!.mensaje

                 }                 }
         }
     }
    fun checkCamaraPermiso(activity: Activity): Boolean{
        val isPermiso = Permisos().checkCamaraPermiso(activity)
        return isPermiso
    }

}
