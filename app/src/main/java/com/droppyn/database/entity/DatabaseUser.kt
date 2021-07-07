package com.droppyn.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.droppyn.domain.User

@Entity(tableName = "user")
data class DatabaseUser (
        @PrimaryKey
        val id : String,
        val username : String,
        val email : String,
        val firstname : String,
        val surname : String,
        val phone : Int,
        val photoURL : String,
        val idDefultSize : String
)
//fun List<DatabaseUser>.asDomainModel(): List<User> {
//        return map{
//                User(
//                        id = it.id,
//                        username = it.username
//                        email = it.email,
//                        firstname = it.firstname,
//                        surname = it.surname,
//
//                )
//
//        }
//}