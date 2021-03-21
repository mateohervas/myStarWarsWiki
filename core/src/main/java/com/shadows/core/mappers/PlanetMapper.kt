package com.shadows.core.mappers

import com.shadows.core.models.local.StarWarsPlanet
import com.shadows.core.models.remote.RemotePlanet
import com.shadows.core.utils.NOT_AVAILABLE

//this mapper is in charge of mapping to StarWars Planet
fun RemotePlanet?.toStarWarsPlanet(): StarWarsPlanet{
    return if(this!=null){
        StarWarsPlanet(name = name?: NOT_AVAILABLE, population = population?:NOT_AVAILABLE)

    }else{StarWarsPlanet(NOT_AVAILABLE,NOT_AVAILABLE)}
}