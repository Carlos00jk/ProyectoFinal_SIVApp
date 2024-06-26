package com.example.sivapp.views.productos

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sivapp.R
import com.example.sivapp.databinding.ActivityMainBinding
import com.example.sivapp.databinding.FragmentProductosBinding
import com.example.sivapp.models.Producto


class ProductosFragment : Fragment(R.layout.fragment_productos), ProductoAdapter.OnItemClicked {

    private lateinit var binding: FragmentProductosBinding
    private lateinit var adapter: ProductoAdapter

    private lateinit var viewModel: ProductosViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductosBinding.bind(view)

        viewModel = ViewModelProvider(this)[ProductosViewModel::class.java]

        setupRecyclerView()
        viewModel.getProductos()

        viewModel.listaProductos.observe(requireActivity()){
            adapter.listaProductos = it as ArrayList<Producto>
            adapter.notifyDataSetChanged()
        }

        binding.ibtnAdd.setOnClickListener{
            alertDialogAddUpdate()
        }
    }
    private  fun setupRecyclerView(){
        binding.rvProductos.layoutManager = LinearLayoutManager(requireActivity())
        adapter = ProductoAdapter(arrayListOf(), this)
        binding.rvProductos.adapter = adapter
    }


    private fun alertDialogAddUpdate(){
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater

        val vista = inflater.inflate(R.layout.alert_dialog_producto, null)
        builder.setView(vista)
         builder.setCancelable(false)

        val ibtnEscaner = vista.findViewById(R.id.ibtnEscaner) as ImageButton
        val etNomProducto = vista.findViewById(R.id.etNomProducto) as EditText
        val etDescripcion = vista.findViewById(R.id.etDescripcion) as EditText
        val spiProveedores = vista.findViewById(R.id.spiProveedor) as Spinner
        val tvNomProveedor = vista.findViewById(R.id.tvNomProveedor) as TextView
        val etPrecio = vista.findViewById(R.id.etPrecio) as EditText
        val etAlmacen = vista.findViewById(R.id.etAlmacen) as EditText

        viewModel.getNomProveedores()
        viewModel.listaNomProovedores.observe(requireActivity()){
            spiProveedores.adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                it
            )
        }

        builder.setPositiveButton("ACEPTAR"){ _, _ ->

        }
        builder.setNegativeButton("CANCELAR") { _, _ ->

        }
        builder.show()
    }
    override fun editarProducto(prod: Producto) {
        TODO("Not yet implemented")
    }
}