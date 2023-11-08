package com.proyecto.personal.puppisparcialtp3.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.proyecto.personal.puppisparcialtp3.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.proyecto.personal.puppisparcialtp3.databinding.FragmentProfileBinding
import com.proyecto.personal.puppisparcialtp3.viewModels.ProfileViewModel


class ProfileFragment : Fragment(){

    private lateinit var binding: FragmentProfileBinding
    private lateinit var image: ImageView
    private lateinit var buttonUpload: Button
    private lateinit var editTextUrl: EditText
    private lateinit var nameTextView: TextView

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        viewModel.onCreate()
        this.setUpViews()
        viewModel._imageUrl.observe(viewLifecycleOwner, Observer {
            Glide.with(this)
                .load(it)
                .circleCrop()
                .placeholder(R.drawable.upload_image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(image)
        })
        return binding.root
    }

    private fun setUpViews() {
        image = binding.imgViewProfile
        nameTextView = binding.textView
        nameTextView.isVisible = false
        viewModel.getName().let {
            nameTextView.text = it
            nameTextView.isVisible = true
        }
        editTextUrl = binding.editTextUploadURLProfile
        buttonUpload = binding.btnProfileUpload
        buttonUpload.setOnClickListener {
            this.updateImageUrl(editTextUrl.text.toString())
        }
    }

    private fun updateImageUrl(url: String) {
        if (url.isNullOrEmpty()) {
            editTextUrl.error = "Mandatory field"
            Handler().postDelayed({
                editTextUrl.error = null
            }, 3000)
        } else {
            viewModel.updateImageUrl(url)
            editTextUrl.setText("")
            Toast.makeText(activity, "Profile picture was successfully updated", Toast.LENGTH_SHORT).show()
        }
    }
}