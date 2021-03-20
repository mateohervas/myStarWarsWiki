package com.shadows.mystarwarswiki.ui.viewmodels

import com.jraska.livedata.test
import com.shadows.core.models.local.StarWarsPlanet
import com.shadows.core.models.local.StarWarsSpecies
import com.shadows.mystarwarswiki.BaseViewModelTest
import com.shadows.mystarwarswiki.domain.CharacterDemographicsRepository
import com.shadows.mystarwarswiki.domain.CharacterFilmsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.junit.Before

class CharactersDetailViewModelTest: BaseViewModelTest() {

    private val repository = mockk<CharacterDemographicsRepository>()
    private val filmsRepository = mockk<CharacterFilmsRepository>()
    private lateinit var viewmodel: CharactersDetailViewModel

    @Before
    fun setUp(){
        viewmodel = CharactersDetailViewModel(repository,filmsRepository)
    }

    @Test
    fun `Demographic Information should get specie planet and films`() {
        coEvery { repository.getPlanetByUrl("") } returns flowOf(StarWarsPlanet("",""))
        coEvery { repository.getSpeciesByUrl("") } returns flowOf(StarWarsSpecies("",""))
        coEvery { filmsRepository.getFilmsByUrl(listOf("")) } returns flowOf(ArrayList())
        viewmodel.getDemographicInformation("","", listOf(""))
        viewmodel.demographicDetails.test().assertHasValue()
    }
}