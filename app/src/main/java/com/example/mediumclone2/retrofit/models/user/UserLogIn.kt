package com.example.mediumclone2.retrofit.models.user


import com.squareup.moshi.Json

data class UserLogIn(
    @Json(name = "user")
    val user: UserXX
)