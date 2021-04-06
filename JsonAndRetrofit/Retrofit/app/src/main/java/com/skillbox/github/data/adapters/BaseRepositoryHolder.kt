package com.skillbox.github.data.adapters

import androidx.recyclerview.widget.RecyclerView
import com.skillbox.github.R
import com.skillbox.github.databinding.ItemRepositoryBinding


abstract class BaseRepositoryHolder(
    binding: ItemRepositoryBinding,
    onItemClick: (name: String, owner: String, url: String) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private var name: String? = null
    private var owner: String? = null
    private var url: String? = null

    init {
        binding.root.setOnClickListener {
            onItemClick(
                name ?: "",
                owner ?: "",
                url ?: ""
            )
        }
    }

    private val tVName = binding.tVName
    private val tVOwner = binding.tVOwner
    private val tVUrl = binding.tVUrl
    private val context = binding.root.context

    protected fun bindMainInfo(
        name: String,
        owner: String,
        url: String,
    ) {
        this.name = name
        this.owner = owner
        this.url = url
        tVName.text = context.getString(R.string.name, name)
        tVOwner.text = context.getString(R.string.owner, owner)
        tVUrl.text = url
    }
}