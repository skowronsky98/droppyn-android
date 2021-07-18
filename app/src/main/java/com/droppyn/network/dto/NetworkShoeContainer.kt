package com.droppyn.network.dto

import com.droppyn.database.entity.DatabaseShoe
import com.droppyn.domain.Media
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class NetworkShoeContainer(val shoes: List<ShoeDTO>)

@JsonClass(generateAdapter = true)
data class ShoeDTO (

		@Json(name = "_id") val id : String,
		val model : String,
		val brand : BrandDTO,
		val media : MediaDTO
)

fun NetworkShoeContainer.asDatabaseModel(): Array<DatabaseShoe> {
	return shoes.map {
		DatabaseShoe(
				id = it.id,
				model = it.model,
				brandId = it.brand.id,
				media = Media(
						it.media.imageUrl,
						it.media.smallImageUrl,
						it.media.thumbUrl
				)
		)
	}.toTypedArray()
}

fun toDatabaseShoe(it: ShoeDTO): DatabaseShoe{
	return DatabaseShoe(
		id = it.id,
		model = it.model,
		brandId = it.brand.id,
		media = Media(
			it.media.imageUrl,
			it.media.smallImageUrl,
			it.media.thumbUrl
		)
	)
}