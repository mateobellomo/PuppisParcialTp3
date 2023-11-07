package com.proyecto.personal.puppisparcialtp3.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.proyecto.personal.puppisparcialtp3.R

class FilterViewHolder (val view: View) : RecyclerView.ViewHolder(view) {

    private val filterName : TextView = view.findViewById(R.id.tvFilterName)
    fun render(filter: String) {
        filterName.text  = filter
    }

}