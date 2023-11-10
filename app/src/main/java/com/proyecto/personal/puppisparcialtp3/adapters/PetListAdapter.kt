package com.proyecto.personal.puppisparcialtp3.adapters

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
    private val filterList: MutableList<String> = mutableListOf()
    private var breedFilter: String = ""
    override fun getItemCount() = petsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_card_view_pet, parent, false)
        return (PostHolder(view))
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {

        val pet = petsList[position]
        holder.render(pet)

        holder.getCardLayout().setOnClickListener {
            onItemClick.onViewItemDetail(pet)
        }
        holder.favoriteBtnUnchecked.setOnClickListener {
            onFavoriteClick.onFavoritesClick(pet)
        }
        holder.favoriteBtnChecked.setOnClickListener {
            onFavoriteClick.onFavoritesClick(pet)
        }

    }

    private fun refreshData() {
        var dataFilter = petsListFilter.toMutableList()

        // filtos de popMenu
        if (filterList.isNotEmpty()) {
            for (filter in filterList) {
                val filteredPets = dataFilter.filter { pet ->
                    when (filter) {
                        "FEMALE" -> pet.gender.toString() == "FEMALE"
                        "MALE" -> pet.gender.toString() == "MALE"
                        "Puppy" -> pet.age < 1
                        "Teen" -> pet.age in 1..3
                        "Adult" -> pet.age in 3..7
                        "Senior" -> pet.age > 7
                        else -> pet.location.toString() == filter
                    }
                }
                dataFilter.clear()
                dataFilter.addAll(filteredPets)
            }
        }

        if (breedFilter.isNotBlank()) {
            dataFilter = searchBar(dataFilter)
        }

        petsList.clear() // Limpia la lista actual
        petsList.addAll(dataFilter) // Agrega los nuevos datos
        notifyDataSetChanged()

    }

    fun updateData(newData: List<Pet>) {
        petsList.clear() // Limpia la lista actual
        petsList.addAll(newData) // Agrega los nuevos datos
        petsListFilter.clear() // Limpia la lista actual
        petsListFilter.addAll(newData) // Agrega los nuevos datos

        notifyDataSetChanged()
    }

    private fun searchBar(listToFilter: MutableList<Pet>): MutableList<Pet> {
        val filteredList = mutableListOf<Pet>()
        for (item in listToFilter) {
            if (item.breed?.lowercase()!!
                    .contains(breedFilter!!.lowercase()) || item.subBreed?.lowercase()!!
                    .contains(breedFilter!!.lowercase())
            ) {
                filteredList.add(item)
            }
        }
        return filteredList
    }

    fun filterBreed(query: String?) {
        if (query != null) {
            breedFilter = query
        }

        refreshData()
    }

    fun filterCategory(category: String) {
        filterList.add(category)
        refreshData()

    }

    fun clearFilterList() {
        filterList.clear()
        refreshData()
    }

    fun deleteFilter(filterToDelete: String) {
        filterList.remove(filterToDelete)
        refreshData()
    }
}