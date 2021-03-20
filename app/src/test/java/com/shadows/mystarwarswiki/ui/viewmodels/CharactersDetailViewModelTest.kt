package com.shadows.mystarwarswiki.ui.viewmodels

import com.jraska.livedata.test
import com.shadows.core.models.local.StarWarsCharacter
import com.shadows.core.models.local.StarWarsPlanet
import com.shadows.core.models.local.StarWarsSpecies
import com.shadows.mystarwarswiki.BaseViewModelTest
import com.shadows.mystarwarswiki.domain.CharacterDemographicsRepository
import com.shadows.mystarwarswiki.domain.CharacterFilmsRepository
import com.shadows.mystarwarswiki.domain.FavoriteCharactersRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.junit.Before

class CharactersDetailViewModelTest: BaseViewModelTest() {

    private val repository = mockk<CharacterDemographicsRepository>()
    private val filmsRepository = mockk<CharacterFilmsRepository>()
    private val favoriteCharacterRepository = mockk<FavoriteCharactersRepository>()
    private lateinit var viewmodel: CharactersDetailViewModel

    @Before
    fun setUp(){
        viewmodel = CharactersDetailViewModel(repository,filmsRepository,favoriteCharacterRepository)
    }

    @Test
    fun `Demographic Information should get specie planet and films`() {
        coEvery { repository.getPlanetByUrl("") } returns flowOf(StarWarsPlanet("",""))
        coEvery { repository.getSpeciesByUrl("") } returns flowOf(StarWarsSpecies("",""))
        coEvery { filmsRepository.getFilmsByUrl(listOf("")) } returns flowOf(ArrayList())
        viewmodel.getDemographicInformation("","", listOf(""))
        viewmodel.demographicDetails.test().assertHasValue()
    }

    @Test
    fun `Save favorite character should be successful`(){
        val starWarsCharacter = mockk<StarWarsCharacter>()
        coEvery { favoriteCharacterRepository.saveCharacter(starWarsCharacter)  } returns flowOf(1L)
        viewmodel.saveFavoriteCharacter(starWarsCharacter)
        viewmodel.favoriteCharacter.test().assertHasValue()
    }

    @Test
    fun `Delete favorite character should be successful`(){
        val starWarsCharacter = mockk<StarWarsCharacter>()
        coEvery { favoriteCharacterRepository.deleteCharacter(starWarsCharacter)  } returns flowOf(1)
        viewmodel.deleteFavoriteCharacter(starWarsCharacter)
        viewmodel.favoriteCharacter.test().assertHasValue()
    }


}