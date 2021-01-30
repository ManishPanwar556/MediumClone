package com.example.mediumclone2.retrofit.models.user


import com.squareup.moshi.Json

data class UserRegister(
    @Json(name = "user")
    val user: UserX
)