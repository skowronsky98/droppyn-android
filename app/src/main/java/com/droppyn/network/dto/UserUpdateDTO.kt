package com.droppyn.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserUpdateDTO(
        @Json(name = "id")
        val id: String,
        val email: String,
        val firstname: String?,
        val surname: String?,
        val phone: String?,
        val photoURL: String?,
        val bio: String?,
        val idDefultSize: String?
)
