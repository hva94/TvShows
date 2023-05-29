package com.hvasoft.mubi.domain.use_case

data class TvShowsUseCases(
    val getTvShows: GetTvShowsUC,
    val getDetailTvShowById: GetDetailTvShowUC,
    val setIsFavorite: SetIsFavoriteUC,
    val updateTvShow: UpdateTvShowUC,
    val getFavoritesTvShows: GetFavoritesTvShowsUC
)
