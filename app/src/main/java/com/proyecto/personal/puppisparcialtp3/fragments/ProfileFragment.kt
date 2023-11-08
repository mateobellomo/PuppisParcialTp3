package com.proyecto.personal.puppisparcialtp3.fragments

import android.graphics.drawable.Drawable
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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.proyecto.personal.puppisparcialtp3.R
import com.proyecto.personal.puppisparcialtp3.databinding.FragmentProfileBinding
import com.proyecto.personal.puppisparcialtp3.helpers.SharedPref
import com.proyecto.personal.puppisparcialtp3.viewModels.ProfileViewModel


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var imageView: ImageView
    private lateinit var buttonUpload: Button
    private lateinit var editTextUrl: EditText
    private lateinit var nameTextView: TextView

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        this.setUpViews()
        SharedPref.addImageURLChangeListener { newImageUrl ->
            newImageUrl?.let {
                updateImage(
                    imageUrl = it
                )
            }
        }
        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        SharedPref.removeImageURLChangeListener { }
    }

    private fun setUpViews() {
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
        val imageUrl: String? = SharedPref.read(SharedPref.IMAGE_URL, null)
        imageUrl?.let { updateImage(it) }
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
            Toast.makeText(activity, "Profile picture was successfully updated", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun updateImage(imageUrl: String) {
        imageView = binding.imgViewProfile
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.ic_placeholder)
        requestOptions.error(R.drawable.ic_error)
        Glide.with(this)
            .load(imageUrl)
            .apply(requestOptions)
            .diskCacheStrategy(DiskCacheStrategy.NONE) // Disable disk caching
            .skipMemoryCache(true) // Skip memory cache
            .thumbnail(0.5f)
            .circleCrop()
            .into(imageView)
    }
}