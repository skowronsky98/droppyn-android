package com.droppyn.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.droppyn.domain.Brand
import com.droppyn.domain.Media

@Entity(tableName = "brands")
data class DatabaseBrand(
    @PrimaryKey
    val id : String,
    val name : String,
    @Embedded val media : Media
)

fun List<DatabaseBrand>.asDomainModel(): List<Brand> {
    return map {
        Brand(
            id = it.id,
            name = it.name,
            media = Media(
                it.media.imageUrl,
                it.media.smallImageUrl,
                it.media.thumbUrl
            )
        )
    }
}

fun databaseBrandtoDomain(it: DatabaseBrand): Brand{
    return Brand(
            id = it.id,
            name = it.name,
            media = Media(
                    it.media.imageUrl,
                    it.media.smallImageUrl,
                    it.media.thumbUrl
            )
    )
}
