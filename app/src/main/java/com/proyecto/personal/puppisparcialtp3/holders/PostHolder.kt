package com.proyecto.personal.puppisparcialtp3.holders

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.proyecto.personal.puppisparcialtp3.R

class PostHolder (v: View) : RecyclerView.ViewHolder(v){

    private var view: View

    init {
        this.view = v
    }

    fun setName(name: String) {
        val txt: TextView = view.findViewById(R.id.tvCardViewNamePet)
        txt.text = name
    }

    fun setBreed(breed: String){
        val txt: TextView = view.findViewById(R.id.tvCardViewBreed)
        txt.text = breed
    }

    fun setSubBreed(subBreed: String){
        val txt: TextView = view.findViewById(R.id.tvCardViewSubBreed)
        txt.text = subBreed
    }

    fun setAge(age: String){
        val txt: TextView = view.findViewById(R.id.tvCardViewAgeGender)
        txt.text = age
    }

    fun getCardLayout (): CardView {
        return view.findViewById(R.id.card_package_item)
    }

    fun setImageView(url: String){
        val imgv: ImageView = view.findViewById(R.id.imCardViewPetPhoto)

        Glide.with(view)
            .load(url)
            .into(imgv)
    }
}