package com.edlabcode.rickmortyapp.data.remote.response

import com.edlabcode.rickmortyapp.domain.model.CharacterModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable()
data class CharacterResponse(
    @SerialName("id") val id: Int,
    @SerialName("status") val status: String,
    @SerialName("image") val image: String,
    @SerialName("name") val name: String,
    @SerialName("species") val species: String,
    @SerialName("gender") val gender: String,
    @SerialName("origin") val origin: OriginResponse,
    @SerialName("episode") val episodes: List<String>
) {
    fun toDomain(): CharacterModel {
        return CharacterModel(
            id = id,
            image = image,
            isAlive = status.lowercase() == "alive",
            name = name,
            species = species,
            gender = gender,
            origin = origin.name,
            episodes = episodes.map { it.substringAfterLast("/") }
        )
    }
}
