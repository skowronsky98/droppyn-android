package com.droppyn.domain

import com.droppyn.network.dto.BrandDTO
import com.droppyn.network.dto.MediaDTO

data class Brand (

	val id : String,
	val name : String,
	val media : Media
)

fun brandToDTO(brand: Brand): BrandDTO = BrandDTO(
		id = brand.id,
		name = brand.name,
		media = MediaDTO(
				imageUrl = brand.media.imageUrl,
				smallImageUrl = brand.media.smallImageUrl,
				thumbUrl = brand.media.thumbUrl
		)
)