package com.shadows.mystarwarswiki.ui.viewmodels

import com.jraska.livedata.test
import com.shadows.mystarwarswiki.BaseViewModelTest
import com.shadows.mystarwarswiki.domain.FavoriteCharactersRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test

class FavoriteCharacterViewModelTest: BaseViewModelTest() {

    private  var favoriteCharactersRepository = mockk<FavoriteCharactersRepository>()

    private lateinit var favoriteCharactersViewModel: FavoriteCharactersViewModel

    @Before
    fun setUp(){
        favoriteCharactersViewModel = FavoriteCharactersViewModel(favoriteCharactersRepository)
    }

    @Test
    fun `Get all characters from room database`(){
        coEvery { favoriteCharactersRepository.getFavoriteCharacters() } returns flowOf(emptyList())
        favoriteCharactersViewModel.getFavoriteCharacters()
        favoriteCharactersViewModel.favoriteCharacters.test().assertHasValue()
    }
}