package com.droppyn.domain

data class Offer (

	val id : String,
	val price : Double,
	val active : Boolean,
	val deleted : Boolean,
	val bio : String,
	val shoe : Shoe,
	val size : Size,
	val user : User
)