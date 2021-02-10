package com.skillbox.fragments_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.skillbox.fragments_2.databinding.FragmentArticleBinding

class ArticleFragment: Fragment() {

    private val articleInterface: ArticleInterface?
        get() = MainFragment.let { it as? ArticleInterface }
            ?: activity?.let { it as? ArticleInterface }

    private lateinit var binding: FragmentArticleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentArticleBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //requireView().setBackgroundColor(requireArguments().getInt(KEY_COLOR))
        //requireView().setBackgroundColor(context?.resources!!.getColor(requireArguments().getInt(KEY_COLOR)))
        binding.tV.setText(requireArguments().getInt(KEY_TEXT))
        binding.iV.setImageResource(requireArguments().getInt(KEY_IMAGE))
        binding.b.setOnClickListener {
            Toast.makeText(context, "$articleInterface", Toast.LENGTH_SHORT).show()
            articleInterface?.buttonClick()
        }
    }

    companion object{

        private const val KEY_TEXT = "text"
        private const val KEY_IMAGE = "image"

        fun newInstance(
            @StringRes textRes: Int,
            @DrawableRes drawableRes: Int
        ): ArticleFragment {
            return  ArticleFragment().withArguments {
                putInt(KEY_TEXT, textRes)
                putInt(KEY_IMAGE, drawableRes)
            }
        }
    }
}