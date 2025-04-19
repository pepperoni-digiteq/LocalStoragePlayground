package com.digiteqautomotive.myapplication.remote.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientProvider {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://swapi.tech/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}