package com.proyecto.personal.puppisparcialtp3.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyecto.personal.puppisparcialtp3.R
import com.proyecto.personal.puppisparcialtp3.activities.HomeActivity
import com.proyecto.personal.puppisparcialtp3.dataBase.PetsDAO
import com.proyecto.personal.puppisparcialtp3.dataBase.appDatabase
import com.proyecto.personal.puppisparcialtp3.databinding.FragmentPostFormBinding
import com.proyecto.personal.puppisparcialtp3.entities.Gender
import com.proyecto.personal.puppisparcialtp3.entities.Location
import com.proyecto.personal.puppisparcialtp3.entities.Pet
import com.proyecto.personal.puppisparcialtp3.listeners.OnViewItemClickedListener
import com.proyecto.personal.puppisparcialtp3.viewModels.PostFormViewModel




class PostFormFragment : Fragment() {

    private var namePetInput: EditText? = null
    private var genderSpinner: Spinner? = null
    private var quantitySpinner: Spinner? = null
    private var daysMonthsYearsSpinner: Spinner? = null
    private var weightPetInput: EditText? = null
    private var grKgSpinner: Spinner? = null
    private var breedSpinner: Spinner? = null
    private var subBreedSpinner: Spinner? = null
    private var locationSpinner: Spinner? = null
    private var ownerPetInput: EditText? = null
    private var descriptionInput: EditText? = null
    private var photosInput: EditText? = null
    private var saveBtn: Button? = null
    private var cancelBtn: Button? = null
    private var addPhotoBtn: Button? = null
    private var deletePhotoBtn: Button? = null
    private var errorMsg: TextView? = null


    private val PostFormViewModel: PostFormViewModel by viewModels()
    private var _binding: FragmentPostFormBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: appDatabase
    private lateinit var petsDao: PetsDAO



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
        quantitySpinner = binding.quantitySpinner
        daysMonthsYearsSpinner = binding.DaysMonthsYearsSpinner
        weightPetInput = binding.editTextFragmentPostFormWeight
        grKgSpinner = binding.GrKgSpinner
        breedSpinner = binding.BreedSpinner
        subBreedSpinner = binding.SubBreedSpinner
        locationSpinner = binding.LocationSpinner
        ownerPetInput = binding.editTextFragmentPostFormOwner
        descriptionInput = binding.editNotes
        photosInput = binding.editTextUrlPhoto
        errorMsg = binding.errorMsg
        errorMsg?.visibility = View.INVISIBLE
        saveBtn = binding.buttonFragmentPostFormSave
        saveBtn?.setOnClickListener {
            this.savePost()
        }
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
        return root
    }

    private fun deleteUrlPhoto() {
        PostFormViewModel.removeImage(photosInput?.text.toString())
    }

    private fun addUrlPhoto() {
        PostFormViewModel.saveImage(photosInput?.text.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun cancel(){
        val intent = Intent(context, HomeActivity::class.java)
        context?.startActivity(intent)

    }

    private fun fillSpinnerValues() {
        activity?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.quantity,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                this.quantitySpinner?.adapter = adapter
            }

            ArrayAdapter.createFromResource(
                it,
                R.array.daysMonthsYear_values,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                this.daysMonthsYearsSpinner?.adapter = adapter
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
                R.array.breeds_value,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                this.breedSpinner?.adapter = adapter
            }

            ArrayAdapter.createFromResource(
                it,
                R.array.subbreeds_value,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                this.subBreedSpinner?.adapter = adapter
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
            //val userName = SharedPref.read(SharedPref.ID, UserSingleton.userId!!)
            val namePet: String = namePetInput?.text.toString()
            val genderString: String = genderSpinner?.selectedItem.toString()
            val quantity: Int = quantitySpinner?.selectedItem.toString().toInt()
            val dayMonthYear: String = daysMonthsYearsSpinner?.selectedItem.toString()
            val weightPet: Double = weightPetInput?.text.toString().toDouble()
            val grKg: String = daysMonthsYearsSpinner?.selectedItem.toString()
            val breed: String = breedSpinner?.selectedItem.toString()
            val subBreed: String = subBreedSpinner?.selectedItem.toString()
            val locationString: String = locationSpinner?.selectedItem.toString()
            val ownerPet: String = ownerPetInput?.text.toString()
            val description: String = descriptionInput?.text.toString()
            val imagesPet: String = photosInput?.text.toString()

            if (namePet.isEmpty()) {
                errorMsg?.visibility = View.VISIBLE
                errorMsg?.text =
                    "The Name field is required"
                Handler().postDelayed({
                    errorMsg?.visibility = View.INVISIBLE
                }, 3000)
            } else if (weightPet == null || weightPet <= 0.0 || weightPetInput?.text.isNullOrBlank()) {
                errorMsg?.visibility = View.VISIBLE
                errorMsg?.text = when {
                    weightPet == null -> "The Weight field is required"
                    weightPet <= 0.0 -> "The weight must be greater than 0"
                    weightPetInput?.text.isNullOrBlank() -> "The Weight field is required"
                    else -> {
                        ""
                    }
                }
                Handler().postDelayed({
                    errorMsg?.visibility = View.INVISIBLE
                }, 2000) // Ocultar el mensaje después de 2 segundos (2000 ms)
            } else if (imagesPet.isEmpty()) {
                errorMsg?.visibility = View.VISIBLE
                errorMsg?.text =
                    "The Photos field is required"
                Handler().postDelayed({
                    errorMsg?.visibility = View.INVISIBLE
                }, 3000)

            } else {
                //val ownerPet =
                val agePet: String = "$quantity $dayMonthYear"
                val weight: String = "$weightPet $grKg"
                val gender: Gender = Gender.fromString(genderString)
                val location: Location = Location.fromString(locationString)


                val newPet = Pet(
                    name = namePet,
                    age = agePet,
                    breed = breed,
                    subBreed = subBreed,
                    gender = gender,
                    description = description,
                    weight = weight,
                    location = location,
                    ownerName = ownerPet,
                    isAdopted = false,
                    isFavorite = false
                )

                Log.e("NamePet!!", namePet)
                Log.e("AgePet!!", agePet)
                Log.e("breedPet!!", breed)


                database = appDatabase.getAppDataBase(binding.root.context)!!
                petsDao = database?.petsDAO()

                petsDao.insertPet(newPet)


                val intent = Intent(context, HomeActivity::class.java)
                context?.startActivity(intent)
            }
        }

}
