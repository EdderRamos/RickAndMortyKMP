package com.edlabcode.rickmortyapp.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class CharacterWrapperResponse(
    val info: InfoResponse,
    val results: List<CharacterResponse>
)
