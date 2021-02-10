package com.skillbox.fragments_2

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Articles(
    @StringRes val textRes: Int,
    @DrawableRes val drawableRes: Int,
    val tags: List<ArticleTag>
)
