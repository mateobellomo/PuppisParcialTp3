package com.proyecto.personal.puppisparcialtp3.fragments

import android.os.Bundle
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
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.proyecto.personal.puppisparcialtp3.R
import com.proyecto.personal.puppisparcialtp3.adapters.PetListAdapter

import com.proyecto.personal.puppisparcialtp3.databinding.FragmentHomeBinding
import com.proyecto.personal.puppisparcialtp3.entities.Pet
import com.proyecto.personal.puppisparcialtp3.listeners.OnViewItemClickedListener
import com.proyecto.personal.puppisparcialtp3.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import hilt_aggregated_deps._com_proyecto_personal_puppisparcialtp3_fragments_HomeFragment_GeneratedInjector

@AndroidEntryPoint
class HomeFragment : Fragment(), OnViewItemClickedListener {

    lateinit var vista: View

    lateinit var recPets : RecyclerView

    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var petListAdapter: PetListAdapter

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
      //  homeViewModel.onCreate()
        vista = inflater.inflate(R.layout.fragment_home, container, false)

        recPets = vista.findViewById(R.id.fragmentHomeRecPets)

        val boton = vista.findViewById<Button>(R.id.boton_prueba_agregar)
        boton.setOnClickListener {
            val action = HomeFragmentDirections.actionFragment2ToFragment3(el_parametro)


            vista.findNavController().navigate(action)

        }

        return vista
    }


    override fun onStart() {
        super.onStart()

        val emptyList = mutableListOf<Pet>()

        homeViewModel.createPet()


        homeViewModel.pets.observe(this, Observer {
            petListAdapter.updateData(it)
        })


        requireActivity()

        recPets.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        petListAdapter = PetListAdapter(emptyList, this)

        recPets.layoutManager = linearLayoutManager
        recPets.adapter = petListAdapter



    }
    override fun onViewItemDetail(pet: Pet) {
     //   val action = Fragment3Directions.actionFragment3ToViewItem(contacto)
      //  this.findNavController().navigate(action)
        //findNavController().navigate(action)
        //Snackbar.make(vista,pet.name, Snackbar.LENGTH_SHORT).show()
    }
}