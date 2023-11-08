package com.proyecto.personal.puppisparcialtp3.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.proyecto.personal.puppisparcialtp3.R

class FilterViewHolder (val view: View) : RecyclerView.ViewHolder(view) {

    private val filterName : TextView = view.findViewById(R.id.tvFilterName)
    private val icLocation : ImageView = view.findViewById(R.id.icLocation)
    private val icPet : ImageView = view.findViewById(R.id.icAge)
    private val icMale : ImageView = view.findViewById(R.id.icMale)
    private val icFemale : ImageView = view.findViewById(R.id.icFemale)
    val itemFilter : ConstraintLayout = view.findViewById(R.id.itemFilter)
    private val layout : LinearLayout = view.findViewById(R.id.loItemCard)
    @SuppressLint("ResourceAsColor")
    fun render(filter: String) {
        hideAllimg()
        filterName.text  = filter
        when (filter) {
            "FEMALE" -> {
                renderIc(icFemale)
                layout.setBackgroundColor(ContextCompat.getColor(view.context, R.color.pink))
            }
            "MALE"-> {
                renderIc(icMale)
                layout.setBackgroundColor(ContextCompat.getColor(view.context, R.color.deep_blue))
            }
            "Puppy", "Teen", "Adult", "Senior" -> {
                renderIc(icPet)
                layout.setBackgroundColor(ContextCompat.getColor(view.context, R.color.orange))
            }
            else ->{ renderIc(icLocation)
                layout.setBackgroundColor(ContextCompat.getColor(view.context, R.color.green))

            }

        }
    }

    private fun renderIc(image : ImageView){
        image.visibility = View.VISIBLE
    }

    private fun hideAllimg(){
    icLocation.visibility = View.GONE
    icMale.visibility = View.GONE
    icFemale.visibility = View.GONE
    icPet.visibility = View.GONE
    }

}