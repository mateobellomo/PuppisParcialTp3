package com.proyecto.personal.puppisparcialtp3.holders

import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.proyecto.personal.puppisparcialtp3.R
import com.proyecto.personal.puppisparcialtp3.domain.Pet

class PostHolder (v: View) : RecyclerView.ViewHolder(v){

    private var view: View
    val favoriteBtnUncheked : ImageView = v.findViewById(R.id.checkBoxCardViewFavorite)
    val favoriteBtnchecked : ImageView = v.findViewById(R.id.checkBoxCardViewFavorite2)
    init {
        this.view = v
    }

    fun render(pet: Pet){
        setAge(TextUtils.concat(pet.age.toString(), " / ", pet.gender.toString()).toString())
        setName(pet.name)
        setBreed(pet.breed)
        pet.subBreed?.let { setSubBreed(it) }
        pet.photo?.let { setImageView(it) }

        if(pet.isFavorite){
            favoriteBtnUncheked.visibility = View.INVISIBLE
            favoriteBtnchecked.visibility = View.VISIBLE
        } else {
            favoriteBtnUncheked.visibility = View.VISIBLE
            favoriteBtnchecked.visibility = View.INVISIBLE
        }
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
        val img: ImageView = view.findViewById(R.id.imCardViewPetPhoto)

        Glide.with(view)
            .load(url)
            .into(img)
    }


}