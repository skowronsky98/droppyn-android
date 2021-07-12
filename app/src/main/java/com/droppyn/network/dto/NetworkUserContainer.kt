package com.droppyn.network.dto

import com.droppyn.database.entity.DatabaseUser
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkUserContainer(val user: UserDTO)

@JsonClass(generateAdapter = true)
data class UserDTO (

	val id : String,
	val username : String,
	val email : String,
	val firstname : String,
	val surname : String,
	val phone : Int,
	val photoURL : String,
	val defultSize : SizeDTO?
)

fun NetworkUserContainer.asDatabaseModel(): DatabaseUser {
	return DatabaseUser(
			id = user.id,
			username = user.username,
			email = user.email,
			firstname = user.firstname,
			surname = user.surname,
			phone = user.phone,
			photoURL = user.photoURL,
			idDefultSize = user.defultSize?.id.toString()
		)

}

