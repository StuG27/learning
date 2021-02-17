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



class MainFragment: Fragment(), MainFragmentInterface {

    private lateinit var binding: FragmentMainBinding
    private var dialog: AlertDialog? = null
    private var filter = BooleanArray(6)
    private var tabState = IntArray(6)
    private var tab = IntArray(6)
    private var isSaved = false

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
        initToolbar()
        val filterTrue = BooleanArray(6)
        filterTrue.fill(true)
        val tabNull = IntArray(5)
        tabNull.fill(0)
        if (savedInstanceState != null) {
            tabState = savedInstanceState.getIntArray(TAB) ?: tabNull
            filter = savedInstanceState.getBooleanArray(FILTER) ?: filterTrue
            isSaved = true
        }
        filterArticles(filter)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBooleanArray(FILTER, filter)
        saveTabState()
        outState.putIntArray(TAB, tabState)
    }

    override fun filterArticles(filterArray: BooleanArray){
        var selectedScreens = mutableListOf<Articles>()

        val allArticles = mutableListOf(
            ArticleTag.PLANE,
            ArticleTag.BUS,
            ArticleTag.INFINITY,
            ArticleTag.ANCHOR,
            ArticleTag.HOUSE,
            ArticleTag.TRANSPORT)

        val selectedArticles = mutableListOf<ArticleTag>()

        for ((j, i) in filterArray.withIndex()) {
            if (i) {
                selectedArticles.add(allArticles[j])
            }
        }

        for (n in screens) {
            for (k in selectedArticles){
                if (n.tags.contains(k)){
                    if (!selectedScreens.contains(n)){
                        selectedScreens.add(n)
                    }
                }
            }
        }
        if (selectedScreens.isEmpty()){
            selectedScreens = screens.toMutableList()
            filterArray.fill(true)
        }
        saveTab()
        filter = filterArray
        initAdapter(selectedScreens)
    }

    private fun saveTabState(){
        var i = 0
        while (i < binding.tL.tabCount) {
            val a = binding.tL.getTabAt(i)?.badge?.number
            tabState[i] = a ?: 0
            i++
        }
    }

    private fun saveTab(){
        var i = 0
        while (i < 6) {
            val a = binding.tL.getTabAt(i)?.badge?.number
            if (a != 0) {
                tab[i] = a ?: 0
            }
            i++
        }
    }

    private fun restoreTab(){
        var i = 0
        while (i < 6) {
            binding.tL.getTabAt(i)?.orCreateBadge?.apply {
                if (tab[i] != 0) {
                    number = tab[i]
                    badgeGravity = BadgeDrawable.TOP_END
                } else {
                    binding.tL.getTabAt(i)?.removeBadge()
                }
            }
            i++
        }
    }

    private fun restoreTabState(){
        var i = 0
        while (i < binding.tL.tabCount) {
            binding.tL.getTabAt(i)?.orCreateBadge?.apply {
                if (tabState[i] != 0) {
                    number = tabState[i]
                    badgeGravity = BadgeDrawable.TOP_END
                    tabState[i] = 0
                } else {
                    binding.tL.getTabAt(i)?.removeBadge()
                }
            }
            i++
        }
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
        viewPager.adapter = adapter
        viewPager.setPageTransformer(object : DepthTransformation(){})
        TabLayoutMediator(binding.tL, viewPager) {
                tab, position -> tab.text = articles[position].tabName
        }.attach()
        if (isSaved) {
            restoreTabState()
            isSaved = false
        } else {
            restoreTab()
        }
        val dotsIndicator = binding.dI
        dotsIndicator.setViewPager2(viewPager)

        binding.vP.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tL.getTabAt(position)?.removeBadge()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog?.dismiss()
    }

    private fun showDialogFragment() {
        MyDialogFragment.newInstance(filter)
            .show(childFragmentManager, "dialog")
    }

    companion object {
        private const val FILTER = "filter"
        private const val TAB = "tab"
        fun newInstance(): MainFragment {
            return MainFragment().withArguments{
            }
        }
    }
}