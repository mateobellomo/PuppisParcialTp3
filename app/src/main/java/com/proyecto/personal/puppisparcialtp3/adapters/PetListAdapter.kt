package com.proyecto.personal.puppisparcialtp3.adapters

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.proyecto.personal.puppisparcialtp3.R
import com.proyecto.personal.puppisparcialtp3.domain.Pet
import com.proyecto.personal.puppisparcialtp3.holders.PostHolder
import com.proyecto.personal.puppisparcialtp3.listeners.OnFavoritesClickListener
import com.proyecto.personal.puppisparcialtp3.listeners.OnViewItemClickedListener

class PetListAdapter(
    private val petsList: MutableList<Pet> = mutableListOf(),
    private val onItemClick: OnViewItemClickedListener,
    private var onFavoriteClick: OnFavoritesClickListener
) : RecyclerView.Adapter<PostHolder>() {

    private val petsListFilter: MutableList<Pet> = mutableListOf()
    override fun getItemCount() = petsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_card_view_pet,parent,false)
        return (PostHolder(view))
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {

        val pet = petsList[position]
        holder.render(pet)

        holder.getCardLayout().setOnClickListener{
            onItemClick.onViewItemDetail(pet)
        }
        holder.favoriteBtnUncheked.setOnClickListener{
            onFavoriteClick.onFavoritesClick(pet)
        }
        holder.favoriteBtnchecked.setOnClickListener{
            onFavoriteClick.onFavoritesClick(pet)
        }

    }

    fun updateData(newData: List<Pet>) {
        petsList.clear() // Limpia la lista actual
        petsList.addAll(newData) // Agrega los nuevos datos
        petsListFilter.clear() // Limpia la lista actual
        petsListFilter.addAll(newData) // Agrega los nuevos datos

        notifyDataSetChanged()
    }

    fun updateDataFilter(newData: List<Pet>) {
        petsList.clear() // Limpia la lista actual
        petsList.addAll(newData) // Agrega los nuevos datos
        notifyDataSetChanged()
    }

    fun filterBreed(query: String?) {
        val filteredList = mutableListOf<Pet>()
        for (item in petsList) {
            if (item.breed?.lowercase()!!.contains(query!!.lowercase()) ||item.subBreed?.lowercase()!!.contains(query!!.lowercase())) {
                filteredList.add(item)
            }
        }
        updateDataFilter(filteredList)
        notifyDataSetChanged()
    }


    fun filterCategory (category : String){
        val filteredList = petsList.filter { pet ->
            when (category) {
                "FEMALE" -> pet.gender.toString() == "FEMALE"
                "MALE" -> pet.gender.toString() == "MALE"
//                "Cachorro" -> pet.age <= 1
//                "Adolescente" -> pet.age in 2..3
//                "Adulto" -> pet.age in 3..7
//                "Senior" -> pet.age > 7
                else -> pet.location.toString() == category
            }
        }
        updateDataFilter(filteredList)
        notifyDataSetChanged()

    }

    fun restoreList(){
        updateDataFilter(petsListFilter)
    }

}