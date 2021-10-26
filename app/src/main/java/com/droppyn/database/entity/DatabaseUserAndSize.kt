package com.droppyn.database.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.droppyn.domain.User

data class DatabaseUserAndSize (
        @Embedded
        val user: DatabaseUser,
        @Relation(
                entity = DatabaseSize::class,
                parentColumn = "idDefultSize",
                entityColumn = "id",
        )
        val defultSize: DatabaseSizeAndBrand?
)

fun List<DatabaseUserAndSize>.asDomainModel(): List<User> {
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

fun databaseUserAndSizeToDomain(it: DatabaseUserAndSize): User{
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
