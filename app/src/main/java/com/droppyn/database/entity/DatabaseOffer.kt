package com.droppyn.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "offers")
data class DatabaseOffer(
        @PrimaryKey
        val id : String,
        val price : Double,
        val active : Boolean,
        val deleted : Boolean,
        val bio : String,
        val idShoe : String,
        val idSize : String,
        val idUser : String
)
