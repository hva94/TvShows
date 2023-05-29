package com.hvasoft.mubi.data.local_db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hvasoft.mubi.data.local_db.converter.SeasonConverter
import com.hvasoft.mubi.data.local_db.dao.RemoteKeysDao
import com.hvasoft.mubi.data.local_db.dao.TvShowDao
import com.hvasoft.mubi.data.local_db.entities.RemoteKeysEntity
import com.hvasoft.mubi.data.local_db.entities.TvShowEntity

@Database(
    entities = [
        TvShowEntity::class,
        RemoteKeysEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(SeasonConverter::class)
abstract class TvShowDatabase : RoomDatabase() {

    abstract fun tvShowDao(): TvShowDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        const val DB_NAME = "mubi.db"
    }
}