package com.example.gok.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.gok.R
import com.example.gok.data.model.Spotlight
import com.squareup.picasso.Picasso

class RecyclerSpotlightAdapter(
    var spotlightList: ArrayList<Spotlight>
) : RecyclerView.Adapter<RecyclerSpotlightAdapter.SpotlightViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotlightViewHolder {
        return SpotlightViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_spotlight, parent, false)
        )
    }

    override fun getItemCount(): Int = spotlightList.size

    override fun onBindViewHolder(holder: SpotlightViewHolder, position: Int) {
        holder.bind(spotlightList[position])
    }

    class SpotlightViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val imageView = view.findViewById<ImageView>(R.id.imageViewSpotlight)

        fun bind(listItem: Spotlight) {
            Picasso.get().load(listItem.bannerURL).into(imageView)
        }
    }
}