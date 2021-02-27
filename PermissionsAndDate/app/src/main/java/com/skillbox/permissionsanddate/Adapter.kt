package com.skillbox.permissionsanddate

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter


class Adapter( onItemClick: (position: Int) -> Unit
) : AsyncListDifferDelegationAdapter<DataSet>(PersonDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(DataSetWithLocationAdapterDelegate(onItemClick))
    }

    class PersonDiffUtilCallback : DiffUtil.ItemCallback<DataSet>() {

        override fun areItemsTheSame(oldItem: DataSet, newItem: DataSet): Boolean {
            return when {
                oldItem is DataSet.DataSetWithLocation && newItem is DataSet.DataSetWithLocation -> oldItem.id == newItem.id
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: DataSet, newItem: DataSet): Boolean {
            return oldItem == newItem
        }
    }
}