package com.proyecto.personal.puppisparcialtp3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.proyecto.personal.puppisparcialtp3.R
import com.proyecto.personal.puppisparcialtp3.databinding.FragmentFavoritesBinding
import com.proyecto.personal.puppisparcialtp3.databinding.FragmentPetFileBinding
import com.proyecto.personal.puppisparcialtp3.domain.Pet
import com.proyecto.personal.puppisparcialtp3.viewModels.SharedViewModel


class PetFileFragment : Fragment() {

    private var _binding: FragmentPetFileBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel : SharedViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPetFileBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val petId = PetFileFragmentArgs.fromBundle(requireArguments()).id
        val pet = sharedViewModel.findPet(petId)
        if (pet != null) {
            setPetValues(pet)
        }

        val backBtn = binding.backBtn
        backBtn.setOnClickListener{
            findNavController().popBackStack()
        }
    }


    fun setPetValues(pet: Pet){
        val img = binding.ivPetRecord
        val name = binding.namePetFile
        val location = binding.locationPetFile
        val sex = binding.sexPetFile
        val weight = binding.weightPetFile
        val owner = binding.tvOwnerName
        val description= binding.tvDescription

        Glide.with(this)
            .load(pet.photo)
            .into(img)
        name.text=pet.name
        location.text = pet.location.toString()
        sex.text = pet.gender.toString()
        weight.text = pet.weight
        owner.text = pet.ownerName
        description.text = pet.description




    }


    }


