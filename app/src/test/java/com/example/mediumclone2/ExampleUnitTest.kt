package com.example.mediumclone2

import com.example.mediumclone2.retrofit.api.MediumApiService
import com.example.mediumclone2.retrofit.api.MediumAuthenticationService
import com.example.mediumclone2.retrofit.models.postArticle.Article
import com.example.mediumclone2.retrofit.models.postArticle.ArticlePost
import com.example.mediumclone2.retrofit.models.user.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    lateinit var mediumApiService: MediumApiService
    lateinit var authenticationService: MediumAuthenticationService
    lateinit var gson: Gson
    val token =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTM4OTUzLCJ1c2VybmFtZSI6InZpcnVzaGthIiwiZXhwIjoxNjE3MjA5MTMzfQ.SfTI9xEOqeV_jCnRnn-UoxT-hcNaPRqfd0hJ563VXtY"

    @Before
    fun setUp() {
        gson = GsonBuilder().create()
        mediumApiService = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://conduit.productionready.io/api/").build()
            .create(MediumApiService::class.java)
        authenticationService =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://conduit.productionready.io/api/").build()
                .create(MediumAuthenticationService::class.java)
    }

    @Test
    fun get_All_Articles() {
        runBlocking {
            val res = mediumApiService.getListOfArticles()
            assertNotNull(res.body()?.articles)
        }

    }

    @Test
    fun get_article_through_slug() {
        runBlocking {
            val res =
                mediumApiService.getArticle("my-awesome-article-this-is-my-awesome-description-read-more-my_awesome_taggandhiblacklivesmatter-tzl1mp")
            assertNotNull(res.body()?.article)
        }
    }

    @Test
    fun signUpUser() {
        runBlocking {
            val user = UserX("viratkohli@test.com", "virat1818", "virushka")
            val userRegister = UserRegister(user)
            val res = authenticationService.registerUser(userRegister)
            assertNotNull(res.body()?.user)
        }
    }

    @Test
    fun getUser() {
        runBlocking {
            val res = authenticationService.getCurrentUser("Token ${token}")
            assertNotNull(res.body()?.user)
        }
    }

    @Test
    fun signInUser() {
        runBlocking {
            val user = UserXX("viratkohli@test.com", "virat1818")
            val userLogIn = UserLogIn(user)
            val res = authenticationService.loginUser(userLogIn)
            assertNotNull(res.body()?.user)
        }
    }
    @Test
    fun postArticle(){
        runBlocking{
            val article=Article("Hello","Hello World", listOf<String>(),"New World")
            val articlePost=ArticlePost(article)
            val res=authenticationService.postArticle(articlePost,"Token $token")
            assertNotNull(res.body()?.article)
        }
    }
    @Test
    fun getProfile() {
        runBlocking {
            val res = authenticationService.getProfile("virushka")
            assertNotNull(res.body()?.profile)
        }
    }
    @Test
    fun feedArticles(){
        runBlocking {
            val res=authenticationService.feedArticles("Token $token")
            assertNotNull(res.body()?.articles)
        }
    }


}