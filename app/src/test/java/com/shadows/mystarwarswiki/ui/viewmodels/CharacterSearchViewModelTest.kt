package com.shadows.mystarwarswiki.ui.viewmodels

import com.shadows.mystarwarswiki.BaseViewModelTest
import com.shadows.mystarwarswiki.domain.CharacterSearchRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test

class CharacterSearchViewModelTest: BaseViewModelTest() {

    private val repository = mockk<CharacterSearchRepository>()
    private lateinit var characterSearchViewModel: CharacterSearchViewModel

    @Before
    fun setUp() {
        characterSearchViewModel = CharacterSearchViewModel(repository)
    }

    @Test
    fun `request with string should get list`() {
        every { repository.getCharacterByName("") } returns flowOf(emptyList())
        characterSearchViewModel.getStarWarsCharacter("")
        io.mockk.verify { repository.getCharacterByName("") }
    }
}