package com.digiteqautomotive.myapplication.remote.grpc

import com.example.grpc.MyServiceGrpcKt
import com.example.grpc.getItemRequest
import com.example.grpc.postItemRequest

class MyServiceClient {

    private val stub = MyServiceGrpcKt.MyServiceCoroutineStub(GrpcChannelProvider.channel)

    suspend fun getItem(id: String): String {
        val request = getItemRequest {
            this.id = id
        }

        val response = stub.getItem(request)
        return "Item: ${response.id} - ${response.content}"
    }

    suspend fun postItem(content: String): String {
        val request = postItemRequest {
            this.content = content
        }

        val response = stub.postItem(request)
        return "Created item ID: ${response.id} with status: ${response.status}"
    }
}