package com.skillbox.fragments_2

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Articles(
    val tabName: String,
    @StringRes val textRes: Int,
    @DrawableRes val drawableRes: Int,
    @ColorRes val colorRes: Int,
    val tags: List<ArticleTag>
)
