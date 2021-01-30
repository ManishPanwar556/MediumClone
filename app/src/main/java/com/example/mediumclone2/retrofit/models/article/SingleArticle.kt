package com.example.mediumclone2.retrofit.models.article


import com.squareup.moshi.Json

data class SingleArticle(
    @Json(name = "article")
    val article: Article
)