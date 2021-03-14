package com.skillbox.multithreading.adapters

import androidx.recyclerview.widget.RecyclerView
import com.skillbox.multithreading.databinding.ItemMovieBinding

abstract class BaseMovieHolder(
    binding: ItemMovieBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val tVTitle = binding.tVTitle
    private val tVYear = binding.tVYear

    protected fun bindMainInfo(
        title: String,
        year: Int
    ) {
        tVTitle.text = title
        tVYear.text = year.toString()
    }
}