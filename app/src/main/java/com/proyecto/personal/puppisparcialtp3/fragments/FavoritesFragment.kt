package com.proyecto.personal.puppisparcialtp3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyecto.personal.puppisparcialtp3.adapters.PetListAdapter
import com.proyecto.personal.puppisparcialtp3.viewModels.FavoritesViewModel
import com.proyecto.personal.puppisparcialtp3.databinding.FragmentFavoritesBinding
import com.proyecto.personal.puppisparcialtp3.entities.Pet
import com.proyecto.personal.puppisparcialtp3.listeners.OnViewItemClickedListener
import com.proyecto.personal.puppisparcialtp3.viewModels.HomeViewModel

class FavoritesFragment : Fragment(), OnViewItemClickedListener {

    private var _binding: FragmentFavoritesBinding? = null

    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var petListAdapter: PetListAdapter



    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val favoritesViewModel =
            ViewModelProvider(this).get(FavoritesViewModel::class.java)

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val emptyList: MutableList<Pet> = mutableListOf()

        requireActivity()

        _binding!!.fragmentFavoritesRecPets.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        //petListAdapter = PetListAdapter(HomeViewModel.getPets(), this) Falta ver esto como hcaerlo

        _binding!!.fragmentFavoritesRecPets.layoutManager = linearLayoutManager
        _binding!!.fragmentFavoritesRecPets.adapter = petListAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewItemDetail(pet: Pet) {
        TODO("Not yet implemented")
    }
}