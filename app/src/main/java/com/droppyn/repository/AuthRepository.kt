package com.droppyn.repository

import android.util.Log
import com.droppyn.database.DroppynDatabase
import com.droppyn.network.DroppynApi
import com.droppyn.network.dto.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository(private val database: DroppynDatabase) {

    val repository = DroppynRepository(database)

    private suspend fun refreshToken() = withContext(Dispatchers.IO){
        try {
            val refreshToken = database.tokenDao.getToken().refreshToken
            val auth = "Bearer $refreshToken"

            val tokensDTO = DroppynApi.retrofitService.refreshToken(auth)

            database.tokenDao.deleteAll()
            database.tokenDao.insert(NetworkAccessTokenContainer(tokensDTO).asDatabaseModel())
            return@withContext true

        } catch (e: Exception){
            Log.i("retrofit",e.message.toString())
            return@withContext false

        }

    }

    suspend fun getUser(username: String, token: AccessTokensDTO){
        try {

            val auth = "Bearer ${token.accessToken}"

            val user = DroppynApi.retrofitService.getUserByUsername(auth,username)
            database.profileDao.deleteAll()

            Log.i("retrofit",user.id)

            database.profileDao.insert(NetworkUserContainer(user).asDatabaseProfileModel())


        } catch (e: Exception){
            Log.i("retrofit",e.message.toString())

        }
    }

    suspend fun login(username: String, password: String) = withContext(Dispatchers.IO){
        try {

            repository.cleanDatabase()

            val tokensDTO = DroppynApi.retrofitService.login(username = username, password = password)

            getUser(username, tokensDTO)

            database.tokenDao.deleteAll()
            database.tokenDao.insert(NetworkAccessTokenContainer(tokensDTO).asDatabaseModel())

            repository.refreshBrands()
            repository.refreshSizeChart()
            repository.refreshSheos()
            repository.refreshMyOffers()

            return@withContext true

        } catch (e: Exception){
            Log.i("retrofit",e.message.toString())
            return@withContext false

        }

    }

    suspend fun register(email: String, username: String, password: String) = withContext(Dispatchers.IO){
        try {
            repository.cleanDatabase()

            val registerFormDTO = RegisterFormDTO(username = username, email = email, password = password)

            DroppynApi.retrofitService.register(registerFormDTO)

            return@withContext login(username, password)

        } catch (e: Exception){
            Log.i("retrofit",e.message.toString())
            return@withContext false

        }

    }
}