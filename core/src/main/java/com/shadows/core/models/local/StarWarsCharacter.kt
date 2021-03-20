package com.shadows.core.models.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StarWarsCharacter(val films: List<String>,
                             val homeWorld: String,
                             val url: String,
                             val birthYear: String,
                             val species: List<String>,
                             val name: String,
                             val heightInCm: String,
                             val heightInInches: String):Parcelable
