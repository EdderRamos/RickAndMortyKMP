package com.edlabcode.rickmortyapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.edlabcode.rickmortyapp.domain.model.CharacterModel
import com.edlabcode.rickmortyapp.domain.model.CharacterOfTheDayModel

@Entity(tableName = "character_of_the_day")
data class CharacterOfTheDayEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val image: String,
    val isAlive: Boolean,
    val selectedDay: String,
) {
    fun toDomain() = CharacterOfTheDayModel(
        characterModel = CharacterModel(id, isAlive, image, name),
        selectedDay = selectedDay
    )
}