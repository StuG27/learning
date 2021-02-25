package com.skillbox.lists_1.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skillbox.lists_1.R

abstract class BasePersonHolder(
    view: View,
    onItemClick: (position: Int) -> Unit
) : RecyclerView.ViewHolder(view) {

    init {
        view.setOnClickListener {
            onItemClick(adapterPosition)
        }
    }

    private val context = view.context
    private val tVName: TextView = view.findViewById(R.id.tVName)
    private val tVAge: TextView = view.findViewById(R.id.tVAge)
    private val tVIsHasOscar: TextView = view.findViewById(R.id.tVIsHasOscar)
    private val iVAvatar: ImageView = view.findViewById(R.id.iVAvatar)

    protected fun bindMainInfo(
        name: String,
        age: Int,
        avatarLink: String,
        isHasOscar: Boolean
    ) {
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