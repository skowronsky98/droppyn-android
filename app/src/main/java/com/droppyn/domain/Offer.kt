package com.droppyn.domain

data class Offer (

        val id : String,
        var price : Double,
        val active : Boolean,
        val deleted : Boolean,
        var bio : String,
        val shoe : Shoe,
        val size : Size,
        val user : User
)