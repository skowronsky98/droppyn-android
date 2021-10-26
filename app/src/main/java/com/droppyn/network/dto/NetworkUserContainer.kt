package com.droppyn.network.dto

import com.droppyn.database.entity.DatabaseProfile
import com.droppyn.database.entity.DatabaseUser
import com.droppyn.domain.Size
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkUserContainer(val user: UserDTO)

@JsonClass(generateAdapter = true)
data class UserDTO(
		@Json(name = "id")
		val id: String,
		val username: String,
		val email: String,
		val firstname: String,
		val surname: String,
		val phone: Int,
		val photoURL: String,
		val bio: String,
		val defultSize: Size?
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
			bio = user.bio,
			idDefultSize = user.defultSize?.id.toString()
		)

}

fun NetworkUserContainer.asDatabaseProfileModel(): DatabaseProfile {
	return DatabaseProfile(
			id = user.id,
			username = user.username,
			email = user.email,
			firstname = user.firstname,
			surname = user.surname,
			phone = user.phone,
			photoURL = user.photoURL,
			bio = user.bio,
			idDefultSize = user.defultSize?.id.toString()
	)

}

