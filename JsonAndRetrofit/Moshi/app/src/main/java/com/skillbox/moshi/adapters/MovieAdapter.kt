package com.skillbox.moshi.adapters

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skillbox.moshi.data.RemoteMovie


class MovieAdapter : AsyncListDifferDelegationAdapter<RemoteMovie>(PersonDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(MovieAdapterDelegate())
    }

    class PersonDiffUtilCallback : DiffUtil.ItemCallback<RemoteMovie>() {

        override fun areItemsTheSame(oldItem: RemoteMovie, newItem: RemoteMovie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RemoteMovie, newItem: RemoteMovie): Boolean {
            return oldItem == newItem
        }
    }
}