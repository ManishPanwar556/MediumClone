package com.example.mediumclone2.retrofit.api

import com.example.mediumclone2.retrofit.models.article.SingleArticle
import com.example.mediumclone2.retrofit.models.articles.MultipleArticles
import com.example.mediumclone2.retrofit.models.postArticle.ArticlePost
import com.example.mediumclone2.retrofit.models.user.Profile
import com.example.mediumclone2.retrofit.models.user.UserAuthentication
import com.example.mediumclone2.retrofit.models.user.UserLogIn
import com.example.mediumclone2.retrofit.models.user.UserRegister
import retrofit2.Response
import retrofit2.http.*

interface MediumAuthenticationService {

    @POST("users")
    suspend fun registerUser(@Body userRegister: UserRegister): Response<UserAuthentication>

    @GET("user")
    suspend fun getCurrentUser(@Header("Authorization") token: String): Response<UserAuthentication>

    @GET("profiles/{username}")
    suspend fun getProfile(@Path("username") username: String): Response<Profile>

    @POST("users/login")
    suspend fun loginUser(@Body userLogIn: UserLogIn): Response<UserAuthentication>

    @POST("articles")
    suspend fun postArticle(
        @Body articlePost: ArticlePost,
        @Header("Authorization") token: String
    ): Response<SingleArticle>

    @GET("articles/feed")
    suspend fun feedArticles(@Header("Authorization") token: String)
            : Response<MultipleArticles>
}