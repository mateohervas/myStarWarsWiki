package com.shadows.core.mappers

import com.shadows.core.models.remote.RemotePlanet
import org.junit.Test

class PlanetMapperTest {

    @Test
    fun `given incomplete RemotePlanet map it to StarWarsPlanet`() {
        val remotePlanet = RemotePlanet(null,null)
        val starWarsPlanet = remotePlanet.toStarWarsPlanet()
        assert((starWarsPlanet.name.isNotEmpty()&&starWarsPlanet.population.isNotEmpty()))
    }
}