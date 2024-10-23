package com.edlabcode.rickmortyapp.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class OriginResponse(
    val name: String,
    val url: String,
)
