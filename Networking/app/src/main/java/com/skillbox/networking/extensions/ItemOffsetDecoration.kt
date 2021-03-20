package com.skillbox.networking.extensions

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemOffsetDecoration(private val context: Context): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val offset = 10.fromDPToPixels(context)
        with(outRect){
            left = offset
            right = offset
            top = offset
            bottom = offset
        }
    }

}