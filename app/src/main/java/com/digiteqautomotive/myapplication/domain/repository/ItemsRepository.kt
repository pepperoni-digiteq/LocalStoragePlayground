package com.digiteqautomotive.myapplication.domain.repository

import com.digiteqautomotive.myapplication.domain.models.ItemModel

interface ItemsRepository {
    suspend fun testGrpc()
    suspend fun testKtor(id: String): ItemModel
    suspend fun testRetrofit(id: String): ItemModel
    suspend fun testOkHttp(id: String): ItemModel
    fun clear()
}