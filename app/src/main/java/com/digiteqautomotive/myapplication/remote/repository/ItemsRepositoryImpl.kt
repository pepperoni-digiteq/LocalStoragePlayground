package com.digiteqautomotive.myapplication.remote.repository

import com.digiteqautomotive.myapplication.domain.models.ItemModel
import com.digiteqautomotive.myapplication.domain.repository.ItemsRepository
import com.digiteqautomotive.myapplication.remote.grpc.MyServiceClient
import com.digiteqautomotive.myapplication.remote.ktor.KtorApiService
import com.digiteqautomotive.myapplication.remote.ktor.KtorClientProvider
import com.digiteqautomotive.myapplication.remote.okhttp.OkHttpService
import com.digiteqautomotive.myapplication.remote.retrofit.ApiService
import com.digiteqautomotive.myapplication.remote.retrofit.RetrofitClientProvider
import com.digiteqautomotive.myapplication.remote.models.SwapiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemsRepositoryImpl: ItemsRepository {

    override suspend fun testGrpc() = withContext(Dispatchers.IO) {
        val client = MyServiceClient()
        val resultGet = client.getItem("123")
        val resultPost = client.postItem("Hello!")
    }

    override suspend fun testKtor(id: String): ItemModel = withContext(Dispatchers.IO) {
        val getResponse = KtorApiService.getItem(id).toItem()
//        val postResponse = KtorApiService.postItem("Hello!")
        println("## Ktor ## $getResponse")
        getResponse
    }

    override suspend fun testRetrofit(id: String): ItemModel = withContext(Dispatchers.IO) {
        val apiService = RetrofitClientProvider.retrofit.create(ApiService::class.java)
        val getItem = apiService.getItem(id).toItem()
//        val postItem = apiService.postItem(PostRequest(content = "Hello!"))
        println("## Retrofit ## $getItem")
        getItem
    }

    override suspend fun testOkHttp(id: String): ItemModel = withContext(Dispatchers.IO) {
        val getItem = OkHttpService.getItem(id)!!.toItem()
//        val postItem = OkHttpService.postItem(PostRequest(content = "Hello!"))
        println("## OkHttp ## $getItem")
        getItem
    }

    override fun clear() {
        KtorClientProvider.clear()
    }

    private fun SwapiResponse.toItem(): ItemModel {
        return ItemModel(
            name = result.properties.name,
            gender = result.properties.gender,
            birthYear = result.properties.birth_year,
            height = result.properties.height,
            mass = result.properties.mass,
            description = result.description,
            homeworldUrl = result.properties.homeworld
        )
    }
}