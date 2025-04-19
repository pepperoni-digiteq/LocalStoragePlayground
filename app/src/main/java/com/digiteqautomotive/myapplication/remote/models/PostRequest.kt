package com.digiteqautomotive.myapplication.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(
    val content: String
)