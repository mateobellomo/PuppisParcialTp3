package com.proyecto.personal.puppisparcialtp3.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.proyecto.personal.puppisparcialtp3.R
import com.proyecto.personal.puppisparcialtp3.databinding.FragmentPostFormBinding
import com.proyecto.personal.puppisparcialtp3.utils.Gender
import com.proyecto.personal.puppisparcialtp3.utils.Location
import com.proyecto.personal.puppisparcialtp3.domain.Pet
import com.proyecto.personal.puppisparcialtp3.helpers.SharedPref
import com.proyecto.personal.puppisparcialtp3.viewModels.PostFormViewModel
import com.proyecto.personal.puppisparcialtp3.viewModels.SharedViewModel


class PostFormFragment : Fragment() {

    private lateinit var namePetInput : EditText
    private lateinit var genderSpinner : Spinner
    private lateinit var ageSpinner : Spinner
    private lateinit var weightPetInput : EditText
    private lateinit var grKgSpinner : Spinner
    private lateinit var breedSpinner : Spinner
    private lateinit var subBreedSpinner : Spinner
    private lateinit var locationSpinner : Spinner
    private lateinit var ownerPetInput : EditText
    private lateinit var ownerPhoneInput : EditText
    private lateinit var descriptionInput : EditText
    private lateinit var urlPhotoInput : EditText

    private var errorMsg: TextView? = null
    private val sharedViewModel : SharedViewModel by activityViewModels()
    private var _binding: FragmentPostFormBinding? = null
    private val binding get() = _binding!!
    private var imagePhoto : List<String> = emptyList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPostFormBinding.inflate(inflater, container, false)
        val root: View = binding.root

        namePetInput = binding.editTextFragmentPostFormName
        genderSpinner = binding.GenderSpinner
        ageSpinner = binding.ageSpinner
        weightPetInput = binding.editTextFragmentPostFormWeight
        grKgSpinner = binding.GrKgSpinner
        breedSpinner = binding.BreedSpinner
        subBreedSpinner = binding.SubBreedSpinner
        locationSpinner = binding.LocationSpinner
        ownerPetInput = binding.editTextFragmentPostFormOwner
        ownerPhoneInput = binding.editTextFragmentPostFormPhone
        descriptionInput = binding.editNotes
        urlPhotoInput = binding.editTextFragmentPostAddPhoto//P


        errorMsg = binding.errorMsg
        errorMsg?.visibility = View.INVISIBLE
         val saveBtn = binding.buttonFragmentPostFormSave
            saveBtn.setOnClickListener {
                this.savePost()
        }
         val cancelBtn = binding.buttonFragmentPostFormCancel
            cancelBtn.setOnClickListener {
                this.cancel()
        }

