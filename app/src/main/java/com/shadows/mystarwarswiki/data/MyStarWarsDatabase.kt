package com.shadows.mystarwarswiki.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shadows.mystarwarswiki.data.dao.CharacterDao
import com.shadows.mystarwarswiki.data.models.CharacterEntity

@Database(entities = [CharacterEntity::class],version = 1)
@TypeConverters(Converters::class)
abstract class MyStarWarsDatabase: RoomDatabase() {
    abstract fun getCharacterDao(): CharacterDao
}