package com.droppyn.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.droppyn.domain.Brand
import com.droppyn.domain.Media

@Entity(tableName = "shoes")
data class DatabaseShoe (
    @PrimaryKey
    val id : String,
    val model : String,
    val brandId : String,
    @Embedded
    val media : Media
)

