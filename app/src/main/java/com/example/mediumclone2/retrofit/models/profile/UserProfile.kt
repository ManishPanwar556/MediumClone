package com.example.mediumclone2.retrofit.models.profile


import com.squareup.moshi.Json

data class UserProfile(
    @Json(name = "profile")
    val profile: Profile
)