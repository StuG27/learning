package com.skillbox.networking.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.networking.data.RemoteMovie
import com.skillbox.networking.databinding.ItemMovieBinding


class MovieAdapterDelegate :
    AbsListItemAdapterDelegate<RemoteMovie, RemoteMovie, MovieAdapterDelegate.MovieHolder>() {

    override fun isForViewType(
        item: RemoteMovie,
        items: MutableList<RemoteMovie>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): MovieHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(
        item: RemoteMovie,
        holder: MovieHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class MovieHolder(
        binding: ItemMovieBinding,
    ) : BaseMovieHolder(binding) {

        fun bind(movie: RemoteMovie) {
            bindMainInfo(movie.id, movie.title, movie.year, movie.type, movie.poster)
        }
    }
}