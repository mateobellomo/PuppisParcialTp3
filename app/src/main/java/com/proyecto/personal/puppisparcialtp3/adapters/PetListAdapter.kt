package com.proyecto.personal.puppisparcialtp3.adapters

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.proyecto.personal.puppisparcialtp3.R
import com.proyecto.personal.puppisparcialtp3.entities.Pet
import com.proyecto.personal.puppisparcialtp3.holders.PostHolder
import com.proyecto.personal.puppisparcialtp3.listeners.OnViewItemClickedListener

class PetListAdapter(
    private val petsList: MutableList<Pet> = mutableListOf(),
private val onItemClick: OnViewItemClickedListener
) : RecyclerView.Adapter<PostHolder>() {

    override fun getItemCount() = petsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.card_view_pet,parent,false)
        return (PostHolder(view))
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {

        val pet = petsList[position]

        holder.setAge(TextUtils.concat(pet.age.toString(), " / ", pet.gender).toString())
        holder.setName(pet.name)
        holder.setBreed(pet.breed)
        holder.setSubBreed(pet.subBreed)
        holder.setImageView(pet.urlImages[0])


        holder.getCardLayout().setOnClickListener{
            onItemClick.onViewItemDetail(pet)
        }

        // Cambiar atributo a favorito
        holder.getCheckBox().setOnClickListener{
            onItemClick.addFavorite(pet)
        }

    }

    fun updateData(newData: List<Pet>) {
        petsList.clear() // Limpia la lista actual
        petsList.addAll(newData) // Agrega los nuevos datos

        notifyDataSetChanged()
    }

}