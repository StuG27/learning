package com.skillbox.fragments_2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ArticlesAdapter(
    private val screens: List<Articles>,
    activity: FragmentActivity): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return screens.size
    }

    override fun createFragment(position: Int): Fragment {
        val screen: Articles = screens[position]
        return ArticleFragment.newInstance(
            screen.textRes,
            screen.drawableRes,
            screen.colorRes
        )
    }
}