package com.droppyn.network.dto

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