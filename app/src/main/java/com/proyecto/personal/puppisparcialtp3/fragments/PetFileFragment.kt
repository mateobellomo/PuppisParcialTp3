package com.proyecto.personal.puppisparcialtp3.fragments

import android.content.Intent
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.proyecto.personal.puppisparcialtp3.R
import com.proyecto.personal.puppisparcialtp3.adapters.ViewPagerAdapter
import com.proyecto.personal.puppisparcialtp3.databinding.FragmentFavoritesBinding
import com.proyecto.personal.puppisparcialtp3.databinding.FragmentPetFileBinding
import com.proyecto.personal.puppisparcialtp3.domain.Pet
import com.proyecto.personal.puppisparcialtp3.helpers.SharedPref
import com.proyecto.personal.puppisparcialtp3.viewModels.SharedViewModel


class PetFileFragment : Fragment() {

    private var _binding: FragmentPetFileBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var currentImagePosition = 0
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
        val arrowLeft = binding.arrowLeft
        val arrowRight = binding.arrowRight
        val viewpager = binding.vpItemCard

        val petId = PetFileFragmentArgs.fromBundle(requireArguments()).id
        val pet = sharedViewModel.findPet(petId)
        if (pet != null) {
            setPetValues(pet)

            arrowRight.setOnClickListener {
                if (currentImagePosition < (pet.photo?.size ?: 1) - 1) {
                    currentImagePosition++
                    viewpager.currentItem = currentImagePosition
                }
            }
            arrowLeft.setOnClickListener {
                if (currentImagePosition > 0) {
                    currentImagePosition--
                    viewpager.currentItem = currentImagePosition
                }
            }
        }
        val backBtn = binding.btnBackDetails
        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        val callBtn = binding.btnPhoneDetails
        callBtn.setOnClickListener {
            val btnPhoneDetails = binding.btnPhoneDetails

            val phoneNumber = binding.btnPhoneDetails.tag
            if (pet != null) {
                btnPhoneDetails.tag = pet.ownerNumber
            }
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber))
            startActivity(intent)

        }
        val ownerPhoto = binding.ivOwnerPetFile
        val photoUrl = pet?.ownerImageUrl
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.ic_placeholder)
        requestOptions.error(R.drawable.ic_error)
        Glide.with(this)
            .load(photoUrl)
            .apply(requestOptions)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .thumbnail(0.5f)
            .into(ownerPhoto)

        val adoptBtn = binding.btnAdoptDetails
        adoptBtn.setOnClickListener {
            val builder =
                AlertDialog.Builder(ContextThemeWrapper(requireContext(), R.style.AlertDialogTheme))
            if (pet?.isAdopted == false) {
                builder.setTitle("Adoption process")
                builder.setMessage("Are you sure you want to adopt this pet?")

                builder.setPositiveButton("Yes") { dialog, which ->
                    val notificationText = "¡Thanks for adopting!"
                    Toast.makeText(view.context, notificationText, Toast.LENGTH_SHORT).show()
                    adoptBtn.text = "Adopted"

                    pet.isAdopted = true
                    pet.ownerName = SharedPref.read(SharedPref.NAME, "Unknown")
                    pet.ownerImageUrl = SharedPref.read(SharedPref.IMAGE_URL, "")
                }
                builder.setNegativeButton("Cancel") { dialog, which ->
                    dialog.dismiss()
                }
                builder.show()
            }
        }
    }


    private fun setPetValues(pet: Pet) {
        val viewPager = binding.vpItemCard
        val name = binding.namePetFile
        val age = binding.agePetFile
        val location = binding.locationPetFile
        val sex = binding.sexPetFile
        val weight = binding.weightPetFile
        val owner = binding.tvOwnerName
        val description = binding.tvDescription
        val adapter = pet.photo?.let { ViewPagerAdapter(it) }
        viewPager.adapter = adapter
        val btnPhoneDetails = binding.btnPhoneDetails
        val realAge = pet.age
        age.text = if (realAge >= 1) "$realAge years old" else "less than a year old"
        btnPhoneDetails.tag = pet.ownerNumber
        name.text = pet.name
        location.text = pet.location.location
        sex.text = pet.gender.toString()
        weight.text = pet.weight
        owner.text = pet.ownerName
        description.text = pet.description
    }
}


