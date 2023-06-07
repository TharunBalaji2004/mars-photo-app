package com.example.android.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

/*
* This file creates a Network Layer between App and Web Service
*/

// API Link
private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

// Moshi Builder
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Retrofit Builder
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// Interface - which tells how Retrofit talks to web server using HTTP Requests
interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}

/*
* Singleton Pattern: One and only instance of object is created and has one global point of access to object
* So, when {MarsApi.retrofitService} is called the caller will access the same singleton Retrofit object which was created during first access
*
* NOTE: App needs only one instance of Retrofit API
 */
object MarsApi {
    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}
