package com.example.ejercicioindividual32.View


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejercicioindividual32.Model.Item
import com.example.ejercicioindividual32.R
import com.example.ejercicioindividual32.ViewModel.ItemViewModel
import com.example.ejercicioindividual32.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var itemViewModel: ItemViewModel
    private lateinit var itemAdapter: ItemAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        itemAdapter = ItemAdapter()
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = itemAdapter
        }

        itemViewModel = ViewModelProvider(requireActivity()).get(ItemViewModel::class.java)
        itemViewModel.allItem.observe(viewLifecycleOwner,Observer<List<Item>> { items ->
            itemAdapter.setItems(items)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}