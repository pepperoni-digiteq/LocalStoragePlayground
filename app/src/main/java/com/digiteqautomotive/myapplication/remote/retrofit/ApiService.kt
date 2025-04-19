package com.digiteqautomotive.myapplication.remote.retrofit

import com.digiteqautomotive.myapplication.remote.models.PostRequest
import com.digiteqautomotive.myapplication.remote.models.PostResponse
import com.digiteqautomotive.myapplication.remote.models.SwapiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("people/{id}")
    suspend fun getItem(
        @Path("id") id: String
    ): SwapiResponse

    @POST("item")
    suspend fun postItem(
        @Body request: PostRequest
    ): PostResponse
}