package com.droppyn.domain

import com.droppyn.network.dto.MediaDTO
import com.droppyn.network.dto.ShoeDTO

data class Shoe (

	val id : String,
	val model : String,
	val brand : Brand,
	val media : Media
)

fun shoeToDTO(shoe: Shoe): ShoeDTO = ShoeDTO(
		id = shoe.id,
		model = shoe.model,
		brand = brandToDTO(shoe.brand),
		media = MediaDTO(
				imageUrl = shoe.media.imageUrl,
				smallImageUrl = shoe.media.smallImageUrl,
				thumbUrl = shoe.media.thumbUrl
		)
)