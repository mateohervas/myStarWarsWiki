package com.shadows.core.mappers

import com.shadows.core.models.remote.RemoteFilm
import com.shadows.core.models.remote.RemoteSpecies
import org.junit.Assert.*
import org.junit.Test

class SpeciesMapperTest(){

    @Test
    fun `given incomplete RemoteSpecies map it to StarWarsSpecies`() {
        val remoteSpecies = RemoteSpecies(null,null)
        val starWarsSpecies = remoteSpecies.toStarWarsSpecies()
        assert((starWarsSpecies.name.isNotEmpty()&&starWarsSpecies.language.isNotEmpty()))
    }

    @Test
    fun `given list of RemoteSpecies return list of StarWarsSpecies `() {
        val list = listOf(RemoteSpecies(null,null))
        assert(!list.toSWlist().isNullOrEmpty())
    }
}