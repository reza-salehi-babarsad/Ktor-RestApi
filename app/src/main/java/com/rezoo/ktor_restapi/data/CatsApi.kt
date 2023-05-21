package com.rezoo.ktor_restapi.data

import retrofit2.http.GET

interface CatsApi {
    @GET("/randomcat")
    suspend fun getRandomCat():Cat

    companion object{
        const val BASE_URL = "http://192.168.1.50:8080"
    }
}