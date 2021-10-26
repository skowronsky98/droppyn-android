package com.droppyn.database.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.droppyn.domain.User

data class DatabaseProfileAndSize (
    @Embedded
    val user: DatabaseProfile,
    @Relation(
        entity = DatabaseSize::class,
        parentColumn = "idDefultSize",
        entityColumn = "id",
    )
    val defultSize: DatabaseSizeAndBrand?
)

fun List<DatabaseProfileAndSize>.asDomainModel(): List<User> {
    return map {
        User(
            id = it.user.id,
            username = it.user.username,
            email = it.user.email,
            firstname = it.user.firstname,
            surname = it.user.surname,
            phone = it.user.phone,
            photoURL = it.user.photoURL,
            bio = it.user.bio,
            defultSize = it.defultSize?.let { size -> databseSizeAndBrandToDomain(size) }
        )
    }
}

fun databaseProfileAndSizeToDomain(it: DatabaseProfileAndSize): User {
    return User(
        id = it.user.id,
        username = it.user.username,
        email = it.user.email,
        firstname = it.user.firstname,
        surname = it.user.surname,
        phone = it.user.phone,
        photoURL = it.user.photoURL,
        bio = it.user.bio,
        defultSize = it.defultSize?.let { size -> databseSizeAndBrandToDomain(size) }
    )
}
