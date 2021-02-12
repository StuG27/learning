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

class MainFragment: Fragment(), MainFragmentInterface {

    private lateinit var binding: FragmentMainBinding
    private var dialog: AlertDialog? = null
    private var savedState = BooleanArray(6)

    private val screens: List<Articles> = listOf(
        Articles(
            "Самолет",
            R.string.article1,
            R.drawable.image1,
            R.color.color1,
            listOf(ArticleTag.PLANE, ArticleTag.TRANSPORT)
        ),
        Articles(
            "Автобус",
            R.string.article2,
            R.drawable.image2,
            R.color.color2,
            listOf(ArticleTag.BUS, ArticleTag.TRANSPORT)
        ),
        Articles(
            "Бесконечность",
            R.string.article3,
            R.drawable.image3,
            R.color.color3,
            listOf(ArticleTag.INFINITY)
        ),
        Articles(
            "Якорь",
            R.string.article4,
            R.drawable.image4,
            R.color.color4,
            listOf(ArticleTag.ANCHOR)
        ),
        Articles(
            "Дом",
            R.string.article5,
            R.drawable.image5,
            R.color.color5,
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
        savedState.fill(true)
        initToolbar()
        initAdapter(screens)
    }

    override fun filterArticles(articleTag : MutableList<ArticleTag>, state: BooleanArray) {
        var selectedScreens = mutableListOf<Articles>()
        savedState = state
        for (i in screens) {
            for (j in articleTag){
                if (i.tags.contains(j)){
                    if (!selectedScreens.contains(i)){
                        selectedScreens.add(i)
                    }
                }
            }
        }
        if (selectedScreens.isEmpty()){
            selectedScreens = screens.toMutableList()
        }
        initAdapter(selectedScreens)
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
        val dotsIndicator = binding.dI
        val adapter = ArticlesAdapter(articles, requireActivity())
        viewPager.adapter = adapter
        viewPager.setPageTransformer(object : DepthTransformation(){})
        TabLayoutMediator(binding.tL, viewPager) {
                tab, position -> tab.text = articles[position].tabName
        }.attach()
        dotsIndicator.setViewPager2(viewPager)
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog?.dismiss()
    }

    private fun showDialogFragment() {
        MyDialogFragment.newInstance(savedState)
            .show(childFragmentManager, "dialog")
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment().withArguments{
            }
        }
    }
}