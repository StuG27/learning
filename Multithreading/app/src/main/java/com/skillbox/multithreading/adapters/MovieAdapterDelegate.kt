package com.skillbox.multithreading.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.multithreading.data.Movie
import com.skillbox.multithreading.databinding.ItemMovieBinding

class MovieAdapterDelegate :
    AbsListItemAdapterDelegate<Movie, Movie, MovieAdapterDelegate.MovieHolder>() {

    override fun isForViewType(item: Movie, items: MutableList<Movie>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): MovieHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(
        item: Movie,
        holder: MovieHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class MovieHolder(
        binding: ItemMovieBinding,
    ) : BaseMovieHolder(binding) {

        fun bind(movie: Movie) {
            bindMainInfo(movie.title, movie.year)
        }
    }
}