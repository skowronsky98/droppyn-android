package com.droppyn.network.dto

import com.droppyn.database.entity.DatabaseToken
import com.droppyn.database.entity.DatabaseUser
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)
data class NetworkAccessTokenContainer(val tokensDTO: AccessTokensDTO)

@JsonClass(generateAdapter = true)
data class AccessTokensDTO(
        @Json(name = "access_token")
        val accessToken: String,
        @Json(name = "refresh_token")
        val refreshToken: String
)

fun NetworkAccessTokenContainer.asDatabaseModel(): DatabaseToken {
        return DatabaseToken(
                accessToken = tokensDTO.accessToken,
                refreshToken = tokensDTO.refreshToken
        )

}