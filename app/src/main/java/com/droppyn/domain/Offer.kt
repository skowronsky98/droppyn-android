package com.droppyn.domain

import com.droppyn.database.entity.DatabaseMyOffer
import com.droppyn.database.entity.DatabaseOffer
import com.droppyn.network.dto.MyOfferDTO

data class Offer (
        var id : String,
        var price : Double,
        var active : Boolean,
        var deleted : Boolean,
        var bio : String,
        var shoe : Shoe,
        var size : Size,
        var user : User
)

fun offertoDatabaseOffer(offer: Offer): DatabaseOffer = DatabaseOffer(
        id = offer.id,
        price = offer.price,
        active = offer.active,
        deleted = offer.deleted,
        bio = offer.bio,
        idShoe = offer.shoe.id,
        idSize = offer.size.id,
        idUser = offer.user.id
)

fun offertoDatabaseMyOffer(offer: Offer): DatabaseMyOffer = DatabaseMyOffer(
        id = offer.id,
        price = offer.price,
        active = offer.active,
        deleted = offer.deleted,
        bio = offer.bio,
        idShoe = offer.shoe.id,
        idSize = offer.size.id,
        idUser = offer.user.id
)

fun offertoDTOMyOffer(offer: Offer): MyOfferDTO = MyOfferDTO(
        id = offer.id,
        price = offer.price,
        active = offer.active,
        deleted = offer.deleted,
        bio = offer.bio,
        shoe = shoeToDTO(offer.shoe),
        size = sizeToDTO(offer.size),
        user = userToDTO(offer.user)
)

