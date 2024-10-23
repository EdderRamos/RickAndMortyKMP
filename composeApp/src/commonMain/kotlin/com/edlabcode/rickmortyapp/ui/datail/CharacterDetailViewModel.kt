package com.edlabcode.rickmortyapp.ui.datail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edlabcode.rickmortyapp.domain.Repository
import com.edlabcode.rickmortyapp.domain.model.CharacterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterDetailViewModel(
    characterModel: CharacterModel,
    val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterDetailState(characterModel))
    val state = _state.asStateFlow()

    init {

        getEpisodesForCharacter(characterModel.episodes)
    }

    private fun getEpisodesForCharacter(episodes: List<String>) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getEpisodeForCharacter(episodes)
            }
            _state.update { it.copy(episodes = result) }

        }
    }
}