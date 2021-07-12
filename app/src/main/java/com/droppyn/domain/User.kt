package com.droppyn.domain

data class User (
	val id : String,
	val username : String,
	val email : String,
	val firstname : String,
	val surname : String,
	val phone : Int,
	val photoURL : String,
	val defultSize : Size?
)