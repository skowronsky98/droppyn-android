package com.droppyn.uitl

import com.droppyn.database.entity.*
import com.droppyn.domain.Media

class TestUtil {


    companion object {

        fun createDatabseBrand(): DatabaseBrand = DatabaseBrand(
                id = "6054bd791f943b17604b19a0",
                name = "Nike",
                media = Media(imageUrl = "img",smallImageUrl = "small",thumbUrl = "thumb")
        )

        fun createDatabaseSize(): DatabaseSize = DatabaseSize(
                id = "1size",
                us = 9.0,
                uk = 8.0,
                eu = "42.5",
                type = "type",
                brandId = createDatabseBrand().id
        )

        fun createDatabaseShoe(): DatabaseShoe = DatabaseShoe(
                id = "1shoe",
                model = "Trainer",
                brandId = createDatabseBrand().id,
                media = Media(imageUrl = "img",smallImageUrl = "small",thumbUrl = "thumb")
        )



        fun createDatabseUser(): DatabaseUser = DatabaseUser(
                id = "1",
                username = "skoczek",
                email = "skocze@gmail.com",
                firstname = "Adam",
                surname = "Malysz",
                phone = 123321123,
                photoURL = "photoURL",
                bio = "",
                idDefultSize = createDatabaseSize().id

        )

        fun createDatabaseOffer(): DatabaseOffer = DatabaseOffer(
                id = "1offer",
                price = 450.0,
                active = true,
                deleted = false,
                bio = "fajny but",
                idShoe = createDatabaseShoe().id,
                idSize = createDatabaseSize().id,
                idUser = createDatabseUser().id
        )

        fun createDatabaseMyOffer(): DatabaseMyOffer = DatabaseMyOffer(
                id = "1myoffer",
                price = 10021.0,
                active = true,
                deleted = false,
                bio = "kox but",
                idShoe = createDatabaseShoe().id,
                idSize = createDatabaseSize().id,
                idUser = createDatabseUser().id
        )
    }
}