package com.edlabcode.rickmortyapp.data.remote

import com.edlabcode.rickmortyapp.data.remote.response.CharacterResponse
import com.edlabcode.rickmortyapp.data.remote.response.CharacterWrapperResponse
import com.edlabcode.rickmortyapp.data.remote.response.EpisodesResponse
import com.edlabcode.rickmortyapp.data.remote.response.EpisodesWrapperResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ApiService(private val client: HttpClient) {

    suspend fun getSingleCharacter(id: String): CharacterResponse {
        return client.get("/api/character/$id").body()
    }

    suspend fun getALlCharacters(page: Int): CharacterWrapperResponse {
        return client.get("/api/character") {
            parameter("page", page)
        }.body()
    }

    suspend fun getAllEpisodes(page: Int): EpisodesWrapperResponse {
        return client.get("/api/episode") {
            parameter("page", page)
        }.body()
    }

    suspend fun getEpisodes(episodes: String): List<EpisodesResponse> {
        return client.get("/api/episode/$episodes").body()
    }

    suspend fun getSingleEpisode(first: String): EpisodesResponse {
        return client.get("/api/episode/$first").body()
    }

}