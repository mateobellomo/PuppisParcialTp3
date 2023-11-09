package com.proyecto.personal.puppisparcialtp3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyecto.personal.puppisparcialtp3.adapters.PetListAdapter
import com.proyecto.personal.puppisparcialtp3.databinding.FragmentFavoritesBinding
import com.proyecto.personal.puppisparcialtp3.domain.Pet
import com.proyecto.personal.puppisparcialtp3.listeners.OnFavoritesClickListener
import com.proyecto.personal.puppisparcialtp3.listeners.OnViewItemClickedListener
import com.proyecto.personal.puppisparcialtp3.viewModels.SharedViewModel

class FavoritesFragment : Fragment(), OnViewItemClickedListener, OnFavoritesClickListener {

    private var _binding: FragmentFavoritesBinding? = null
    private lateinit var petListAdapter: PetListAdapter
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initRecyclerView()

        return root
    }

    override fun onStart() {
        super.onStart()

        sharedViewModel.pets.observe(this, Observer {
            if (it != null) {
                binding.tvfavorites.visibility = View.GONE
                val favoritesList: List<Pet> = it.filter { it.isFavorite }
                petListAdapter.updateData(favoritesList)
                if (favoritesList.isEmpty()) {
                    binding.tvfavorites.visibility = View.VISIBLE
                }
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerView() {
        val recycleView = binding.rvFavorites
        recycleView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        petListAdapter = PetListAdapter(mutableListOf(), this, this)
        recycleView.adapter = petListAdapter

    }

    override fun onFavoritesClick(pet: Pet) {
        sharedViewModel.onFavoritesClick(pet)
    }

    override fun onViewItemDetail(pet: Pet) {
        val action = FavoritesFragmentDirections.actionNavigationFavoritesToPetFileFragment(pet.id)
        findNavController().navigate(action)
    }
}