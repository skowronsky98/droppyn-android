package com.droppyn.network.dto

import com.droppyn.domain.Brand
import com.droppyn.domain.Media
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkBrandContainer(val brands: List<BrandDTO>)

@JsonClass(generateAdapter = true)
data class BrandDTO (

	@Json(name = "id") val id : String,
	val name : String,
	val media : MediaDTO
)

fun NetworkBrandContainer.asDomainModel(): List<Brand> {
	return brands.map {
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

//fun NetworkBrandContainer.asDatabaseModel(): Array<DatabaseAdvertisment> {
//	return advertisments.map {
//		DatabaseAdvertisment(
//			id = it.id,
//			firstname = it.firstname,
//			surname = it.surname,
//			age = it.age,
//			bio = it.bio,
//			active = it.active,
//			price = it.price,
//			description = it.description
//		)
//	}.toTypedArray()
//}