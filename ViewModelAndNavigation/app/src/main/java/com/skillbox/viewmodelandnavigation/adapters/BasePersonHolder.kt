package com.skillbox.viewmodelandnavigation.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skillbox.myapplication.R

abstract class BasePersonHolder(
    view: View,
    onItemClick: (id: Long) -> Unit,
    onLongItemClick: (position: Int) -> Unit
) : RecyclerView.ViewHolder(view) {

    private var currentId: Long? = null

    init {
        view.setOnClickListener {
            currentId?.let{
                onItemClick(it)
            }
        }
        view.setOnLongClickListener {
            onLongItemClick(adapterPosition)
            return@setOnLongClickListener true
        }
    }

    private val context = view.context
    private val tVName: TextView = view.findViewById(R.id.tVName)
    private val tVAge: TextView = view.findViewById(R.id.tVAge)
    private val tVIsHasOscar: TextView = view.findViewById(R.id.tVIsHasOscar)
    private val iVAvatar: ImageView = view.findViewById(R.id.iVAvatar)

    protected fun bindMainInfo(
        id: Long,
        name: String,
        age: Int,
        avatarLink: String,
        isHasOscar: Boolean
    ) {
        currentId = id
        tVName.text = name
        tVAge.text = context.getString(R.string.age, age)
        if (!isHasOscar) {
            tVIsHasOscar.visibility = View.GONE
        } else {
            tVIsHasOscar.visibility = View.VISIBLE
        }
        Glide
            .with(itemView)
            .load(avatarLink)
            .placeholder(R.drawable.ic_baseline_portrait_24)
            .error(R.drawable.ic_baseline_error_24)
            .into(iVAvatar)
    }
}