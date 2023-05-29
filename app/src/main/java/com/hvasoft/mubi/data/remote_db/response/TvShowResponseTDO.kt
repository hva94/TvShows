package com.hvasoft.mubi.data.remote_db.response

import com.squareup.moshi.Json

data class TvShowResponseTDO(
    val page: Int,
    val results: List<TvShowTDO>,
    @Json(name = "total_pages")
    val totalPages: Int
)