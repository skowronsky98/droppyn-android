package com.droppyn.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class DatabaseProfile (
    @PrimaryKey
    val id : String,
    val username : String,
    val email : String,
    val firstname : String,
    val surname : String,
    val phone : Int,
    val photoURL : String,
    val idDefultSize : String
)
