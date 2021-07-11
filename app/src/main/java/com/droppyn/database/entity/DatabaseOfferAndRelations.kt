package com.droppyn.database.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.droppyn.domain.Offer

data class DatabaseOfferAndRelations(
        @Embedded
        val offer: DatabaseOffer,
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
                entity = DatabaseUser::class,
                parentColumn = "idUser",
                entityColumn = "id"
        )
        val user: DatabaseUserAndSize,
)

fun List<DatabaseOfferAndRelations>.asDomainModel(): List<Offer> {
        return map {
                Offer(
                        id = it.offer.id,
                        price = it.offer.price,
                        active = it.offer.active,
                        deleted = it.offer.deleted,
                        bio = it.offer.bio,
                        shoe = databaseShoesAndBrandToDomain(it.shoe),
                        size = databseSizeAndBrandToDomain(it.size),
                        user = databaseUserAndSizeToDomain(it.user)
                )
        }
}