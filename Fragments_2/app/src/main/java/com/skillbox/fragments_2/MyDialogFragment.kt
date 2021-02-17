package com.skillbox.fragments_2

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class MyDialogFragment: DialogFragment() {

    private val mainFragmentInterface: MainFragmentInterface?
        get() = parentFragment.let { it as? MainFragmentInterface }
            ?: activity?.let { it as? MainFragmentInterface }

    private val items = arrayOf(
        ArticleTag.PLANE.tag,
        ArticleTag.BUS.tag,
        ArticleTag.INFINITY.tag,
        ArticleTag.ANCHOR.tag,
        ArticleTag.HOUSE.tag,
        ArticleTag.TRANSPORT.tag)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val state = requireArguments().getBooleanArray(KEY_BOOLEAN_ARRAY)!!

        return AlertDialog.Builder(requireContext())
            .setTitle("Выберите интересующие темы")
            .setMultiChoiceItems(
                items,
                state
            ) { _, _, _ ->
            }
            .setNegativeButton("Назад") { _, _ ->
            }
            .setPositiveButton("Отфильтровать") { _, _ ->
                mainFragmentInterface?.filterArticles(state)
            }
            .create()
    }

    companion object {
        private const val KEY_BOOLEAN_ARRAY = "key boolean array"
        fun newInstance(savedState: BooleanArray): MyDialogFragment {
            return MyDialogFragment().withArguments{
                putBooleanArray(KEY_BOOLEAN_ARRAY, savedState)
            }
        }
    }
}



