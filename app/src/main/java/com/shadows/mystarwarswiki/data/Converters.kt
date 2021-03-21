package com.shadows.mystarwarswiki.data

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

//type converters to be able to store a list in form of string in Room Database

class Converters {
    @TypeConverter
    fun fromList(list : List<String>) = Json.encodeToString(list)

    @TypeConverter
    fun toList(list: String) = Json.decodeFromString<List<String>>(list)
}