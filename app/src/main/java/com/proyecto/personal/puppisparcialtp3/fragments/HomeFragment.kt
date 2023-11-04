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
import androidx.lifecycle.ViewModelProvider
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

@AndroidEntryPoint
class HomeFragment : Fragment(), OnViewItemClickedListener {

    lateinit var vista: View

    lateinit var recPets : RecyclerView

    var pets : MutableList<Pet> = ArrayList()

    val lista : MutableList<String> = ArrayList()

    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var petListAdapter: PetListAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
      //  homeViewModel.onCreate()
        vista = inflater.inflate(R.layout.fragment_home, container, false)

        recPets = vista.findViewById(R.id.fragmentHomeRecPets)



        return vista
    }


    override fun onStart() {
        super.onStart()


        lista.add("https://images.dog.ceo/breeds/terrier-toy/n02087046_7037.jpg")
        lista.add("https://images.dog.ceo/breeds/husky/n02110185_12498.jpg")

        pets.add(Pet("Mateo", 10, "boxer", "shiba","Male","Nada","30","BS AS","Agustin", lista))
        pets.add(Pet("Agustin", 10, "beagle", "shiba","Male","Nada","30","BS AS","Agustin", lista))
        pets.add(Pet("Paola", 10, "chow", "shiba","Male","Nada","30","BS AS","Agustin", lista))
        pets.add(Pet("Yanina", 10, "labrador", "shiba","Male","Nada","30","BS AS","Agustin", lista))
        pets.add(Pet("Camila", 10, "pug", "shiba","Male","Nada","30","BS AS","Agustin", lista))

        requireActivity()

        recPets.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        petListAdapter = PetListAdapter(pets, this)

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