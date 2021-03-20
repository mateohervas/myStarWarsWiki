package com.shadows.mystarwarswiki.data.mappers

import com.shadows.core.models.local.StarWarsCharacter
import com.shadows.core.utils.NOT_AVAILABLE
import com.shadows.mystarwarswiki.data.models.CharacterEntity


fun CharacterEntity.toStarWarsCharacter(): StarWarsCharacter {
    return StarWarsCharacter(
        films = this.films,
        homeWorld = this.homeWorld,
        name = this.name,
        url = this.url,
        birthYear = this.birthYear,
        species = if(this.species== NOT_AVAILABLE) emptyList() else listOf(this.species),
        heightInInches = this.heightInInches,
        heightInCm = this.heightInCm
    )
}

fun StarWarsCharacter.toCharacterEntity(): CharacterEntity{
    return CharacterEntity(films = this.films,
        homeWorld = this.homeWorld,
        name = this.name,
        url = this.url,
        birthYear = this.birthYear,
        species = this.species.firstOrNull()?: NOT_AVAILABLE,
        heightInInches = this.heightInInches,
        heightInCm = this.heightInCm)
}

fun List<CharacterEntity>.toStarWarsCharacterList(): List<StarWarsCharacter>{
    return if(isNotEmpty()){
        map {
            it.toStarWarsCharacter()
        }
    }else{
        emptyList()
    }
}