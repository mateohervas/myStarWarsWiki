package com.shadows.mystarwarswiki.data.dao

import androidx.room.*
import com.shadows.mystarwarswiki.data.models.CharacterEntity

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacter(characterEntity: CharacterEntity): Long

    @Delete
    suspend fun removeCharacter(characterEntity: CharacterEntity): Int

    @Query("SELECT * FROM characters_table")
    suspend fun getFavoriteCharacters():List<CharacterEntity>

    @Query("SELECT EXISTS (SELECT 1 FROM characters_table WHERE url LIKE :characterUrl)")
    suspend fun isCharacterFavorite(characterUrl:String): Boolean
}