        fillSpinnerValues()
            sharedViewModel.breedListLiveData.observe(viewLifecycleOwner, Observer { it ->
                if (it != null) {
                    updateSpinners(it)
                }
            })
        return root
    }


    override fun onStart() {
        super.onStart()
        sharedViewModel.images.observe(viewLifecycleOwner, Observer { images ->
            imagePhoto = images
        })

        val btnSaveUrlPhoto = binding.root.findViewById<ImageButton>(R.id.btnSaveUrlPhoto) //P
        btnSaveUrlPhoto.setOnClickListener {
            val urlString = urlPhotoInput.text.toString() // P
            imagePhoto = imagePhoto.plus(urlString)//P
            urlPhotoInput.text.clear()
        }

     }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun cancel(){
        findNavController().popBackStack()

    }

    private fun fillSpinnerValues() {
        activity?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.quantity,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                this.ageSpinner?.adapter = adapter
            }

            ArrayAdapter.createFromResource(
                it,
                R.array.gender_values,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                this.genderSpinner?.adapter = adapter
            }

            ArrayAdapter.createFromResource(
                it,
                R.array.grKr_values,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                this.grKgSpinner?.adapter = adapter
            }

            ArrayAdapter.createFromResource(
                it,
                R.array.locations_values,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                this.locationSpinner?.adapter = adapter
            }
        }
    }

      private fun savePost() {

          val namePet: String = namePetInput.text.toString()
          val genderString: String = genderSpinner.selectedItem.toString()
          val weightPet: String = weightPetInput.text.toString()
          val grKg: String = grKgSpinner.selectedItem.toString()
          val breed: String = breedSpinner.selectedItem.toString()
          val selectedItem = subBreedSpinner.selectedItem
          val subBreed: String = selectedItem?.toString() ?: ""
          val locationString: String = locationSpinner.selectedItem.toString()
          val ownerPet: String = ownerPetInput.text.toString()
          val ownerPhone: String = ownerPhoneInput.text.toString()
          val description: String = descriptionInput.text.toString()
            if (namePet.isEmpty()) {
                errorMsg?.visibility = View.VISIBLE
                errorMsg?.text =
                    "The Name field is required"
                Handler().postDelayed({
                    errorMsg?.visibility = View.INVISIBLE
                }, 3000)

            }else if (weightPet.isNullOrBlank()){
                errorMsg?.visibility = View.VISIBLE
                errorMsg?.text = when {
                    weightPet.isNullOrBlank()-> "The Weight field is required"
                    weightPetInput?.text.isNullOrBlank() -> "The Weight field is required"
                    else -> {
                        ""
                    }
                }
                Handler().postDelayed({
                    errorMsg?.visibility = View.INVISIBLE
                }, 2000) // Ocultar el mensaje después de 2 segundos (2000 ms)
            }else if (ownerPhone.isNullOrBlank()) {
                errorMsg?.visibility = View.VISIBLE
                errorMsg?.text = when {
                    ownerPhone.isNullOrBlank()-> "The Phone field is required"
                    ownerPhoneInput?.text.isNullOrBlank() -> "The Phone field is required"
                    else -> {
                        ""
                    }
                }
                Handler().postDelayed({
                    errorMsg?.visibility = View.INVISIBLE
                }, 2000) // Ocultar el mensaje después de 2 segundos (2000 ms)
            } else {
                val selectedAgeString = ageSpinner.selectedItem.toString()
                val ageValue = selectedAgeString.split(" ")[0].toIntOrNull() ?: 0

                val weight: String = "$weightPet $grKg"
                val gender: Gender = Gender.fromString(genderString)
                val location: Location = Location.fromString(locationString)


                val newPet = Pet(Pet.nextId(), //genera id automatico
                    name = namePet,
                    age = ageValue,
                    breed = breed,
                    subBreed = subBreed,
                    gender = gender,
                    description = description,
                    weight = weight,
                    location = location,
                    ownerName = ownerPet,
                    photo = imagePhoto,
                    isAdopted = false,
                    isFavorite = false,
                    ownerNumber = ownerPhone
                )

                val builder = AlertDialog.Builder(ContextThemeWrapper(requireContext(), R.style.AlertDialogTheme))

                builder.setTitle("Adoption process")
                builder.setMessage("¿Are you sure you want to post this pet?")


                builder.setPositiveButton("Yes") { dialog, which ->
                    Log.d("pet creado", newPet.toString())
                    Log.d("pet creado view model", sharedViewModel.pets.toString())

                    sharedViewModel.addPet(newPet)
                    cleanInputs()
                }

                builder.setNegativeButton("Cancel") { dialog, which ->

                }
                builder.show()
            }
        }

    fun cleanInputs(){
        namePetInput.setText("")
        genderSpinner.setSelection(0, false)
        ageSpinner.setSelection(0, false)
        weightPetInput.text = null
        grKgSpinner.setSelection(0, false)
        breedSpinner.setSelection(0, false)
        subBreedSpinner.setSelection(0, false)
        locationSpinner.setSelection(0, false)
        ownerPetInput.setText("")
        descriptionInput.setText("")
        ownerPhoneInput.setText("")

    }
    private fun updateSpinners(list: List<Pair<String, List<String>>>) {

            val breedsList = list.map { it.first }
            val breedAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, breedsList)

        breedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            breedSpinner.adapter   = breedAdapter

            val subBreedAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, mutableListOf<String>())
            subBreedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            subBreedSpinner.adapter = subBreedAdapter

            breedSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val selectedBreed = breedsList[position]
                    val subBreeds = list[position].second

                    if (subBreeds.isNotEmpty()) {
                        subBreedAdapter.clear()
                        subBreedAdapter.addAll(subBreeds)
                        subBreedAdapter.notifyDataSetChanged()
                        subBreedSpinner.visibility = View.VISIBLE
                    } else {
                        subBreedSpinner.visibility = View.GONE
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }
}