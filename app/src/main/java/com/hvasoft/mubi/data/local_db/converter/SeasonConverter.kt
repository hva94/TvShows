package com.hvasoft.mubi.data.local_db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hvasoft.mubi.data.local_db.entities.SeasonEntity
import java.util.*

class SeasonConverter {

    @TypeConverter
    fun fromSeasonList(value: List<SeasonEntity>): String {
        val gson = Gson()
        val type = object : TypeToken<List<SeasonEntity>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toSeasonList(value: String?): List<SeasonEntity> {
        if (value == null) {
            return Collections.emptyList()
        }
        val gson = Gson()
        val type = object : TypeToken<List<SeasonEntity>>() {}.type
        return gson.fromJson(value, type)
    }
}