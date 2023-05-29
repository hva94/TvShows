package com.hvasoft.mubi.data.remote_db.service

import com.hvasoft.mubi.data.remote_db.response.TvShowTDO
import com.hvasoft.mubi.data.remote_db.response.TvShowResponseTDO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowApi {

    @GET("popular")
    suspend fun getPopularTvShows(
        @Query("page") page: Int
    ): Response<TvShowResponseTDO>

    @GET("top_rated")
    suspend fun getTopRatedTvShows(
        @Query("page") page: Int
    ): Response<TvShowResponseTDO>

    @GET("airing_today")
    suspend fun getAiringTodayTvShows(
        @Query("page") page: Int
    ): Response<TvShowResponseTDO>

    @GET("on_the_air")
    suspend fun getOnTheAirTvShows(
        @Query("page") page: Int
    ): Response<TvShowResponseTDO>

    @GET("{tv_id}")
    suspend fun getDetailTvShowById(
        @Path("tv_id") tvShowId: String
    ): Response<TvShowTDO>

}