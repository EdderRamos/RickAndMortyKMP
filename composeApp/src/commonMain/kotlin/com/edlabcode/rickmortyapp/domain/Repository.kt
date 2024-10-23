package com.edlabcode.rickmortyapp.domain

import androidx.paging.PagingData
import com.edlabcode.rickmortyapp.domain.model.CharacterModel
import com.edlabcode.rickmortyapp.domain.model.CharacterOfTheDayModel
import com.edlabcode.rickmortyapp.domain.model.EpisodeModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getSingleCharacter(id: String): CharacterModel
    fun getAllCharacters(): Flow<PagingData<CharacterModel>>
    suspend fun getCharacterDB(): CharacterOfTheDayModel?
    suspend fun saveCharacterDB(characterOfTheDayModel: CharacterOfTheDayModel)
    fun getAllEpisodes(): Flow<PagingData<EpisodeModel>>
    suspend fun getEpisodeForCharacter(episodes: List<String>): List<EpisodeModel>
}
