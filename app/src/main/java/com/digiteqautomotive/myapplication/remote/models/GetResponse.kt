package com.digiteqautomotive.myapplication.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class SwapiResponse(
    val message: String,
    val result: Result,
    val apiVersion: String,
    val timestamp: String,
    val support: Support,
    val social: Social
)

@Serializable
data class Result(
    val properties: Properties,
    val _id: String,
    val description: String,
    val uid: String,
    val __v: Int
)

@Serializable
data class Properties(
    val created: String,
    val edited: String,
    val name: String,
    val gender: String,
    val skin_color: String,
    val hair_color: String,
    val height: String,
    val eye_color: String,
    val mass: String,
    val homeworld: String,
    val birth_year: String,
    val url: String
)

@Serializable
data class Support(
    val contact: String,
    val donate: String,
    val partnerDiscounts: PartnerDiscounts
)

@Serializable
data class PartnerDiscounts(
    val saberMasters: SaberMasters
)

@Serializable
data class SaberMasters(
    val link: String,
    val details: String
)

@Serializable
data class Social(
    val discord: String,
    val reddit: String,
    val github: String
)