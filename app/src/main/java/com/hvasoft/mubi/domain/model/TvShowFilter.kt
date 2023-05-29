package com.hvasoft.mubi.domain.model

import androidx.annotation.StringRes
import com.hvasoft.mubi.R

enum class TvShowFilter(@StringRes val title: Int, val tvName: String) {
    TOP_RATED(title = R.string.tv_show_top_rated, tvName = "top_rated"),
    POPULAR(title = R.string.tv_show_popular, tvName = "popular"),
    ON_TV(title = R.string.tv_show_on_tv, tvName = "on_tv"),
    AIRING_TODAY(title = R.string.tv_show_airing_today, tvName = "airing_today")
}

fun getAllTvShowsTypes(): List<TvShowFilter> {
    return listOf(
        TvShowFilter.TOP_RATED,
        TvShowFilter.POPULAR,
        TvShowFilter.ON_TV,
        TvShowFilter.AIRING_TODAY
    )
}

fun getTvShowType(value: String): TvShowFilter? {
    val map = TvShowFilter.values().associateBy(TvShowFilter::tvName)
    return map[value]
}