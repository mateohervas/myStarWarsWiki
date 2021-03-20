package com.shadows.mystarwarswiki.domain

import com.shadows.core.api.MyStarWarsApi
import com.shadows.core.mappers.toStarWarsFilm
import com.shadows.core.mappers.toStarWarsPlanet
import com.shadows.core.mappers.toStarWarsSpecies
import com.shadows.core.models.local.StarWarsPlanet
import com.shadows.core.models.local.StarWarsSpecies
import com.shadows.mystarwarswiki.utils.toHttps
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

//this repository is in charge of getting the demographic information (species and planet ) information of a character
class CharacterDemographicsRepository @Inject constructor(private val starWarsApi: MyStarWarsApi) {

    suspend fun getPlanetByUrl(planetUrl: String): Flow<StarWarsPlanet> = flow {
        val remotePlanet = starWarsApi.getPlanetByUrl(planetUrl.toHttps())
        emit(remotePlanet.toStarWarsPlanet())
    }.flowOn(Dispatchers.IO)

    suspend fun getSpeciesByUrl(speciesUrl: String): Flow<StarWarsSpecies> = flow {
        val remoteSpecies = starWarsApi.getSpeciesByUrl(speciesUrl.toHttps())
        emit(remoteSpecies.toStarWarsSpecies())
    }.flowOn(Dispatchers.IO)


}