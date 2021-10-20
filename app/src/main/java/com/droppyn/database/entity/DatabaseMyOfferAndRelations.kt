package com.droppyn.database.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.droppyn.domain.Offer

data class DatabaseMyOfferAndRelations(
        @Embedded
        val myOffer: DatabaseMyOffer,
        @Relation(
                entity = DatabaseShoe::class,
                parentColumn = "idShoe",
                entityColumn = "id"
        )
        val shoe: DatabaseShoesAndBrand,


        @Relation(
                entity = DatabaseSize::class,
                parentColumn = "idSize",
                entityColumn = "id"
        )
        val size: DatabaseSizeAndBrand,

        @Relation(
                entity = DatabaseProfile::class,
                parentColumn = "idUser",
                entityColumn = "id"
        )
        val user: DatabaseProfileAndSize,
)

fun List<DatabaseMyOfferAndRelations>.asDomainModel(): List<Offer> {
    return map {
        Offer(
                id = it.myOffer.id,
                price = it.myOffer.price,
                active = it.myOffer.active,
                deleted = it.myOffer.deleted,
                bio = it.myOffer.bio,
                shoe = databaseShoesAndBrandToDomain(it.shoe),
                size = databseSizeAndBrandToDomain(it.size),
                user = databaseProfileAndSizeToDomain(it.user)
        )
    }
}
