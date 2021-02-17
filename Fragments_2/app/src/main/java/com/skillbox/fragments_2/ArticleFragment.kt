package com.skillbox.fragments_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayout
import com.skillbox.fragments_2.databinding.FragmentArticleBinding
import kotlin.random.Random

class ArticleFragment: Fragment() {

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
            val tabLayout = (requireActivity() as AppActivity).findViewById<TabLayout>(R.id.tL)
            tabLayout.getTabAt(Random.nextInt(tabLayout.tabCount))?.orCreateBadge?.apply {
                number++
                badgeGravity = BadgeDrawable.TOP_END
            }
        }
    }

    companion object{

        private const val KEY_TEXT = "text"
        private const val KEY_IMAGE = "image"
        private const val KEY_COLOR = "color"

        fun newInstance(
            @StringRes textRes: Int,
            @DrawableRes drawableRes: Int,
            @ColorRes colorRes: Int
        ): ArticleFragment {
            return  ArticleFragment().withArguments {
                putInt(KEY_TEXT, textRes)
                putInt(KEY_IMAGE, drawableRes)
                putInt(KEY_COLOR, colorRes)
            }
        }
    }
}