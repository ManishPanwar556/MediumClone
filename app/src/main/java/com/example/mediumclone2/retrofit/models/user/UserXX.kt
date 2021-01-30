package com.example.mediumclone2.retrofit.models.user


import com.squareup.moshi.Json

data class UserXX(
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String
)