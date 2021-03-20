package com.shadows.core.mappers


import com.shadows.core.models.remote.RemoteCharacter
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@RunWith(JUnit4::class)
class CharacterMapperTest {


    @get:Rule
    var rule: MockitoRule = MockitoJUnit.rule()


    @Test
    fun `given complete RemoteCharacter map it to StarWarsCharacter`() {
        val remoteCharacter = RemoteCharacter(null,null,"url",null,null,"",null)
        assertNotNull(remoteCharacter.toStarWarsCharacter())
    }

    @Test
    fun `given incomplete RemoteCharacter return null`() {
        val remoteCharacter = mock(RemoteCharacter::class.java)
        assertNull(remoteCharacter.toStarWarsCharacter())
    }

    @Test
    fun `given list of RemoteCharacter return list of StarWarsCharacter `() {
        val list = listOf(RemoteCharacter(null,null,"url",null,null,"",null))
        assert(!list.toStarWarsCharacterList().isNullOrEmpty())
    }
    @Test
    fun `given null list of RemoteCharacter return emptyList `() {
        val list = listOf(RemoteCharacter(null,null,null,null,null,"",null))
        assert(list.toStarWarsCharacterList().isEmpty())
    }


}