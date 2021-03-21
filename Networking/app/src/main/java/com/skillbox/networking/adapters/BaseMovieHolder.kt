package com.skillbox.networking.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skillbox.networking.R
import com.skillbox.networking.databinding.ItemMovieBinding


abstract class BaseMovieHolder(
    binding: ItemMovieBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val tVTitle = binding.tVTitle
    private val tVYear = binding.tVYear
    private val tVId = binding.tVId
    private val tVType = binding.tVType
    private val iVPoster = binding.iVPoster

    protected fun bindMainInfo(
        id: String,
        title: String,
        year: String,
        type: String,
        poster: String
    ) {
        tVTitle.text = title
        tVYear.text = year
        tVId.text = id
        tVType.text = type
        Glide
            .with(itemView)
            .load(poster)
            .placeholder(R.drawable.ic_baseline_movie_24)
            .error(R.drawable.ic_baseline_error_24)
            .into(iVPoster)
    }
}