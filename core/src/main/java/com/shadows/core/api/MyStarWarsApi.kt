package com.shadows.core.api

import com.shadows.core.models.remote.*
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface MyStarWarsApi {

    @GET("people/")
    suspend fun getCharacterByName(@Query("search") name: String): ApiResponse<RemoteCharacter>

    @GET
    suspend fun getSpeciesByUrl(@Url speciesUrl: String): RemoteSpecies

    @GET
    suspend fun getFilmByUrl(@Url filmUrl: String): RemoteFilm

    @GET
    suspend fun getPlanetByUrl(@Url characterUrl: String): RemotePlanet


}