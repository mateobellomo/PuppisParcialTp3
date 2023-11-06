package com.proyecto.personal.puppisparcialtp3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.proyecto.personal.puppisparcialtp3.R

class FilterAdapter  (private var filters: MutableList<String>
) : RecyclerView.Adapter<FilterViewHolder>() {

    // Método para actualizar la lista de recordatorios
    fun updateData(nuevaLista: List<String>) {
        filters.clear()
        filters.addAll(nuevaLista)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_filter, parent, false)
        return FilterViewHolder(view)
    }

    override fun getItemCount(): Int  = filters.size

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.render(filters[position])




    }
}