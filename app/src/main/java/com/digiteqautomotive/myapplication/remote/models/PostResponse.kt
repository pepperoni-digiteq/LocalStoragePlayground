package com.digiteqautomotive.myapplication.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(
    val id: String,
    val status: String
)