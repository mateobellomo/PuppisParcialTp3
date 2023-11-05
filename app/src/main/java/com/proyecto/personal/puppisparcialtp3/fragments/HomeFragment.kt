package com.proyecto.personal.puppisparcialtp3.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.proyecto.personal.puppisparcialtp3.R
import com.proyecto.personal.puppisparcialtp3.adapters.PetListAdapter
import com.proyecto.personal.puppisparcialtp3.dataBase.PetsDAO
import com.proyecto.personal.puppisparcialtp3.dataBase.appDatabase
import com.proyecto.personal.puppisparcialtp3.databinding.FragmentAdoptedBinding

import com.proyecto.personal.puppisparcialtp3.databinding.FragmentHomeBinding
import com.proyecto.personal.puppisparcialtp3.entities.Gender
import com.proyecto.personal.puppisparcialtp3.entities.Location
import com.proyecto.personal.puppisparcialtp3.entities.Pet
import com.proyecto.personal.puppisparcialtp3.listeners.OnViewItemClickedListener
import com.proyecto.personal.puppisparcialtp3.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), OnViewItemClickedListener {

    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var petListAdapter: PetListAdapter

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var database: appDatabase

    private lateinit var petsDao: PetsDAO

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.botonPruebaAgregar.setOnClickListener {
            homeViewModel.newPet()
        }
        return root
    }


    override fun onStart() {
        super.onStart()

        database = appDatabase.getAppDataBase(binding.root.context)!!

        petsDao = database?.petsDAO()

        val numPets = petsDao.getPetsCount()

        // Funcionaba_todo bien hasta que borre las BD y ya no me guarda en el mismo , lo hace en el "petsDB-val"

        if (numPets == 0) {
            petsDao.insertPet(Pet("Mateo", 5, "beagle", "N/A", Gender.MALE, "Brown", 10.0, Location.BUENOS_AIRES, "Agustin", false, isFavorite = false));
            petsDao.insertPet(Pet("Paola", 6, "chow", "N/A", Gender.FEMALE, "White", 30.0, Location.CORDOBA, "Carlos", false, isFavorite = false));
            petsDao.insertPet(Pet("Yanina", 2, "labrador", "N/A", Gender.MALE, "Black", 20.0, Location.SALTA, "Agustin", false, isFavorite = false));
            petsDao.insertPet(Pet("Camila", 8, "akita", "N/A", Gender.FEMALE, "White", 15.0, Location.MENDOZA, "Daniel", false, isFavorite = false));
            petsDao.insertPet(Pet("Agustin", 5, "beagle", "N/A", Gender.MALE, "Brown", 10.0, Location.BUENOS_AIRES, "Pedro", false, isFavorite = false));
            petsDao.insertPet(Pet("Francisco", 6, "chow", "N/A", Gender.FEMALE, "White", 30.0, Location.CORDOBA, "Mariella", false, isFavorite = false));
            petsDao.insertPet(Pet("Javier", 2, "labrador", "N/A", Gender.MALE, "Black", 20.0, Location.SALTA, "Agustin", false, isFavorite = false));
            petsDao.insertPet(Pet("Micaela", 8, "akita", "N/A", Gender.FEMALE, "White", 15.0, Location.MENDOZA, "Juan", false, isFavorite = false));
            petsDao.insertPet(Pet("Jimena", 2, "labrador", "N/A", Gender.MALE, "Black", 20.0, Location.SALTA, "Fernando", false, isFavorite = false));
            petsDao.insertPet(Pet("Ariel", 8, "akita", "N/A", Gender.FEMALE, "Black", 15.0, Location.MENDOZA, "Agustin", false, isFavorite = false));
        }


        homeViewModel.pets.observe(this) {
            petListAdapter.updateData(it)
        }

        requireActivity()

        val emptyList = mutableListOf<Pet>()
        binding.fragmentHomeRecPets.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        petListAdapter = PetListAdapter(emptyList, this)

        binding.fragmentHomeRecPets.layoutManager = linearLayoutManager
        binding.fragmentHomeRecPets.adapter = petListAdapter
    }
    override fun onViewItemDetail(pet: Pet) {
     //   val action = Fragment3Directions.actionFragment3ToViewItem(contacto)
      //  this.findNavController().navigate(action)
        //findNavController().navigate(action)
        //Snackbar.make(vista,pet.name, Snackbar.LENGTH_SHORT).show()
    }

    override fun addFavorite(pet: Pet) {
        homeViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}