package com.example.mediumclone2.retrofit.models.articles


import com.squareup.moshi.Json
import java.io.Serializable

data class MultipleArticles(
    @Json(name = "articles")
    val articles: List<Article>,
    @Json(name = "articlesCount")
    val articlesCount: Int
):Serializable