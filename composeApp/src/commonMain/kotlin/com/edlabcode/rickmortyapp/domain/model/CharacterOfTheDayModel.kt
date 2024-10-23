package com.edlabcode.rickmortyapp.domain.model

import com.edlabcode.rickmortyapp.data.database.entity.CharacterOfTheDayEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

data class CharacterOfTheDayModel(
    val characterModel: CharacterModel,
    val selectedDay: String
) {
    fun toEntity() = CharacterOfTheDayEntity(
        id = characterModel.id,
        name = characterModel.name,
        isAlive = characterModel.isAlive,
        image = characterModel.image,
        selectedDay = selectedDay,
        species = characterModel.species,
        gender = characterModel.gender,
        origin = characterModel.origin,
        episodes = Json.encodeToString(characterModel.episodes)
    )
}