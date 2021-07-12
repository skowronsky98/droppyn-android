package com.droppyn.network.dto

import com.droppyn.database.entity.DatabaseMyOffer
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkMyOfferContainer(val myOffers: List<MyOfferDTO>)


@JsonClass(generateAdapter = true)
data class MyOfferDTO (
        val id : String,
        val price : Double,
        val active : Boolean,
        val deleted : Boolean,
        val bio : String,
        val shoe : ShoeDTO,
        val size : SizeDTO,
        val user : UserDTO
)

fun NetworkMyOfferContainer.asDatabaseModel(): Array<DatabaseMyOffer> {
    return myOffers.map {
        DatabaseMyOffer(
                id = it.id,
                price = it.price,
                active = it.active,
                deleted = it.deleted,
                bio = it.bio,
                idShoe = it.shoe.id,
                idSize = it.size.id,
                idUser = it.user.id
        )
    }.toTypedArray()
}