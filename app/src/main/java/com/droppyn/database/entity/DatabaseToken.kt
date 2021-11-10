package com.droppyn.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "token")
data class DatabaseToken(
    @PrimaryKey
    val accessToken: String,
    val refreshToken: String
)
