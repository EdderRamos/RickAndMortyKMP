package com.edlabcode.rickmortyapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.edlabcode.rickmortyapp.domain.model.CharacterModel
import com.edlabcode.rickmortyapp.domain.model.CharacterOfTheDayModel
import kotlinx.serialization.json.Json

@Entity(tableName = "character_of_the_day")
data class CharacterOfTheDayEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val image: String,
    val isAlive: Boolean,
    val selectedDay: String,
    val species: String,
    val gender: String,
    val origin: String,
    val episodes: String,
) {
    fun toDomain() = CharacterOfTheDayModel(
        characterModel = CharacterModel(
            id = id,
            isAlive = isAlive,
            image = image,
            name = name,
            species = species,
            gender = gender,
            origin = origin,
            episodes = Json.decodeFromString(episodes)
        ),
        selectedDay = selectedDay
    )
}