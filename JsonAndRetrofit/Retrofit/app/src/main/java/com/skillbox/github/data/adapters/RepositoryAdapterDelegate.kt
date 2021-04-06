package com.skillbox.github.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.github.data.Repository
import com.skillbox.github.databinding.ItemRepositoryBinding


class RepositoryAdapterDelegate(
    private val onItemClick: (name: String, owner: String, url: String) -> Unit
) :
    AbsListItemAdapterDelegate<Repository, Repository, RepositoryAdapterDelegate.RepositoryHolder>() {

    override fun isForViewType(
        item: Repository,
        items: MutableList<Repository>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): RepositoryHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRepositoryBinding.inflate(layoutInflater, parent, false)
        return RepositoryHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(
        item: Repository,
        holder: RepositoryHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class RepositoryHolder(
        binding: ItemRepositoryBinding,
        onItemClick: (name: String, owner: String, url: String) -> Unit,
    ) : BaseRepositoryHolder(binding, onItemClick) {

        fun bind(repository: Repository) {
            bindMainInfo(
                repository.name,
                repository.owner.login,
                repository.owner.url
            )
        }
    }
}