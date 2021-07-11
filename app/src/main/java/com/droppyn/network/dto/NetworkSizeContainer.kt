package com.droppyn.network.dto

import com.droppyn.database.entity.DatabaseBrand
import com.droppyn.database.entity.DatabaseSize
import com.droppyn.database.entity.databaseBrandtoDomain
import com.droppyn.domain.Media
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkSizeContainer(val sizechart: List<SizeDTO>)

@JsonClass(generateAdapter = true)
data class SizeDTO (
		@Json(name = "id") val id : String,
		val us : Double,
		val uk : Double,
		val eu : String,
		val type : String,
		val brandId : String
)


fun NetworkSizeContainer.asDatabaseModel(): Array<DatabaseSize> {
	return sizechart.map {
		DatabaseSize(
				id = it.id,
				us = it.us,
				uk = it.uk,
				eu = it.eu,
				type = it.type,
				brandId = it.brandId
		)
	}.toTypedArray()
}