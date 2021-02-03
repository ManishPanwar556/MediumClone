package com.example.mediumclone2.retrofit.models.postArticle


import com.squareup.moshi.Json

data class ArticlePost(
    @Json(name = "article")
    val article: Article
)