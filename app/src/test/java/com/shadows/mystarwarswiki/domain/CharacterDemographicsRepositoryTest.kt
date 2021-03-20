package com.shadows.mystarwarswiki.domain

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.shadows.mystarwarswiki.BaseTest
import com.shadows.mystarwarswiki.dispatcher.DummyDataDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class CharacterDemographicsRepositoryTest: BaseTest() {

    private lateinit var characterDemographicsRepository: CharacterDemographicsRepository

    @Before
    fun setUp(){
        super.setup()
        characterDemographicsRepository= CharacterDemographicsRepository(starWarsAPI)
    }

    @Test
    fun `search for planet 1 and get result`() {
        runBlocking {
           val planet = characterDemographicsRepository.getPlanetByUrl(DummyDataDispatcher.SEARCH_FOR_PLANET)
            planet.collect {
                assert(it.name.isNotEmpty())
            }
        }

    }

    @Test
    fun `search for species 1 and get result`() {
        runBlocking {
            val species = characterDemographicsRepository.getSpeciesByUrl(DummyDataDispatcher.SEARCH_FOR_SPECIES)
            species.collect{
                assert(it.name.isNotEmpty())
            }
        }

    }
}