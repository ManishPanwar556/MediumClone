package com.example.mediumclone2.retrofit.api

import com.example.mediumclone2.retrofit.models.article.SingleArticle
import com.example.mediumclone2.retrofit.models.articles.MultipleArticles
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MediumApiService {

    @GET("articles")
    suspend fun getListOfArticles(
        @Query("tag") tag: String? = null,
        @Query("author") author: String? = null,
        @Query("favorited") favorited: String? = null
    ): Response<MultipleArticles>

    @GET("articles/{slug}")
    suspend fun getArticle(@Path("slug") slug:String):Response<SingleArticle>
}