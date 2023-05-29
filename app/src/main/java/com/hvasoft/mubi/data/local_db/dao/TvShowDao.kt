package com.hvasoft.mubi.data.local_db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hvasoft.mubi.data.local_db.entities.TvShowEntity

@Dao
interface TvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTvShows(tvShows: List<TvShowEntity>)

    @Query("SELECT * FROM tv_shows WHERE category = :category")
    fun getTvShows(category: String): PagingSource<Int, TvShowEntity>

    @Query("DELETE FROM tv_shows WHERE category = :category")
    suspend fun clear(category: String)

    @Query("SELECT COUNT(id) FROM tv_shows WHERE category = :category ")
    suspend fun existData(category: String): Int

    @Update
    suspend fun updateTvShow(tvShowEntity: TvShowEntity)

    @Query("UPDATE tv_shows SET isFavorite = :isFavorite WHERE id = :tvShowId")
    suspend fun setIsFavorite(tvShowId: String, isFavorite: Boolean)

    @Query("SELECT * FROM tv_shows WHERE isFavorite = 1")
    suspend fun getFavoritesTvShows(): List<TvShowEntity>

}