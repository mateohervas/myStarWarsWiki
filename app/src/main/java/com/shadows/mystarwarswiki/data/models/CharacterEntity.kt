package com.shadows.mystarwarswiki.data.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters_table")
data class CharacterEntity(
	val name: String,
	@PrimaryKey(autoGenerate = false) @ColumnInfo(name = "url")
	val url: String,
	val homeWorld: String,
	val species: String,
	val films: List<String>,
	val birthYear: String,
	val heightInCm: String,
	val heightInInches: String
)

