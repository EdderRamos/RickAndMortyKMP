package com.edlabcode.rickmortyapp.data.remote

import com.edlabcode.rickmortyapp.data.remote.response.CharacterResponse
import com.edlabcode.rickmortyapp.data.remote.response.CharacterWrapperResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import kotlin.contracts.Returns

class ApiService(private val client: HttpClient) {

    suspend fun getSingleCharacter(id: String): CharacterResponse {
        return client.get("/api/character/$id").body()
    }

    suspend fun getALlCharacters(page: Int): CharacterWrapperResponse {
       return  client.get("/api/character") {
            parameter("page", page)
        }.body()
    }

}