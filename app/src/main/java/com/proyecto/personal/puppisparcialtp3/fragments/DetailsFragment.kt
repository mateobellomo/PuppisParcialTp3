package com.proyecto.personal.puppisparcialtp3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.proyecto.personal.puppisparcialtp3.R
import com.proyecto.personal.puppisparcialtp3.adapters.PetPhotoAdapter
import com.proyecto.personal.puppisparcialtp3.viewModels.PetViewModel

class DetailsFragment : Fragment() {
    lateinit var vista: View
    private val petViewModel: PetViewModel by viewModels()
    override fun onCreate(
        savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val petImagesViewPager: ViewPager2 = vista.findViewById(R.id.petImagesViewPager)
        val prevButton: Button = vista.findViewById(R.id.btnLeft)
        val nextButton: Button = vista.findViewById(R.id.btnRight)
        vista = inflater.inflate(R.layout.fragment_details, container, false)
        val selectedPet = petViewModel.selectedPet.value
        if (selectedPet != null) {
            val imageUrls = listOf("url1", "url2", "url3")
            val adapter = PetPhotoAdapter(imageUrls)
            petImagesViewPager.adapter = adapter

            prevButton.setOnClickListener {
                if (petImagesViewPager.currentItem > 0) {
                    petImagesViewPager.currentItem -= 1
                }
            }

            nextButton.setOnClickListener {
                if (petImagesViewPager.currentItem < imageUrls.size - 1) {
                    petImagesViewPager.currentItem += 1
                }
            }
        } else {

        }


        return vista
    }


}