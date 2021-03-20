package com.shadows.mystarwarswiki.data.mappers

import com.shadows.core.models.local.StarWarsCharacter
import com.shadows.mystarwarswiki.data.models.CharacterEntity
import io.mockk.mockk
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class CharacterEntityMapperTest {

    @Test
    fun `map StarWarsCharacter to CharacterEntity`() {
        val starWarsCharacter = StarWarsCharacter(emptyList(),"","","", emptyList(),"","","")
        assertNotNull(starWarsCharacter.toCharacterEntity())
    }

    @Test
    fun `map CharacterEntity to StarWarsCharacter`() {
        val characterEntity = CharacterEntity("","","","", emptyList(),"","","")
        assertNotNull(characterEntity.toStarWarsCharacter())
    }

    @Test
    fun `CharacterEntity list to StarWarsCharacter List`() {
        val list = listOf(CharacterEntity("","","","", emptyList(),"","",""))
        assertNotNull(list.toStarWarsCharacterList())
    }
}