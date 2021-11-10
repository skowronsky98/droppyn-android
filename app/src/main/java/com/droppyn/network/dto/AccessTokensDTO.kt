package com.droppyn.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccessTokensDTO(
        @Json(name = "access_token")
        val accessToken: String,
        @Json(name = "refresh_token")
        val refreshToken: String
)