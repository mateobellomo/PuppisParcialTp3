package com.proyecto.personal.puppisparcialtp3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.proyecto.personal.puppisparcialtp3.R
import com.bumptech.glide.Glide



class ProfileFragment : Fragment(){
    lateinit var vista: View
    lateinit var image: ImageView

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

        return vista
    }


    // override fun onStart() {
        //super.onStart()

    //}

}