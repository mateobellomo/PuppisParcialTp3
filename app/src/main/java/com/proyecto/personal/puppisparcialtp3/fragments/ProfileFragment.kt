package com.proyecto.personal.puppisparcialtp3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.proyecto.personal.puppisparcialtp3.R
import com.bumptech.glide.Glide



class ProfileFragment : Fragment(){
    lateinit var vista: View
    lateinit var image: ImageView
    lateinit var buttonUpload: Button
    lateinit var editTextUrl: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_profile, container, false)
        image = vista.findViewById<ImageView>(R.id.imgViewProfile)
        Glide.with(vista)
            .load("https://images.dog.ceo/breeds/terrier-toy/n02087046_7037.jpg")
            .into(image)

        editTextUrl = vista.findViewById(R.id.editTextUploadURLProfile)
        buttonUpload = vista.findViewById(R.id.btnProfileUpload)
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
        return vista

    }


    // override fun onStart() {
        //super.onStart()

    //}

}