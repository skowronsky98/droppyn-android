package com.droppyn.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sizechart")
data class DatabaseSize (
        @PrimaryKey
        val id : String,
        val us : Int,
        val uk : Double,
        val eu : String,
        val type : String,
        val brandId : String
)