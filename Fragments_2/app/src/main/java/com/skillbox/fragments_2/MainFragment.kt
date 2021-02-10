package com.skillbox.fragments_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayoutMediator
import com.skillbox.fragments_2.databinding.FragmentMainBinding
import kotlin.random.Random

class MainFragment: Fragment(), ArticleInterface {

    private lateinit var binding: FragmentMainBinding
    private var dialog: AlertDialog? = null


    private val nameTab: List<String> = listOf("Самолет","Автобус","Бесконечность","Якорь","Дом")
    private val screens: List<Articles> = listOf(
        Articles(
            R.string.article1,
            R.drawable.image1,
            listOf(ArticleTag.PLANE, ArticleTag.TRANSPORT)
        ),
        Articles(
            R.string.article2,
            R.drawable.image2,
            listOf(ArticleTag.BUS, ArticleTag.TRANSPORT)
        ),
        Articles(
            R.string.article3,
            R.drawable.image3,
            listOf(ArticleTag.INFINITY)
        ),
        Articles(
            R.string.article4,
            R.drawable.image4,
            listOf(ArticleTag.ANCHOR)
        ),
        Articles(
            R.string.article5,
            R.drawable.image5,
            listOf(ArticleTag.HOUSE)
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initToolbar()
        initAdapter(screens)
    }

    override fun buttonClick() {
        binding.tL.getTabAt(Random.nextInt(screens.size))?.orCreateBadge?.apply {
            number++
            badgeGravity = BadgeDrawable.TOP_END
        }
        binding.vP.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tL.getTabAt(position)?.removeBadge()
            }
        })
    }

    private fun initToolbar() {
        binding.toolbar.title = "Статьи"
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId){
                R.id.action ->{
                    showDialogFragment()
                    true
                }
                else -> false
            }
        }
    }

    private fun initAdapter(articles: List<Articles>){
        val viewPager = binding.vP
        val adapter = ArticlesAdapter(articles, requireActivity())
        val dotsIndicator = binding.dI
        viewPager.adapter = adapter
        dotsIndicator.setViewPager2(viewPager)
        viewPager.setPageTransformer(object : DepthTransformation(){})
        TabLayoutMediator(binding.tL, viewPager) {
                tab, position -> tab.text = nameTab[position]
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog?.dismiss()
    }

    private fun showDialogFragment() {
        MyDialogFragment()
            .show(childFragmentManager, "dialog")
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment().withArguments{
            }
        }
    }
}