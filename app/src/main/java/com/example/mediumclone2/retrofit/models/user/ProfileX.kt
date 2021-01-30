package com.example.mediumclone2.retrofit.models.user


import com.squareup.moshi.Json

data class ProfileX(
    @Json(name = "bio")
    val bio: String,
    @Json(name = "following")
    val following: Boolean,
    @Json(name = "image")
    val image: String,
    @Json(name = "username")
    val username: String
)