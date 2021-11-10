package com.droppyn.database.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.droppyn.domain.Brand
import com.droppyn.domain.Size

data class DatabaseSizeAndBrand (
        @Embedded
        val size: DatabaseSize,
        @Relation(
                parentColumn = "brandId",
                entityColumn = "id"
        )
        val brand: DatabaseBrand
)

fun List<DatabaseSizeAndBrand>.asDomainModel(): List<Size> {
    return map {
        Size(
                id = it.size.id,
                us = it.size.us,
                uk = it.size.uk,
                eu = it.size.eu,
                type = it.size.type,
                brand = databaseBrandtoDomain(it.brand)
        )
    }
}

fun databseSizeAndBrandToDomain(it: DatabaseSizeAndBrand): Size{
    return Size(
            id = it.size.id,
            us = it.size.us,
            uk = it.size.uk,
            eu = it.size.eu,
            type = it.size.type,
            brand = databaseBrandtoDomain(it.brand)
    )
}