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
//    private var formState = FormState(BooleanArray(6))
    private var state = BooleanArray(6)
    private val allArticles = mutableListOf(
        ArticleTag.PLANE,
        ArticleTag.BUS,
        ArticleTag.INFINITY,
        ArticleTag.ANCHOR,
        ArticleTag.HOUSE,
        ArticleTag.TRANSPORT)
    private val selectedArticles = mutableListOf<ArticleTag>()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val fullState = BooleanArray(6)
        fullState.fill(true)
        state = requireArguments().getBooleanArray(KEY_BOOLEAN_ARRAY) ?: fullState
//        val formStateFull = FormState(BooleanArray(6))
//        formStateFull.state.fill(true)
//        formState = savedInstanceState?.getParcelable(FORM_STATE) ?: formStateFull
        var i = 0
        while (i < state.size){
            if (!state[i]){
                selectedArticles.removeAll {it == allArticles[i]}
            }
            else {
                selectedArticles.add(allArticles[i])
            }
            i++
        }
        return AlertDialog.Builder(requireContext())
            .setTitle("Выберите интересующие темы")
            .setMultiChoiceItems(
                items,
                state
//                formState.state,
            ) { _, itemNumber, itemState ->
                when (itemState) {
                    true -> selectedArticles.add(allArticles[itemNumber])
                    false -> selectedArticles.removeAll { it == allArticles[itemNumber] }
                }
            }
            .setNegativeButton("Назад") { _, _ ->
            }
            .setPositiveButton("Отфильтровать") { _, _ ->
                mainFragmentInterface?.filterArticles(selectedArticles, state)
            }
            .create()
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putParcelable(FORM_STATE, formState)
//    }
//
//    companion object{
//        private const val FORM_STATE = "formState"
//    }
    companion object {
        private const val KEY_BOOLEAN_ARRAY = "key boolean array"
        fun newInstance(savedState: BooleanArray): MyDialogFragment {
            return MyDialogFragment().withArguments{
                putBooleanArray(KEY_BOOLEAN_ARRAY, savedState)
            }
        }
    }
}



