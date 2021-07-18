package com.droppyn.domain

import com.droppyn.network.dto.SizeDTO

data class Size (
	val id : String,
	val us : Double,
	val uk : Double,
	val eu : String,
	val type : String,
	val brand : Brand
)

fun sizeToDTO(size: Size): SizeDTO = SizeDTO(
		id = size.id,
		us = size.us,
		uk = size.uk,
		eu = size.eu,
		type = size.type,
		brandId = brandToDTO(size.brand).id
)