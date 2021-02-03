package com.example.mediumclone2.repository

import com.example.mediumclone2.retrofit.api.MediumApiService
import com.example.mediumclone2.retrofit.api.MediumAuthenticationService
import com.example.mediumclone2.retrofit.models.article.SingleArticle
import com.example.mediumclone2.retrofit.models.articles.Article
import com.example.mediumclone2.retrofit.models.articles.MultipleArticles
import com.example.mediumclone2.retrofit.models.postArticle.ArticlePost
import com.example.mediumclone2.retrofit.models.user.Profile
import com.example.mediumclone2.retrofit.models.user.UserAuthentication
import com.example.mediumclone2.retrofit.models.user.UserLogIn
import com.example.mediumclone2.retrofit.models.user.UserRegister
import com.example.mediumclone2.room.UserDao
import com.example.mediumclone2.room.UserEntity
import retrofit2.Response
import java.nio.file.attribute.UserDefinedFileAttributeView
import javax.inject.Inject

class MediumRepository @Inject constructor(
    private val mediumApiService: MediumApiService,
    private val authenticationService: MediumAuthenticationService,
    private val userDao: UserDao
) {

    suspend fun getAllArticles(): Response<MultipleArticles> {
        return mediumApiService.getListOfArticles()
    }

    suspend fun getArticleBySlug(slug: String): Response<SingleArticle> {
        return mediumApiService.getArticle(slug)
    }

    suspend fun signUpUser(userRegister: UserRegister): Response<UserAuthentication> {
        return authenticationService.registerUser(userRegister)
    }

    suspend fun logInUser(userLogIn: UserLogIn): Response<UserAuthentication> {
        return authenticationService.loginUser(userLogIn)
    }

    suspend fun getProfile(userName: String): Response<Profile> {
        return authenticationService.getProfile(userName)
    }

    suspend fun getUser(token: String): Response<UserAuthentication> {
        return authenticationService.getCurrentUser("Token ${token}")
    }

    suspend fun insertUserToRoom(userEntity: UserEntity) {
        userDao.insertUser(userEntity)
    }

    suspend fun deleteUser() {
        userDao.delete()
    }

    suspend fun getUser(): UserEntity? {
        return userDao.getUsers()
    }
    suspend fun postArticle(articlePost: ArticlePost,token: String):Response<SingleArticle>{
        return authenticationService.postArticle(articlePost,token)
    }
    suspend fun feedArticles(token: String):Response<MultipleArticles>{
        return authenticationService.feedArticles(token)
    }
}