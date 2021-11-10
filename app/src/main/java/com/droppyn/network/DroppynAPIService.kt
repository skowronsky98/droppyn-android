package com.droppyn.network

import com.droppyn.network.dto.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*


private const val BASE_LOCALHOST_URL = "http://10.0.2.2:8080/"
private const val BASE_LOCAL_URL = "http://192.168.0.12:8080/"
private const val BASE_URL = "https://droppyn.herokuapp.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_LOCALHOST_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface DroppynAPIService {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(@Field("username") username: String, @Field("password") password: String): AccessTokensDTO

    @POST("user/register")
    suspend fun register(@Body registerFormDTO: RegisterFormDTO): UserDTO

    @GET("user/token/refresh")
    suspend fun refreshToken(@Header("Authorization") auth: String): AccessTokensDTO

    @GET("brand/all")
    suspend fun getBrandProperties(@Header("Authorization") auth: String): List<BrandDTO>

    @GET("shoe/all")
    suspend fun getShoesProperties(@Header("Authorization") auth: String): List<ShoeDTO>

    @GET("sizechart?brandId=6054bd791f943b17604b19a0")
    suspend fun getSizeChartProperties(@Header("Authorization") auth: String): List<SizeDTO>

    @GET("user")
    suspend fun getUserProperties(@Header("Authorization") auth: String,
                                  @Query("id") id: String): UserDTO


    @GET("offer/all")
    suspend fun getOffersProperties(@Header("Authorization") auth: String): List<OfferDTO>


    @GET("offer/my/all")
    suspend fun getMyOffersProperties(@Header("Authorization") auth: String,
                                      @Query("userId") idUser: String): List<MyOfferDTO>

    @PUT("offer/my")
    suspend fun updateMyOffer(@Header("Authorization") auth: String,
                              @Query("shoeId") idShoe: String,
                              @Query("userId") idUser: String,
                              @Query("sizeId") idSize: String,
                              @Body myOfferDTO: MyOfferDTO): MyOfferDTO

    @DELETE(value = "offer/my")
    suspend fun deleteMyOffer(@Header("Authorization") auth: String,
                              @Query("id") idMyOffer: String,
                              @Query("userId") idUser: String)

    @POST("offer/my")
    suspend fun createMyOffer(@Header("Authorization") auth: String,
                              @Query("shoeId") idShoe: String,
                              @Query("userId") idUser: String,
                              @Query("sizeId") idSize: String,
                              @Body myOfferDTO: MyOfferDTO): MyOfferDTO


//    @GET("mentee")
//    suspend fun getMentee(@Query("email") email : String) : MenteeDTO
}

object DroppynApi{
    val retrofitService : DroppynAPIService by lazy {
        retrofit.create(DroppynAPIService::class.java)
    }
}