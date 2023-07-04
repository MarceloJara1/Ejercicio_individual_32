package com.example.ejercicioindividual32.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ejercicioindividual32.Model.Item
import com.example.ejercicioindividual32.R
import com.example.ejercicioindividual32.ViewModel.ItemViewModel
import com.example.ejercicioindividual32.databinding.FragmentSecondBinding
import com.google.android.material.snackbar.Snackbar

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private lateinit var itemViewModel: ItemViewModel
    private lateinit var tvPrecioTotal: TextView

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        val picker = binding.picker
        val minValue = 1
        val maxValue = 50
        val default = 1
        picker.minValue = minValue
        picker.maxValue = maxValue
        picker.value = default

        binding.btnGuardar.setOnClickListener {
            //findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            val cantidad = picker.value
            saveData(cantidad)
        }
        tvPrecioTotal = binding.totalPrice

        itemViewModel.allItem.observe(viewLifecycleOwner, Observer<List<Item>>{item->
            calcularTotal(item)
        })
    }

    private fun saveData(cantidad: Int){

        val item = binding.textFieldItem.editText?.text.toString()
        val precio = binding.textFieldPrecio.editText?.text.toString()

        if (!item.isNullOrEmpty() && !precio.isNullOrEmpty()){
            try {
                val precioInt = precio.toInt()
                val item = Item(
                    0, item, precioInt, cantidad
                )
                itemViewModel.insertItem(item)


                binding.textFieldItem.editText?.text?.clear()
                binding.textFieldPrecio.editText?.text?.clear()
                Snackbar.make(binding.root, "Datos guardados correctamente...", Snackbar.LENGTH_SHORT).show()
            }catch (e: NumberFormatException){
                Snackbar.make(binding.root, "El formato del precio es incorrecto.", Snackbar.LENGTH_SHORT).show()
            }

        }else{
            Snackbar.make(binding.root, "Por favor ingrese datos...", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun calcularTotal(item: List<Item>){
        var precioTotal = 0
        for (item in item) {
            precioTotal += item.itemPrice * item.itemCant
        }
        tvPrecioTotal.text = precioTotal.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}