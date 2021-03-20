package com.shadows.mystarwarswiki.domain

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.shadows.mystarwarswiki.BaseTest
import com.shadows.mystarwarswiki.dispatcher.DummyDataDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class CharacterFilmRepositoryTest: BaseTest() {

    private lateinit var characterFilmsRepository: CharacterFilmsRepository

    @Before
    fun setUp(){
        super.setup()
        characterFilmsRepository = CharacterFilmsRepository(starWarsAPI)
    }

    @Test
    fun `look for film and get film`(){
        runBlocking {
           val films =  characterFilmsRepository.getFilmsByUrl(listOf(DummyDataDispatcher.SEARCH_FOR_FILMS))
            films.collect {
                assert(it.isNotEmpty())
            }
        }
    }

}