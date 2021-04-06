package com.skillbox.github.data.adapters

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skillbox.github.data.Repository


class RepositoryAdapter(
    onItemClick: (name: String, owner: String, url: String) -> Unit
) : AsyncListDifferDelegationAdapter<Repository>(PersonDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(RepositoryAdapterDelegate(onItemClick))
    }

    class PersonDiffUtilCallback : DiffUtil.ItemCallback<Repository>() {

        override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem == newItem
        }
    }
}