package com.proyecto.personal.puppisparcialtp3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyecto.personal.puppisparcialtp3.adapters.PetListAdapter
import com.proyecto.personal.puppisparcialtp3.databinding.FragmentAdoptedBinding
import com.proyecto.personal.puppisparcialtp3.domain.Pet
import com.proyecto.personal.puppisparcialtp3.listeners.OnFavoritesClickListener
import com.proyecto.personal.puppisparcialtp3.listeners.OnViewItemClickedListener
import com.proyecto.personal.puppisparcialtp3.viewModels.AdoptedViewModel
import com.proyecto.personal.puppisparcialtp3.viewModels.SharedViewModel

class AdoptedFragment : Fragment(), OnViewItemClickedListener, OnFavoritesClickListener {

    private var _binding: FragmentAdoptedBinding? = null
    private lateinit var petListAdapter: PetListAdapter
    private val sharedViewModel : SharedViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val adoptedViewModel =
            ViewModelProvider(this).get(AdoptedViewModel::class.java)

        _binding = FragmentAdoptedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initRecyclerView()
        return root
    }

    override fun onStart() {
        super.onStart()

        sharedViewModel.pets.observe(this, Observer {
            if (it != null) {
                binding.tvAdopted.visibility = View.GONE
                val listaAdoptados: List<Pet> = it.filter { it.isAdopted }
                petListAdapter.updateData(listaAdoptados)
                if (listaAdoptados.isEmpty()){
                    binding.tvAdopted.visibility = View.VISIBLE
                }


            }

        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initRecyclerView(){

        val recycleView = binding.rvAdopted
        recycleView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        petListAdapter = PetListAdapter(mutableListOf(), this, this)
        recycleView.adapter = petListAdapter

    }
    override fun onFavoritesClick(pet: Pet) {
        sharedViewModel.onFavoritesClick(pet)
    }
    override fun onViewItemDetail(pet: Pet) {
        Toast.makeText(context,"todavia no implementado!!!", Toast.LENGTH_SHORT).show()
    }


}