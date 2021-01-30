package com.example.mediumclone2.retrofit.models.user


import com.squareup.moshi.Json

data class Profile(
    @Json(name = "profile")
    val profile: ProfileX
)