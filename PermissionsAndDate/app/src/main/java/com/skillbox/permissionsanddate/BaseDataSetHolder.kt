package com.skillbox.permissionsanddate

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skillbox.permissionsanddate.databinding.ItemMessageWithDateBinding

abstract class BaseDataSetHolder(binding: ItemMessageWithDateBinding, onItemClick: (position: Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            onItemClick(adapterPosition)
        }
    }

    private val tVTime: TextView = binding.tVTime
    private val tVLatitude: TextView = binding.tVLatitude
    private val tVLongitude: TextView = binding.tVLongitude
    private val tVAltitude: TextView = binding.tVAltitude
    private val iVImage: ImageView = binding.iVImage

    protected fun bindMainInfo(
            createdAt: String,
            latitude: String,
            longitude: String,
            altitude: String,
            link: String
    ) {
        tVTime.text = createdAt
        tVLatitude.text = latitude
        tVLongitude.text = longitude
        tVAltitude.text = altitude
        Glide
                .with(itemView)
                .load(link)
                .placeholder(R.drawable.ic_baseline_error_24)
                .error(R.drawable.ic_baseline_error_24)
                .into(iVImage)
    }
}