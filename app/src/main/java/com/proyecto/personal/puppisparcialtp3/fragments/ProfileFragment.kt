package com.proyecto.personal.puppisparcialtp3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.proyecto.personal.puppisparcialtp3.R
import com.bumptech.glide.Glide
import com.proyecto.personal.puppisparcialtp3.helpers.SharedPref


class ProfileFragment : Fragment(){
    private lateinit var view: View
    private lateinit var image: ImageView
    private lateinit var buttonUpload: Button
    private lateinit var editTextUrl: EditText
    private lateinit var nameTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false)
        image = view.findViewById<ImageView>(R.id.imgViewProfile)
        Glide.with(view)
            .load("https://images.dog.ceo/breeds/terrier-toy/n02087046_7037.jpg")
            .into(image)

        editTextUrl = view.findViewById(R.id.editTextUploadURLProfile)
        buttonUpload = view.findViewById(R.id.btnProfileUpload)
        nameTextView = view.findViewById(R.id.textView)
        val name = SharedPref.read(SharedPref.NAME, null)
        nameTextView.text = name
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
        return view
    }


    // override fun onStart() {
        //super.onStart()

    //}

}