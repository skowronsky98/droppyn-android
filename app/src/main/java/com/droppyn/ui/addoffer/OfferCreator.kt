package com.droppyn.ui.addoffer

import com.droppyn.domain.Offer
import com.droppyn.domain.Shoe
import com.droppyn.domain.Size
import com.droppyn.domain.User

class OfferCreator {
    lateinit var shoe: Shoe
    lateinit var size: Size
    var bio = ""
    var price = 0.0
    lateinit var user: User

    fun createOffer(): Offer?{

        if(!this::shoe.isInitialized || !this::size.isInitialized || !this::user.isInitialized) return null

        return Offer(
                id = "",
                price = price,
                active = true,
                deleted = false,
                bio = bio,
                shoe = shoe,
                size = size,
                user = user
        )
    }
}