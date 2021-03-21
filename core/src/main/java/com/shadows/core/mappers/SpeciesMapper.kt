package com.shadows.core.mappers

import com.shadows.core.models.local.StarWarsSpecies
import com.shadows.core.models.remote.RemoteSpecies
import com.shadows.core.utils.NOT_AVAILABLE

//this mapper is in charge of mapping to StarWars Species
fun RemoteSpecies.toStarWarsSpecies(): StarWarsSpecies{
    return StarWarsSpecies(
            name = name ?: NOT_AVAILABLE,
            language = language?:NOT_AVAILABLE)

}

fun List<RemoteSpecies>.toSWlist(): List<StarWarsSpecies>{
    return if(!this.isNullOrEmpty()){
        this.map {
            it.toStarWarsSpecies()
        }
    }else emptyList()
}