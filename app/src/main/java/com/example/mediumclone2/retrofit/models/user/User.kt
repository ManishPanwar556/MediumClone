package com.example.mediumclone2.retrofit.models.user


import com.squareup.moshi.Json

data class User(
    @Json(name = "bio")
    val bio: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "image")
    val image: Any,
    @Json(name = "token")
    val token: String,
    @Json(name = "username")
    val username: String
)