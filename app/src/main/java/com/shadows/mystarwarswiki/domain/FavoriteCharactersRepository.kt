package com.shadows.mystarwarswiki.domain

import com.shadows.core.models.local.StarWarsCharacter
import com.shadows.mystarwarswiki.data.dao.CharacterDao
import com.shadows.mystarwarswiki.data.mappers.toCharacterEntity
import com.shadows.mystarwarswiki.data.mappers.toStarWarsCharacterList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FavoriteCharactersRepository @Inject constructor(private val characterDao: CharacterDao) {

    fun saveCharacter(starWarsCharacter: StarWarsCharacter) = flow {
        emit(characterDao.insertCharacter(starWarsCharacter.toCharacterEntity()))
    }.flowOn(Dispatchers.IO)


    fun deleteCharacter(starWarsCharacter: StarWarsCharacter) = flow{
        emit(characterDao.removeCharacter(starWarsCharacter.toCharacterEntity()))
    }.flowOn(Dispatchers.IO)

    fun getFavoriteCharacters() = flow{
        characterDao.getFavoriteCharacters()
        emit(characterDao.getFavoriteCharacters().toStarWarsCharacterList())
    }.flowOn(Dispatchers.IO)

    fun isCharacterFavorite(characterUrl:String) = flow {
        emit(characterDao.isCharacterFavorite(characterUrl))
    }.flowOn(Dispatchers.IO)

}