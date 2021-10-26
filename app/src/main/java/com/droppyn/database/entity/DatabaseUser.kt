package com.droppyn.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.droppyn.domain.User

@Entity(tableName = "user")
data class DatabaseUser (
        @PrimaryKey
        val id : String,
        val username : String,
        val email : String,
        val firstname : String,
        val surname : String,
        val phone : String,
        val photoURL : String,
        val bio : String,
        val idDefultSize : String
)


