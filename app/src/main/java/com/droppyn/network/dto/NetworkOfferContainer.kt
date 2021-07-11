package com.droppyn.network.dto

import com.droppyn.database.entity.DatabaseOffer
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class NetworkOfferContainer(val offers: List<OfferDTO>)

@JsonClass(generateAdapter = true)
data class OfferDTO (
	val id : String,
	val price : Double,
	val active : Boolean,
	val deleted : Boolean,
	val bio : String,
	val shoe : ShoeDTO,
	val size : SizeDTO,
	val user : UserDTO
)

fun NetworkOfferContainer.asDatabaseModel(): Array<DatabaseOffer> {
	return offers.map {
		DatabaseOffer(
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