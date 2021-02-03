package com.example.mediumclone2.retrofit.models.postArticle


import com.squareup.moshi.Json

data class Article(
    @Json(name = "body")
    val body: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "tagList")
    val tagList: List<String>,
    @Json(name = "title")
    val title: String
)