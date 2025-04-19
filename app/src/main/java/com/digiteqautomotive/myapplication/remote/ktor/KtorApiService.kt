package com.digiteqautomotive.myapplication.remote.ktor

import com.digiteqautomotive.myapplication.remote.models.PostRequest
import com.digiteqautomotive.myapplication.remote.models.PostResponse
import com.digiteqautomotive.myapplication.remote.models.SwapiResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

object KtorApiService {

    private const val BASE_URL = "https://swapi.tech/api/"

    suspend fun getItem(id: String): SwapiResponse {
        return KtorClientProvider.client.get("$BASE_URL/people/$id")
            .body()
    }

    suspend fun postItem(content: String): PostResponse {
        return KtorClientProvider.client.post("$BASE_URL/item") {
            contentType(ContentType.Application.Json)
            setBody(PostRequest(content))
        }.body()
    }
}