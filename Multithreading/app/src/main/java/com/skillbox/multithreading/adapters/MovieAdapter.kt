package com.skillbox.multithreading.adapters

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skillbox.multithreading.data.Movie

class MovieAdapter : AsyncListDifferDelegationAdapter<Movie>(PersonDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(MovieAdapterDelegate())
    }

    class PersonDiffUtilCallback : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}