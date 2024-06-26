package com.example.sivapp.views.productos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.sivapp.R
import com.example.sivapp.models.Producto

class ProductoAdapter (
    var listaProductos: ArrayList<Producto>,
    val onClick: OnItemClicked
): RecyclerView.Adapter<ProductoAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_producto, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoAdapter.ViewHolder, position: Int) {
        val producto = listaProductos[position]
        holder.tvIdProducto.text = producto.codProducto
        holder.tvNomProducto.text = "Producto: ${producto.nomProducto}"
        holder.tvDescripcion.text = "Descripción: ${producto.descripcion}"
        holder.tvNomProveedor.text = "Proveedor: ${producto.nomProveedor}"
        holder.tvAlmacen.text = "${producto.almacen}"
        holder.tvPrecio.text = "$${producto.precio}"

        holder.cvProducto.setOnClickListener{
            onClick?.editarProducto(producto)
        }

    }

    override fun getItemCount(): Int {
        return listaProductos.size
    }
    class ViewHolder(itemView: View ): RecyclerView.ViewHolder(itemView) {

        val cvProducto = itemView.findViewById(R.id.cvProducto) as CardView
        val tvIdProducto = itemView.findViewById(R.id.tvIdProducto) as TextView
        val tvNomProducto = itemView.findViewById(R.id.tvNomProducto) as TextView
        val tvDescripcion = itemView.findViewById(R.id.tvDescripcion) as TextView
        val tvNomProveedor = itemView.findViewById(R.id.tvNomProveedor) as TextView
        val tvAlmacen = itemView.findViewById(R.id.tvAlmacen) as TextView
        val tvPrecio = itemView.findViewById(R.id.tvPrecio) as TextView
    }
 interface OnItemClicked{
     fun editarProducto(prod: Producto)
 }

}