package com.edlabcode.rickmortyapp.domain

import com.edlabcode.rickmortyapp.domain.model.CharacterModel
import com.edlabcode.rickmortyapp.domain.model.CharacterOfTheDayModel
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GetRandomCharacter(private val repository: Repository) {
    suspend operator fun invoke(): CharacterModel {
        val characterOfDay = repository.getCharacterDB()
        val selectedDay = getCurrentDayOfTheYear()
        return if (characterOfDay != null && characterOfDay.selectedDay == selectedDay) {
            characterOfDay.characterModel
        } else {
            val result = getRandomCharacter()
            repository.saveCharacterDB(CharacterOfTheDayModel(result, selectedDay))
            result
        }
    }

    private suspend fun getRandomCharacter(): CharacterModel {
        val random = (1..826).random()
        return repository.getSingleCharacter(random.toString())
    }

    private fun getCurrentDayOfTheYear(): String {
        val instant = Clock.System.now()
        val localTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return "${localTime.dayOfYear}${localTime.year}"
    }
}