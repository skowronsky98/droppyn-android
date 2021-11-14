package com.droppyn.uitl

import com.droppyn.database.entity.*
import com.droppyn.domain.Media

class TestUtil {


    companion object {

        fun addToken(): DatabaseToken = DatabaseToken(
            accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYXBjemFuIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlzcyI6IkRyb3BweW4iLCJleHAiOjE2MzY1NTAwNzZ9.Tpe8M0O8lm2kqkdW4p8leqEosp9ONQVAzQrOnU3uQus",
            refreshToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYXBjemFuIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlzcyI6IkRyb3BweW4iLCJleHAiOjE2NjgwODM1NDJ9.1l29rEmw1OiCVvWiJOxo8245gdGfP-cM_mpcA3JC7mQ"
        )

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



        fun createDatabseUser(): DatabaseProfile = DatabaseProfile(
                id = "618bb9bcde3bed6158e96c06",
                username = "japczan",
                email = "aa.krasucki@gamil.com",
                firstname = null,
                surname = null,
                phone = null,
                photoURL = null,
                bio = null,
                idDefultSize = null

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