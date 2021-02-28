package com.skillbox.lists_1.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.skillbox.lists_1.R
import com.skillbox.lists_1.databinding.ItemActorBinding
import com.skillbox.lists_1.databinding.ItemProducerBinding

abstract class BasePersonHolder(
    binding: ViewBinding,
    onItemClick: (position: Int) -> Unit
) : RecyclerView.ViewHolder(binding.root){

    init {
        binding.root.setOnClickListener {
            onItemClick(adapterPosition)
        }
    }

    private val context = when (binding) {
        is ItemActorBinding -> binding.root.context
        is ItemProducerBinding -> binding.root.context
        else -> null
    }
    private val tVName = when (binding) {
        is ItemActorBinding -> binding.tVName
        is ItemProducerBinding -> binding.tVName
        else -> null
    }
    private val tVAge = when (binding) {
        is ItemActorBinding -> binding.tVAge
        is ItemProducerBinding -> binding.tVAge
        else -> null
    }
    private val tVIsHasOscar = when (binding) {
        is ItemActorBinding -> binding.tVIsHasOscar
        is ItemProducerBinding -> binding.tVIsHasOscar
        else -> null
    }
    private val iVAvatar = when (binding) {
        is ItemActorBinding -> binding.iVAvatar
        is ItemProducerBinding -> binding.iVAvatar
        else -> null
    }

    protected fun bindMainInfo(
        name: String,
        age: Int,
        avatarLink: String,
        isHasOscar: Boolean
    ) {
        tVName?.text = name
        tVAge?.text = context?.getString(R.string.age, age)
        if (!isHasOscar) {
            tVIsHasOscar?.visibility = View.GONE
        } else {
            tVIsHasOscar?.visibility = View.VISIBLE
        }
        Glide
            .with(itemView)
            .load(avatarLink)
            .placeholder(R.drawable.ic_baseline_portrait_24)
            .error(R.drawable.ic_baseline_error_24)
            .into(iVAvatar!!)
    }
}
