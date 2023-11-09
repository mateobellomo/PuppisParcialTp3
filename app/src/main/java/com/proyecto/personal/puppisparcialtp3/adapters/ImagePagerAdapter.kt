package com.proyecto.personal.puppisparcialtp3.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.proyecto.personal.puppisparcialtp3.R

class ImagePagerAdapter(private val context: Context, private val imageList: List<Int>) :
    RecyclerView.Adapter<ImagePagerAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(imageResId: Int) {
            imageView.setImageResource(imageResId)
        }
    }
}