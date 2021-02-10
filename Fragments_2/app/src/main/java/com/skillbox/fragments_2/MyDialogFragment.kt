package com.skillbox.fragments_2

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast


import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment



class MyDialogFragment: DialogFragment() {
    private val items = arrayOf(
        ArticleTag.TRANSPORT.tag,
        ArticleTag.ANCHOR.tag,
        ArticleTag.PLANE.tag,
        ArticleTag.BUS.tag,
        ArticleTag.HOUSE.tag,
        ArticleTag.INFINITY.tag)
    private val state = BooleanArray(6)
    private val allArticles = mutableListOf(
        ArticleTag.TRANSPORT,
        ArticleTag.ANCHOR,
        ArticleTag.PLANE,
        ArticleTag.BUS,
        ArticleTag.HOUSE,
        ArticleTag.INFINITY)
    private val selectedArticles = mutableListOf<ArticleTag>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Выберите интересующие темы")
            .setMultiChoiceItems(
                items,
                state,
                DialogInterface.OnMultiChoiceClickListener { _, itemNumber, itemState ->
                    when (itemState) {
                        true -> selectedArticles.add(allArticles[itemNumber])
                        false -> selectedArticles.removeAll { it == allArticles[itemNumber]}
                    }
                    Toast.makeText(context, "$selectedArticles", Toast.LENGTH_LONG).show()
                })
            .setNegativeButton("Назад") { _, _ -> //интерфейс как обычно
            }
            .setPositiveButton("Отфильтровать") { _, _ ->
            }
            .create()
    }
}



