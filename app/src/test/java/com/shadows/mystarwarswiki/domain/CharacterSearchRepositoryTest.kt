package com.shadows.mystarwarswiki.domain

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.shadows.mystarwarswiki.BaseTest
import com.shadows.mystarwarswiki.dispatcher.DummyDataDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class CharacterSearchRepositoryTest: BaseTest() {

    private lateinit var characterSearchRepository: CharacterSearchRepository

    @Before
    fun setUp() {
        super.setup()
        characterSearchRepository = CharacterSearchRepository(starWarsAPI)
    }

    @Test
    fun `search for luke and get result`() {
        runBlocking {
            val characterResponse = characterSearchRepository.getCharacterByName(
                DummyDataDispatcher.SEARCH_FOR_LUKE
            )
            characterResponse.collect {
                assert(it.isNotEmpty())
            }
        }
    }
    @Test
    fun `search for mateo and get empty`() {
        runBlocking {
            val characterResponse = characterSearchRepository.getCharacterByName(
                DummyDataDispatcher.SEARCH_FOR_EMPTY
            )
            characterResponse.collect {
                assert(it.isEmpty())
            }
        }
    }

}