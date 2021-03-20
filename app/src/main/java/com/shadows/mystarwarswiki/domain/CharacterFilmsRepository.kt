package com.shadows.mystarwarswiki.domain

import com.shadows.core.api.MyStarWarsApi
import com.shadows.core.mappers.toSWFilmList
import com.shadows.core.mappers.toStarWarsFilm
import com.shadows.core.models.local.StarWarsFilm
import com.shadows.core.models.remote.RemoteFilm
import com.shadows.mystarwarswiki.utils.toHttps
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

//This repo is in charge of getting all the films of a determined character
class CharacterFilmsRepository @Inject constructor(private val starWarsApi: MyStarWarsApi) {

    suspend fun getFilmsByUrl(filmUrlsList: List<String>): Flow<List<StarWarsFilm>> = flow {
        val films: ArrayList<RemoteFilm> = ArrayList()
        filmUrlsList.forEach {
            films.add(starWarsApi.getFilmByUrl(it.toHttps()))
        }
        emit(films.toSWFilmList())
    }.flowOn(Dispatchers.IO)
}