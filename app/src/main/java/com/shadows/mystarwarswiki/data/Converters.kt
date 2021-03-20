package com.shadows.mystarwarswiki.data

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    @TypeConverter
    fun fromList(list : List<String>) = Json.encodeToString(list)

    @TypeConverter
    fun toList(list: String) = Json.decodeFromString<List<String>>(list)
}