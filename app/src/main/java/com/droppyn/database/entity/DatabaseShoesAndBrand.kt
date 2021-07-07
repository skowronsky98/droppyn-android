package com.droppyn.database.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.droppyn.domain.Brand
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
                        brand = Brand(
                                id = it.brand.id,
                                name = it.brand.name,
                                media = it.brand.media
                        ),
                        media = it.shoe.media
                )
        }
}