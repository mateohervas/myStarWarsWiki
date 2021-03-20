package com.shadows.mystarwarswiki.domain

import android.util.Log
import com.shadows.core.api.MyStarWarsApi
import com.shadows.core.mappers.toStarWarsCharacterList
import com.shadows.core.models.local.StarWarsCharacter
import com.shadows.core.models.remote.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

//this class is used to deal with the business logic of looking up a character.
// It also handles the saving and retrieving of characters in our Room Database

class CharacterSearchRepository @Inject constructor(private val myStarWarsApi: MyStarWarsApi) {

    fun getCharacterByName(name: String): Flow<List<StarWarsCharacter>> = flow {
        val list = myStarWarsApi.getCharacterByName(name).results.toStarWarsCharacterList()
        emit(list)
    }.flowOn(Dispatchers.IO)


}