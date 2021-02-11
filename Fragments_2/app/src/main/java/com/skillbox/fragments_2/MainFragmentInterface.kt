package com.skillbox.fragments_2

interface MainFragmentInterface {
    fun filterArticles(articleTag: MutableList<ArticleTag>, state: BooleanArray)
    fun buttonClick()
}