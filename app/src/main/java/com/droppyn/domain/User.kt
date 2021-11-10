package com.droppyn.domain

import com.droppyn.network.dto.UserDTO

data class User (
	val id : String,
	val username : String,
	val email : String,
	val firstname : String?,
	val surname : String?,
	val phone : String?,
	val photoURL : String?,
	val bio : String?,
	val defultSize : Size?
)

fun userToDTO(user: User): UserDTO = UserDTO(
		id = user.id,
		username = user.username,
		email = user.email,
		firstname = user.firstname,
		surname = user.surname,
		phone = user.phone,
		photoURL = user.photoURL,
		bio = user.bio,
		defultSize = user.defultSize
)