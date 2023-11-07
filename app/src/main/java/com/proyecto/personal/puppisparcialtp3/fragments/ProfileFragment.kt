package com.proyecto.personal.puppisparcialtp3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.proyecto.personal.puppisparcialtp3.R
import com.bumptech.glide.Glide
import com.proyecto.personal.puppisparcialtp3.databinding.FragmentFavoritesBinding
import com.proyecto.personal.puppisparcialtp3.databinding.FragmentProfileBinding


class ProfileFragment : Fragment(){

    lateinit var image: ImageView
    lateinit var buttonUpload: Button
    lateinit var editTextUrl: EditText
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        image = binding.imgViewProfile
        Glide.with(root)
            .load("https://images.dog.ceo/breeds/terrier-toy/n02087046_7037.jpg")
            .into(image)

        editTextUrl = binding.editTextUploadURLProfile
        buttonUpload = binding.btnProfileUpload
        buttonUpload.setOnClickListener {
            if (editTextUrl.visibility == View.VISIBLE) {
                editTextUrl.visibility = View.GONE
            } else {
                editTextUrl.visibility = View.VISIBLE
            }

            val imageUrl = editTextUrl.text.toString()
            Glide.with(this).clear(image)
            // Check if the URL is not empty
            if (imageUrl.isNotEmpty()) {
                // Use Glide to load the image into the ImageView

                Glide.with(this)
                    .load(imageUrl)
                    .into(image)
            } else {
                Glide.with(this)
                    .load(R.drawable.ic_error_loading)
                    .into(image)
            }
        }
        return root

    }


     override fun onStart() {
        super.onStart()
         var toolbar = binding.toolbarProfile
         toolbar.setOnClickListener{
             findNavController().popBackStack()
         }

    }

}