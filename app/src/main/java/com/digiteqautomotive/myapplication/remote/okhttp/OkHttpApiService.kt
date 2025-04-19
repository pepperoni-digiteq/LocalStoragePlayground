package com.digiteqautomotive.myapplication.remote.okhttp

import com.digiteqautomotive.myapplication.remote.models.PostRequest
import com.digiteqautomotive.myapplication.remote.models.PostResponse
import com.digiteqautomotive.myapplication.remote.models.SwapiResponse
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

object OkHttpService {
    private val gson = Gson()
    private val client = OkHttpClientProvider.client
    private val JSON = "application/json; charset=utf-8".toMediaType()

    suspend fun getItem(id: String): SwapiResponse? {
        val request = Request.Builder()
            .url("https://swapi.tech/api/people/$id")
            .get()
            .build()

        val response = client.newCall(request).execute()
        val body = response.body?.string()

        return if (response.isSuccessful && body != null) {
            gson.fromJson(body, SwapiResponse::class.java)
        } else {
            null
        }
    }

    suspend fun postItem(postRequest: PostRequest): PostResponse? {
        val jsonBody = gson.toJson(postRequest)
        val requestBody = jsonBody.toRequestBody(JSON)

        val request = Request.Builder()
            .url("https://your.api.url/items")
            .post(requestBody)
            .build()

        val response = client.newCall(request).execute()
        val body = response.body?.string()

        return if (response.isSuccessful && body != null) {
            gson.fromJson(body, PostResponse::class.java)
        } else {
            null
        }
    }
}