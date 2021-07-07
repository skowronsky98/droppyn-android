package com.droppyn.database.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.droppyn.domain.Shoe

data class DatabaseShoesAndBrand(
        @Embedded
        val shoe: DatabaseShoe,
        @Relation(
                parentColumn = "brandId",
                entityColumn = "id"
        )
        val brand: DatabaseBrand
)

fun List<DatabaseShoesAndBrand>.asDomainModel(): List<Shoe> {
        return map {
                Shoe(
                        id = it.shoe.id,
                        model = it.shoe.model,
                        brand = databaseBrandtoDomain(it.brand),
                        media = it.shoe.media
                )
        }
}