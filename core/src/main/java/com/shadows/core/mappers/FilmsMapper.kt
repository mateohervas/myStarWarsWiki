package com.shadows.core.mappers

import com.shadows.core.models.local.StarWarsFilm
import com.shadows.core.models.remote.RemoteFilm
import com.shadows.core.utils.NOT_AVAILABLE

fun RemoteFilm.toStarWarsFilm(): StarWarsFilm{
    return StarWarsFilm(title = title?: NOT_AVAILABLE,
        openingCrawl = openingCrawl?:NOT_AVAILABLE,
        releasedDate = releasedDate?:NOT_AVAILABLE)
}

fun List<RemoteFilm>?.toSWFilmList(): List<StarWarsFilm>{
    return if(!this.isNullOrEmpty()){
        this.map {
            it.toStarWarsFilm()
        }
    }else emptyList()
}