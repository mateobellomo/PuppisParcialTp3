package com.proyecto.personal.puppisparcialtp3.holders

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.proyecto.personal.puppisparcialtp3.R
import com.proyecto.personal.puppisparcialtp3.domain.Pet

class PostHolder(v: View) : RecyclerView.ViewHolder(v) {

    private var view: View
    val favoriteBtnUnchecked: ImageView = v.findViewById(R.id.checkBoxCardViewFavorite)
    val favoriteBtnChecked: ImageView = v.findViewById(R.id.checkBoxCardViewFavorite2)

    init {
        this.view = v
    }

    fun render(pet: Pet) {
        setAge(TextUtils.concat(pet.age.toString(), " / ", pet.gender.toString()).toString())
        setName(pet.name)
        setBreed(pet.breed)
        pet.subBreed?.let { setSubBreed(it) }
        pet.photo?.let { setImageView(it[0]) }

        if (pet.isFavorite) {
            favoriteBtnUnchecked.visibility = View.INVISIBLE
            favoriteBtnChecked.visibility = View.VISIBLE
        } else {
            favoriteBtnUnchecked.visibility = View.VISIBLE
            favoriteBtnChecked.visibility = View.INVISIBLE
        }
    }

    private fun setName(name: String) {
        val txt: TextView = view.findViewById(R.id.tvCardViewNamePet)
        txt.text = name
    }

    private fun setBreed(breed: String) {
        val txt: TextView = view.findViewById(R.id.tvCardViewBreed)
        txt.text = breed
    }

    private fun setSubBreed(subBreed: String) {
        val txt: TextView = view.findViewById(R.id.tvCardViewSubBreed)
        txt.text = subBreed
    }

    private fun setAge(age: String) {
        val txt: TextView = view.findViewById(R.id.tvCardViewAgeGender)
        txt.text = age
    }

    fun getCardLayout(): CardView {
        return view.findViewById(R.id.card_package_item)
    }

    private fun setImageView(url: String) {
        val img: ImageView = view.findViewById(R.id.imCardViewPetPhoto)

        Glide.with(view)
            .load(url)
            .into(img)
    }
}