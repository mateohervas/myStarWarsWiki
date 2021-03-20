package com.shadows.mystarwarswiki.ui.adapters.search

import com.shadows.core.models.local.StarWarsCharacter

interface CharacterClickListener {
    fun goToCharacterDetailActivity(starWarsCharacter: StarWarsCharacter)
}