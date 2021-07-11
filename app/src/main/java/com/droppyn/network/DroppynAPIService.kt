package com.droppyn.network

import com.droppyn.network.dto.BrandDTO
import com.droppyn.network.dto.ShoeDTO
import com.droppyn.network.dto.SizeDTO
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_LOCAL_URL = "http://10.0.2.2:8080/"
private const val BASE_URL = "https://droppyn.herokuapp.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_LOCAL_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface DroppynAPIService {
    @GET("brand/all")
    suspend fun getBrandProperties(): List<BrandDTO>

    @GET("shoe/all")
    suspend fun getShoesProperties(): List<ShoeDTO>

    @GET("sizechart/all")
    suspend fun getSizeChartProperties(): List<SizeDTO>

//    @GET("mentee")
//    suspend fun getMentee(@Query("email") email : String) : MenteeDTO
}

object DroppynApi{
    val retrofitService : DroppynAPIService by lazy {
        retrofit.create(DroppynAPIService::class.java)
    }
}