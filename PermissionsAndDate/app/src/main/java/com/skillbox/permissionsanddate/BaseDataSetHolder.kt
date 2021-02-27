package com.skillbox.permissionsanddate

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

abstract class BaseDataSetHolder(view: View, onItemClick: (position: Int) -> Unit): RecyclerView.ViewHolder(view){

    init {
        view.setOnClickListener {
            onItemClick(adapterPosition)
        }
    }

    private val tVTime: TextView = view.findViewById(R.id.tVTime)
    private val tVLatitude: TextView = view.findViewById(R.id.tVLatitude)
    private val tVLongitude: TextView = view.findViewById(R.id.tVLongitude)
    private val tVAltitude: TextView = view.findViewById(R.id.tVAltitude)
    private val iVImage: ImageView = view.findViewById(R.id.iVImage)

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