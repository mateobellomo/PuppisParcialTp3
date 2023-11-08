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
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.proyecto.personal.puppisparcialtp3.R
import com.proyecto.personal.puppisparcialtp3.databinding.FragmentPostFormBinding
import com.proyecto.personal.puppisparcialtp3.utils.Gender
import com.proyecto.personal.puppisparcialtp3.utils.Location
import com.proyecto.personal.puppisparcialtp3.domain.Pet
import com.proyecto.personal.puppisparcialtp3.viewModels.PostFormViewModel
import com.proyecto.personal.puppisparcialtp3.viewModels.SharedViewModel
import kotlinx.coroutines.launch


class PostFormFragment : Fragment() {

    private lateinit var namePetInput: EditText
    private lateinit var genderSpinner: Spinner
    private lateinit var ageSpinner: Spinner
    private lateinit var weightPetInput: EditText
    private lateinit var grKgSpinner: Spinner
    private lateinit var breedSpinner: Spinner
    private lateinit var subBreedSpinner: Spinner
    private lateinit var locationSpinner: Spinner
    private lateinit var ownerPetInput: EditText
    private lateinit var descriptionInput: EditText
    private lateinit var phonePetInput: EditText



    private var addPhotoBtn: Button? = null
    private var deletePhotoBtn: Button? = null
    private var errorMsg: TextView? = null

    private val PostFormViewModel: PostFormViewModel by viewModels()
    private val sharedViewModel : SharedViewModel by activityViewModels()
    private var _binding: FragmentPostFormBinding? = null
    private val binding get() = _binding!!
    private var imagePhoto :String = ""



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
        descriptionInput = binding.editNotes
        phonePetInput = binding.editTextFragmentPostFormPhone

        errorMsg = binding.errorMsg
        errorMsg?.visibility = View.INVISIBLE
        val saveBtn = binding.buttonFragmentPostFormSave
         saveBtn.setOnClickListener {
            this.savePost()
        }
        val cancelBtn = binding.buttonFragmentPostFormCancel
        cancelBtn?.setOnClickListener {
            this.cancel()
        }
        addPhotoBtn?.setOnClickListener {
            this.addUrlPhoto()
        }
        deletePhotoBtn?.setOnClickListener {
            this.deleteUrlPhoto()
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

        binding.editTextUrlPhoto.setOnClickListener{

//          val image =  sharedViewModel.getImage()
//            Glide.with(this)
//                .load("URL_DE_LA_IMAGEN") // Reemplaza con la URL de la imagen que deseas cargar
//                .into(object : CustomTarget<Drawable>() {
//                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
//                        // Establece la imagen como fondo del EditText
//                        binding.editTextUrlPhoto.background = resource
//                    }
//
//                    override fun onLoadCleared(placeholder: Drawable?) {
//                        // Código para manejar la carga cancelada si es necesario
//                    }
//                })


            lifecycleScope.launch {
                imagePhoto = sharedViewModel.getImage()
                Log.e("imagen", imagePhoto)
                Glide.with(this@PostFormFragment)
                    .load(imagePhoto)
                    .into(binding.editTextUrlPhoto) // Asignar la imagen al EditText
            }
            Log.e("imagen 2", imagePhoto)
        }
    }

    private fun deleteUrlPhoto() {
        PostFormViewModel.removeImage(imagePhoto)
    }

    private fun addUrlPhoto() {
        PostFormViewModel.saveImage(imagePhoto)
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
            val description: String = descriptionInput.text.toString()
            val phonePet : String = phonePetInput.text.toString()
            val imagesPet: String = imagePhoto

            if (namePet.isEmpty()) {
                errorMsg?.visibility = View.VISIBLE
                errorMsg?.text =
                    "The Name field is required"
                Handler().postDelayed({
                    errorMsg?.visibility = View.INVISIBLE
                }, 3000)
            } else if (weightPet.isNullOrBlank()) {
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
            } else if (phonePet.isNullOrBlank()) {
                errorMsg?.visibility = View.VISIBLE
                errorMsg?.text = when {
                    phonePet.isNullOrBlank()-> "The Phone field is required"
                    phonePetInput?.text.isNullOrBlank() -> "The Phone field is required"
                    else -> {
                        ""
                    }
                }
                Handler().postDelayed({
                    errorMsg?.visibility = View.INVISIBLE
                }, 2000) // Ocultar el mensaje después de 2 segundos (2000 ms)
            } else if (!imagesPet.isEmpty()) {
                errorMsg?.visibility = View.VISIBLE
                errorMsg?.text =
                    "The Photos field is required"
                Handler().postDelayed({
                    errorMsg?.visibility = View.INVISIBLE
                }, 3000)

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
                    ownerPhone = phonePet,
                    photo = "",
                    isAdopted = false,
                    isFavorite = false
                )

                val builder = AlertDialog.Builder(ContextThemeWrapper(requireContext(), R.style.AlertDialogTheme))

                builder.setTitle("Proceso de Adopcion")
                builder.setMessage("¿Estás seguro que deseas publicar esta mascota?")

                builder.setPositiveButton("Si") { dialog, which ->
                    Log.d("pet creado", newPet.toString())
                    Log.d("pet creado view model", sharedViewModel.pets.toString())

                    sharedViewModel.addPet(newPet)
                    cleanInputs()

                }

                builder.setNegativeButton("Cancelar") { dialog, which ->

                }

                builder.show()




            }
        }
    fun cleanInputs(){
        namePetInput?.setText("")
        genderSpinner?.setSelection(0, false)
        ageSpinner?.setSelection(0, false)
        weightPetInput?.text = null
        grKgSpinner?.setSelection(0, false)
        breedSpinner?.setSelection(0, false)
        subBreedSpinner?.setSelection(0, false)
        locationSpinner?.setSelection(0, false)
        ownerPetInput?.setText("")
        phonePetInput?.setText("")
        descriptionInput?.setText("")


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
