package com.shadows.core.mappers

import com.shadows.core.models.local.StarWarsCharacter
import com.shadows.core.models.remote.RemoteCharacter
import com.shadows.core.utils.NOT_AVAILABLE
import com.shadows.core.utils.toInches

//mappers file that will follow clean guidelines and return local objects that the presentation layer can use.

//this method converts a remote model of a character into the one who the presentation layer will use. Therefore it is mandatory
// that its attributes can not be null. This function will return null if the url or name are null by any chance from the API
//it also calls the ext function that calculates the height in inches
fun RemoteCharacter.toStarWarsCharacter(): StarWarsCharacter? {
    return StarWarsCharacter(films = films?: emptyList(),
        homeWorld = homeWorld?: NOT_AVAILABLE,
        url = url?:return null,
        birthYear = birthYear?:NOT_AVAILABLE,
        species = species?: emptyList(),
        name = name?:return null,
        heightInCm = height?:"0",
        heightInInches = height.toInches()
    )
}
//this function maps a whole list of remote models to a list of local models.
// It avoids any remote character that could be null with the mapnotnull function
fun List<RemoteCharacter>?.toStarWarsCharacterList(): List<StarWarsCharacter>{
    return if(!isNullOrEmpty()){
        mapNotNull {
            it.toStarWarsCharacter()
        }
    }else emptyList()
}